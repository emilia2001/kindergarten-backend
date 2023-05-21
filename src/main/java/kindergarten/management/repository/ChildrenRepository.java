package kindergarten.management.repository;

import kindergarten.management.model.entity.Child;
import kindergarten.management.model.enums.EChildStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenRepository extends JpaRepository<Child, String> {

    List<Child> findAllByStatus(EChildStatus status);

    Integer countByGroup_IdAndStatus(Long groupId, EChildStatus status);
}
