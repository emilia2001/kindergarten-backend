package kindergarten.management.mapper;

import kindergarten.management.model.dto.teacher.TeacherAddDto;
import kindergarten.management.model.dto.teacher.TeacherDto;
import kindergarten.management.model.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
import java.util.List;


@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface TeacherMapper {
    @Mapping(source = "picture", target = "picture", qualifiedByName = "base64ToByteArray")
    Teacher toEntityFromTeacherAddDto(TeacherAddDto teacherAddDto);
    @Mapping(source = "picture", target = "picture", qualifiedByName = "byteArrayToBase64")
    @Mapping(source = "group", target = "groupDto")
    TeacherDto toTeacherDto(Teacher teacher);

    List<TeacherDto> entitiesToTeacherDtos(List<Teacher> entities);

    @Named("base64ToByteArray")
    static byte[] base64ToByteArray(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    @Named("byteArrayToBase64")
    static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
