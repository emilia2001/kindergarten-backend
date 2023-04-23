package kindergarten.management.repository;

import kindergarten.management.model.entity.Attendance;
import kindergarten.management.model.entity.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {

    List<Attendance> findAllByMonth(final String month);

}
