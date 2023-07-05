package kindergarten.management.service;

import kindergarten.management.exceptions.RequestException;
import kindergarten.management.mapper.ChildrenMapper;
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
        if (requestRepository.findByChild(requestToBeAdded.getChild()) != null) {
            throw new RequestException("Mai există o cerere pentru același copil");
        }
        Child childRequest = requestToBeAdded.getChild();
        Child current = childrenRepository.getById(childRequest.getCnp());
        current.setPicturePath(childRequest.getPicturePath());
        current.setGroup(childRequest.getGroup());
        current.setStatus(EChildStatus.PENDING);
        childrenRepository.save(current);
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
        Child child = childrenRepository.getById(requestDto.getChild().getCnp());
        child.setStatus(getChildStatusFromRequest(ERequestStatus.valueOf(requestDto.getStatus())));
        requestRepository.save(requestMapper.toEntity(requestDto));
    }

    private EChildStatus getChildStatusFromRequest(ERequestStatus status) {
        return status == ERequestStatus.APPROVED ? EChildStatus.APPROVED : (status == ERequestStatus.REJECTED ? EChildStatus.REJECTED : EChildStatus.PENDING);
    }

    @Override
    public void updateRequestByParent(ExtensionRequestParentDto requestDto) {
        ExtensionRequest requestToBeAdded = requestMapper.toEntity(requestDto);
        Child childRequest = requestToBeAdded.getChild();
        Child current = childrenRepository.getById(childRequest.getCnp());
        current.setPicturePath(childRequest.getPicturePath());
        current.setGroup(childRequest.getGroup());
        current.setStatus(EChildStatus.PENDING);
        childrenRepository.save(current);
        requestRepository.save(requestToBeAdded);
    }
}
