package kindergarten.management.repository;

import kindergarten.management.model.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Child, String> {

}
