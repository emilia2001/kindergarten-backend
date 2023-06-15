package kindergarten.management.service;

import kindergarten.management.exceptions.RequestException;
import kindergarten.management.mapper.ExtensionRequestMapper;
import kindergarten.management.model.dto.request.extension.ExtensionRequestParentDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.ExtensionRequest;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.enums.EChildStatus;
import kindergarten.management.model.enums.ERequestStatus;
import kindergarten.management.repository.ChildrenRepository;
import kindergarten.management.repository.ExtensionRequestRepository;
import kindergarten.management.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ExtensionRequestServiceImpl implements ExtensionRequestService {

    ExtensionRequestRepository requestRepository;
    ChildrenRepository childrenRepository;
    GroupRepository groupRepository;
    ExtensionRequestMapper requestMapper;

    @Override
    public void addRequest(ExtensionRequestParentDto requestDto) throws RequestException {
        ExtensionRequest requestToBeAdded = requestMapper.toEntity(requestDto);
        Child child = requestToBeAdded.getChild();
        if (requestRepository.findByChild(requestToBeAdded.getChild()) != null) {
            throw new RequestException("Mai există o cerere pentru același copil");
        }
        Parent parent = new Parent();
        parent.setId(requestDto.getChild().getParentId());
        child.setParent(parent);
        child.setStatus(EChildStatus.PENDING);
        childrenRepository.save(child);
        requestRepository.save(requestToBeAdded);
    }

    @Override
    public List<ExtensionRequestParentDto> findAllRequestsByParent(Long id) {
        return requestMapper.toParentDtos(requestRepository.findAllByChildParentIdOrderByIdDesc(id));
    }

    @Override
    public ExtensionRequestParentDto findOneById(Long id) {
        return requestMapper.toParentDto(requestRepository.getById(id));
    }

    @Override
    public List<ExtensionRequestParentDto> findAllRequests() {
        return requestMapper.toParentDtos(requestRepository.findAll());
    }

    @Override
    public void updateRequestByAdmin(ExtensionRequestParentDto requestDto) throws RequestException {
        Long groupId = requestDto.getChild().getGroup().getId();
        int groupCapacity = groupRepository.findById(groupId).get().getCapacity();
        int unavailableSpots = childrenRepository.countByGroup_IdAndStatus(groupId, EChildStatus.APPROVED);
        if (ERequestStatus.valueOf(requestDto.getStatus()) == ERequestStatus.APPROVED) {
            if (groupCapacity - unavailableSpots == 0) {
                throw new RequestException("Toate locurile sunt ocupate!");
            }
        }
        requestRepository.save(requestMapper.toEntity(requestDto));
    }

    @Override
    public void updateRequestByParent(ExtensionRequestParentDto requestDto) {
        ExtensionRequest requestToBeAdded = requestMapper.toEntity(requestDto);
        Parent parent = new Parent();
        parent.setId(requestDto.getChild().getParentId());
        Child child = requestToBeAdded.getChild();
        child.setParent(parent);
        child.setStatus(EChildStatus.PENDING);
        childrenRepository.save(child);
        requestRepository.save(requestToBeAdded);
    }
}
