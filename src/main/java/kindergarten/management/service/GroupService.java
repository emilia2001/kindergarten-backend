package kindergarten.management.service;

import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.model.dto.teacher.TeacherDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    List<GroupDto> findAllGroups();

}
