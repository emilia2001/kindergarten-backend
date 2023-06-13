package kindergarten.management.service;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import kindergarten.management.exceptions.PaymentException;
import kindergarten.management.mapper.PaymentMapper;
import kindergarten.management.model.dto.ChargeRequest;
import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.model.enums.EPaymentStatus;
import kindergarten.management.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    private static void setSecretKey() {
        Stripe.apiKey = "sk_test_51N2F4DBquZgmE331IWNgu36doCouc1wFi1cg8QnuoXlYfLDNoFc9lp9VGDG8qGvHSpByzp7e4YQLw1qAHdeuvGpV00I78eECzS";
    }

    @Override
    public List<PaymentDto> findAllForMonth(String month) {
        YearMonth yearMonth = YearMonth.parse(month);

        return paymentMapper.toDtos(paymentRepository.findAllByMonth(yearMonth));
    }

    @Override
    public List<PaymentDto> findAllForParent(Long id) {
        return paymentMapper.toDtos(paymentRepository.findAllByChildParentIdOrderByMonthDescChildFirstNameAsc(id));
    }

    @Override
    public Payment updatePayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toEntity(paymentDto);
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentDto chargeByParent(ChargeRequest chargeRequest) throws PaymentException {
        setSecretKey();

        Payment payment = paymentRepository.findById(chargeRequest.getPaymentId()).orElse(null);

        if (payment == null) {
            throw new PaymentException("Plata nu exista");
        }
        if (payment.getTotalUnpaidAmount() > chargeRequest.getAmount()) {
            throw new PaymentException("Suma depășește suma de plată");
        }

        int amount = chargeRequest.getAmount();
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount * 100);
        params.put("currency", "RON");
        params.put("source", chargeRequest.getToken());
        params.put(
                "description",
                "Plata pentru " + payment.getChild().getFirstName() + " " + payment.getChild().getLastName()
        );

        Charge charge = null;

        try {
            charge = Charge.create(params);
        } catch (StripeException e) {
            throw new PaymentException(e.getMessage());
        }

        EPaymentStatus newStatus = mapStripeStatus(charge.getStatus());
        if (newStatus == EPaymentStatus.PAID) {
            if (chargeRequest.getAmount() == payment.getTotalUnpaidAmount()) {
                payment.setStatus(EPaymentStatus.PAID);
            }
            payment.setTotalUnpaidAmount(payment.getTotalUnpaidAmount() - amount);
        }
        paymentRepository.save(payment);

        return paymentMapper.toDto(payment);
    }

    private EPaymentStatus mapStripeStatus(String status) {
        if ("succeeded".equals(status)) {
            return EPaymentStatus.PAID;
        } else {
            return EPaymentStatus.UNPAID;
        }
    }

    @Override
    public void saveAll(List<Payment> paymentsCurrentMonth) {
        paymentRepository.saveAll(paymentsCurrentMonth);
    }

    @Override
    public PaymentDto updatePaymentStatusByAdmin(long paymentId, int amount) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        paymentOpt.ifPresent((Payment payment) -> {
            if (amount == payment.getTotalUnpaidAmount())
                payment.setStatus(EPaymentStatus.PAID);
            payment.setTotalUnpaidAmount(payment.getTotalUnpaidAmount() - amount);
            paymentRepository.save(payment);
        });
        return paymentOpt.map(paymentMapper::toDto).orElse(null);
    }
}
