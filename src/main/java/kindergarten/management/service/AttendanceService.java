package kindergarten.management.service;

import kindergarten.management.model.dto.AttendanceDto;

import java.util.List;
import java.util.Map;

public interface AttendanceService {

    List<AttendanceDto> findAllForMonth(final String month);

    void saveAll(final List<AttendanceDto> toBeSaved);

    void deleteAll(final List<AttendanceDto> toBeDeleted);

    Map<String, Long> findAllNoAttendancesForMonth(final String month);
}
