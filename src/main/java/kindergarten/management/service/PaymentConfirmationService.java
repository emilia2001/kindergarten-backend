package kindergarten.management.service;

import kindergarten.management.model.dto.PaymentConfirmationDto;

import java.util.List;

public interface PaymentConfirmationService {

    List<PaymentConfirmationDto> findAllPaymentConfirmations();

    List<PaymentConfirmationDto> findAllPaymentConfirmationsByParent(Long parentId);

    Long findNextId();

    void addPaymentConfirmation(PaymentConfirmationDto paymentConfirmationDto);
}
