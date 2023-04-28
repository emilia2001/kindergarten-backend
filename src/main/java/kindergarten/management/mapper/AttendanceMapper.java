package kindergarten.management.mapper;

import kindergarten.management.model.dto.AttendanceDto;
import kindergarten.management.model.entity.Attendance;
import kindergarten.management.model.entity.Child;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mapping(source = "child", target = "childCnp", qualifiedByName = "childToCnp")
    AttendanceDto toDto (Attendance attendance);

    List<AttendanceDto> toDtos(List<Attendance> attendances);

    @Mapping(source = "childCnp", target = "child", qualifiedByName = "cnpToChild")
    Attendance toEntity(AttendanceDto attendanceDto);

    List<Attendance> toEntities(List<AttendanceDto> attendanceDtos);

    @Named("childToCnp")
    static String childToCnp(Child child) {
        return child.getCnp();
    }

    @Named("cnpToChild")
    static Child cnpToChild(String cnp) {
        Child child = new Child();
        child.setCnp(cnp);
        return child;
    }
}
