package kindergarten.management.service;

import kindergarten.management.mapper.AttendanceMapper;
import kindergarten.management.model.dto.AttendanceDto;
import kindergarten.management.model.entity.Attendance;
import kindergarten.management.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public List<AttendanceDto> findAllForMonth(String month) {
        YearMonth yearMonth = YearMonth.parse(month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return attendanceMapper.toDtos(attendanceRepository.findAllByDateBetween(startDate, endDate));
    }

    @Override
    public void saveAll(List<AttendanceDto> toBeSaved) {
        List<Attendance> attendancesToBeSaved = attendanceMapper.toEntities(toBeSaved);
        attendanceRepository.saveAll(attendancesToBeSaved);
    }

    @Override
    public void deleteAll(List<AttendanceDto> toBeDeleted) {
        attendanceRepository.deleteAllById(toBeDeleted.stream().map(AttendanceDto::getId).collect(Collectors.toList()));
    }

    @Override
    public Map<String, Long> findAllNoAttendancesForMonth(String month) {
        List<AttendanceDto> attendanceDtos = findAllForMonth(month);
        return attendanceDtos.stream()
                .collect(Collectors.groupingBy(AttendanceDto::getChildCnp, Collectors.counting()));

    }

}
