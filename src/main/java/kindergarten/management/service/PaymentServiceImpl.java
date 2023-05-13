package kindergarten.management.service;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import kindergarten.management.mapper.PaymentMapper;
import kindergarten.management.model.dto.ChargeRequest;
import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.model.enums.EPaymentStatus;
import kindergarten.management.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.stripe.exception.AuthenticationException;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Payment> payments = paymentRepository.findAll();
        payments = payments.stream().filter(payment -> payment.getChild().getParent().getId().equals(id)).collect(Collectors.toList());
        return paymentMapper.toDtos(payments);
    }

    @Override
    public Payment updatePayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toEntity(paymentDto);
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(final Long id, final String status) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);
        paymentOpt.ifPresent((Payment payment) -> {
            payment.setStatus(mapStripeStatus(status));
            paymentRepository.save(payment);
        });
    }

    private EPaymentStatus mapStripeStatus(String status) {
        if ("succeded".equals(status)) {
            return EPaymentStatus.PAID;
        } else {
            return EPaymentStatus.UNPAID;
        }
    }

    @Override
    public void saveAll(List<Payment> paymentsCurrentMonth) {
        paymentRepository.saveAll(paymentsCurrentMonth);
    }
}
