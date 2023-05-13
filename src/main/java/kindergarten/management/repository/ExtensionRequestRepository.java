package kindergarten.management.repository;

import kindergarten.management.model.entity.ExtensionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRequestRepository extends JpaRepository<ExtensionRequest, Long> {
}
