package kindergarten.management.service;

import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findAllForMonth(final String month);

    List<PaymentDto> findAllForParent(final Long id);

    Payment updatePayment(PaymentDto paymentDto);

    PaymentDto updatePaymentStatus(Long id, String status, int amount);

    void saveAll(List<Payment> paymentsCurrentMonth);

    PaymentDto updatePaymentStatusByAdmin(long paymentId, int amount);
}
