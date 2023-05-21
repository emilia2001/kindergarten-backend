package kindergarten.management.repository;

import kindergarten.management.model.entity.ExtensionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtensionRequestRepository extends JpaRepository<ExtensionRequest, Long> {
    List<ExtensionRequest> findAllByChildParentId(Long id);
}
