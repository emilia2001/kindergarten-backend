package kindergarten.management.service;

import kindergarten.management.model.dto.teacher.TeacherAddDto;
import kindergarten.management.model.dto.teacher.TeacherDto;
import kindergarten.management.model.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<TeacherDto> findAllTeachers();

    Teacher addTeacher(TeacherAddDto teacherAddDto);
}
