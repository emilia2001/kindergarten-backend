package kindergarten.management.service;

import kindergarten.management.mapper.GroupMapper;
import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.model.dto.GroupSpotsDto;
import kindergarten.management.model.enums.EChildStatus;
import kindergarten.management.repository.ChildrenRepository;
import kindergarten.management.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ChildrenRepository childrenRepository;
    private final GroupMapper groupMapper;

    @Override
    public List<GroupDto> findAllGroups() {
        return groupMapper.entitiesToDtos(groupRepository.findAll());
    }

    @Override
    public GroupSpotsDto findSpotsInformatiom(Long groupId) {
        GroupSpotsDto spotsDto = new GroupSpotsDto();
        spotsDto.setAvailableCount(groupRepository.findById(groupId).get().getCapacity());
        spotsDto.setUnavailableCount(childrenRepository.countByGroup_IdAndStatus(groupId, EChildStatus.APPROVED));
        spotsDto.setPendingCount(childrenRepository.countByGroup_IdAndStatus(groupId, EChildStatus.PENDING));
        return spotsDto;
    }

}
