package kindergarten.management.repository;

import kindergarten.management.model.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findByUsername(@Param("username") String username);

}
