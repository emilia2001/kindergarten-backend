package kindergarten.management.service.job;

import kindergarten.management.mapper.AttendanceMapper;
import kindergarten.management.model.entity.Attendance;
import kindergarten.management.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class UpdateAttendancesJob {

    private final AttendanceService attendanceService;

    private final AttendanceMapper attendanceMapper;

//    @Scheduled(cron = "0 0 0 1 1/1 *") // first day of a new month
    @Scheduled(fixedRate = 30 * 1000) // for testing purposes -> every 30 sec
    public void updateAttendances() {
        List<Attendance> previousMonthAttendances = attendanceService.getAllForMonth(LocalDate.now().minusMonths(1).getMonth().name());
        List<Attendance> currentMonthAttendances = attendanceMapper.copy(previousMonthAttendances);
        currentMonthAttendances.forEach(attendance -> {
            attendance.setNoAttendances(0);
            attendance.setMonth(LocalDate.now().getMonth().name());
        });
        attendanceService.saveAll(currentMonthAttendances);
    }

}
