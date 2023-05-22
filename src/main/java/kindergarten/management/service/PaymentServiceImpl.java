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
    public PaymentDto updatePaymentStatus(final Long id, final String status, int amount) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);
        paymentOpt.ifPresent((Payment payment) -> {
            EPaymentStatus newStatus = mapStripeStatus(status);
            if (newStatus == EPaymentStatus.PAID) {
                if (amount == payment.getTotalAmount()) {
                    payment.setStatus(newStatus);
                } else if (payment.getOutstandingAmount() == amount) {
                    payment.setOutstandingAmount(0);
                } else {
                    if (payment.getOutstandingAmount() < amount) {
                        payment.setOutstandingAmount(0);
                        payment.setCurrentAmount(payment.getCurrentAmount() - amount);
                    } else {
                        payment.setOutstandingAmount(payment.getOutstandingAmount() - amount);
                    }
                }
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
            if (amount == payment.getTotalAmount()) {
                payment.setStatus(EPaymentStatus.PAID);
            } else if (payment.getOutstandingAmount() == amount) {
                payment.setOutstandingAmount(0);
            } else {
                if (payment.getOutstandingAmount() < amount) {
                    payment.setCurrentAmount(payment.getCurrentAmount() - (amount - payment.getOutstandingAmount()));
                    payment.setOutstandingAmount(0);
                } else {
                    payment.setOutstandingAmount(payment.getOutstandingAmount() - amount);
                }
            }
            paymentRepository.save(payment);
        });
        return paymentOpt.map(paymentMapper::toDto).orElse(null);
    }
}
