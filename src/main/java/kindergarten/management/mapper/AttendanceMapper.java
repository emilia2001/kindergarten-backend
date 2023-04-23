package kindergarten.management.mapper;

import kindergarten.management.model.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    @Mapping(target = "noAttendances", ignore = true)
    List<Attendance> copy(final List<Attendance> toCopy);

}
