package kindergarten.management.mapper;

import kindergarten.management.model.dto.teacher.TeacherDto;
import kindergarten.management.model.entity.Teacher;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TeacherMapper {

    Teacher toEntity(TeacherDto teacherDto);
}
