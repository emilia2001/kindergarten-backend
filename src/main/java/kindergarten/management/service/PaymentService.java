package kindergarten.management.service;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import kindergarten.management.model.dto.ChargeRequest;
import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.model.enums.EPaymentStatus;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findAllForMonth(final String month);

    List<PaymentDto> findAllForParent(final Long id);

    Payment updatePayment(PaymentDto paymentDto);

    void updatePaymentStatus(Long id, String status);

    void saveAll(List<Payment> paymentsCurrentMonth);

}
