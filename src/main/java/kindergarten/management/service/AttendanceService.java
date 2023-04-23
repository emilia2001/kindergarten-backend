package kindergarten.management.service;

import kindergarten.management.model.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    List<Attendance> getAllForMonth(final String month);

    void saveAll(final List<Attendance> toBeSaved);
}
