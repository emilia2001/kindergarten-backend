package kindergarten.management.service;

import kindergarten.management.model.entity.Attendance;
import kindergarten.management.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> getAllForMonth(final String month) {
        return attendanceRepository.findAllByMonth(month);
    }

    @Override
    public void saveAll(List<Attendance> toBeSaved) {
        attendanceRepository.saveAll(toBeSaved);
    }

}
