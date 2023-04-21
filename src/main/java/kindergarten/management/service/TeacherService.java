package kindergarten.management.service;

import kindergarten.management.model.dto.teacher.TeacherDto;
import kindergarten.management.model.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAllTeachers();

    Teacher addTeacher(TeacherDto teacherDto);
}
