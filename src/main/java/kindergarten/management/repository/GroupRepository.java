package kindergarten.management.repository;

import kindergarten.management.model.entity.Group;
import kindergarten.management.model.enums.EGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByGroupType(EGroupType type);
}
