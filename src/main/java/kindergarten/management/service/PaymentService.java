package kindergarten.management.service;

import kindergarten.management.exceptions.PaymentException;
import kindergarten.management.model.dto.ChargeRequest;
import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findAllForMonth(final String month);

    List<PaymentDto> findAllForParent(final Long id);

    Payment updatePayment(PaymentDto paymentDto);

    PaymentDto chargeByParent(ChargeRequest chargeRequest) throws PaymentException;

    void saveAll(List<Payment> paymentsCurrentMonth);

    PaymentDto updatePaymentStatusByAdmin(long paymentId, int amount);
}
