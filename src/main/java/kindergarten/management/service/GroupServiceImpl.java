package kindergarten.management.service;

import kindergarten.management.mapper.GroupMapper;
import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Override
    public List<GroupDto> findAllGroups() {
        return groupMapper.entitiesToDtos(groupRepository.findAll());
    }
}
