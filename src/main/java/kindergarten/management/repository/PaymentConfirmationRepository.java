package kindergarten.management.repository;

import kindergarten.management.model.entity.PaymentConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentConfirmationRepository extends JpaRepository<PaymentConfirmation, Long> {

    List<PaymentConfirmation> findAllByPaymentChildParentId(Long id);

    Optional<PaymentConfirmation> findFirstByOrderByIdDesc();

}
