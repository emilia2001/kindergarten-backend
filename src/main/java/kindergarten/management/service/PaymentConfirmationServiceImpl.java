package kindergarten.management.service;

import kindergarten.management.mapper.PaymentConfirmationMapper;
import kindergarten.management.model.dto.PaymentConfirmationDto;
import kindergarten.management.model.entity.PaymentConfirmation;
import kindergarten.management.repository.PaymentConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentConfirmationServiceImpl implements PaymentConfirmationService {

    PaymentConfirmationRepository paymentConfirmationRepository;
    PaymentConfirmationMapper paymentConfirmationMapper;

    @Override
    public List<PaymentConfirmationDto> findAllPaymentConfirmations() {
        return paymentConfirmationMapper.toDtos(paymentConfirmationRepository.findAll());
    }

    @Override
    public List<PaymentConfirmationDto> findAllPaymentConfirmationsByParent(Long parentId) {
        return paymentConfirmationMapper.toDtos(paymentConfirmationRepository.findAllByPaymentChildParentId(parentId));
    }

    @Override
    public Long findNextId() {
        Optional<PaymentConfirmation> paymentConfirmation = paymentConfirmationRepository.findFirstByOrderByIdDesc();
        return paymentConfirmation.map(confirmation -> confirmation.getId() + 1).orElse(1L);
    }

    @Override
    public void addPaymentConfirmation(PaymentConfirmationDto paymentConfirmationDto) {
        paymentConfirmationRepository.save(paymentConfirmationMapper.toEntity(paymentConfirmationDto));
    }
}
