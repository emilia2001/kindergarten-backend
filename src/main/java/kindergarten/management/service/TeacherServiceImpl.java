package kindergarten.management.service;

import kindergarten.management.mapper.TeacherMapper;
import kindergarten.management.model.dto.teacher.TeacherDto;
import kindergarten.management.model.entity.Group;
import kindergarten.management.model.entity.Teacher;
import kindergarten.management.repository.GroupRepository;
import kindergarten.management.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final GroupRepository groupRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher addTeacher(TeacherDto teacherDto) {
        Teacher teacherToBeAdded = teacherMapper.toEntity(teacherDto);
        Optional<Group> teacherGroup = groupRepository.findById(teacherDto.getGroupId());
        teacherToBeAdded.setGroup(teacherGroup.get());
        return teacherRepository.save(teacherToBeAdded);
    }

}
