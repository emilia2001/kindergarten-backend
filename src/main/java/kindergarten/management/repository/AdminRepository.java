package kindergarten.management.repository;

import kindergarten.management.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(@Param("username") String username);

}
