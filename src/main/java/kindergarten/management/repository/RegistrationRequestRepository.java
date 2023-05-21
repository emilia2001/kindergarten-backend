package kindergarten.management.repository;

import kindergarten.management.model.entity.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    List<RegistrationRequest> findAllByChildParentId(Long id);
}
