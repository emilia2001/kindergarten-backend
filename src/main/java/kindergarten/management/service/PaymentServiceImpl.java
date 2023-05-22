package kindergarten.management.service;

import kindergarten.management.mapper.PaymentMapper;
import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.model.enums.EPaymentStatus;
import kindergarten.management.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    private static final String secretKey = "";

    @Override
    public List<PaymentDto> findAllForMonth(String month) {
        YearMonth yearMonth = YearMonth.parse(month);

        return paymentMapper.toDtos(paymentRepository.findAllByMonth(yearMonth));
    }

    @Override
    public List<PaymentDto> findAllForParent(Long id) {
        return paymentMapper.toDtos(paymentRepository.findAllByChildParentIdOrderByMonthDesc(id));
    }

    @Override
    public Payment updatePayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toEntity(paymentDto);
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentDto updatePaymentStatus(final Long id, final String status, int amount) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);
        paymentOpt.ifPresent((Payment payment) -> {
            EPaymentStatus newStatus = mapStripeStatus(status);
            if (newStatus == EPaymentStatus.PAID) {
                if (amount == payment.getTotalUnpaidAmount())
                    payment.setStatus(EPaymentStatus.PAID);
                payment.setTotalUnpaidAmount(payment.getTotalUnpaidAmount() - amount);
            }
            paymentRepository.save(payment);
        });
        return paymentOpt.map(paymentMapper::toDto).orElse(null);
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
