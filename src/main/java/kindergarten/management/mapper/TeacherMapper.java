package kindergarten.management.mapper;

import kindergarten.management.model.dto.teacher.TeacherAddDto;
import kindergarten.management.model.dto.teacher.TeacherDto;
import kindergarten.management.model.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface TeacherMapper {

    Teacher toEntityFromTeacherAddDto(TeacherAddDto teacherAddDto);

    @Mapping(source = "group", target = "groupDto")
    TeacherDto toTeacherDto(Teacher teacher);

    List<TeacherDto> entitiesToTeacherDtos(List<Teacher> entities);
}
