package kindergarten.management.service;

import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.model.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findAllForMonth(final String month);

    Payment updatePayment(PaymentDto paymentDto);

}
