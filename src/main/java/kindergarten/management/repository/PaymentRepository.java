package kindergarten.management.repository;

import kindergarten.management.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByMonth(YearMonth month);

    List<Payment> findAllByChildParentIdOrderByMonthDescChildLastNameAsc(Long parentId);
}
