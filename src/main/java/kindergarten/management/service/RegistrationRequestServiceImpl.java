package kindergarten.management.service;

import kindergarten.management.exceptions.RequestException;
import kindergarten.management.mapper.RegistrationRequestMapper;
import kindergarten.management.model.dto.request.registration.RegistrationRequestParentDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.entity.RegistrationRequest;
import kindergarten.management.model.enums.EChildStatus;
import kindergarten.management.model.enums.ERequestStatus;
import kindergarten.management.repository.ChildrenRepository;
import kindergarten.management.repository.GroupRepository;
import kindergarten.management.repository.RegistrationRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationRequestServiceImpl implements RegistrationRequestService{

    RegistrationRequestRepository requestRepository;
    ChildrenRepository childrenRepository;
    GroupRepository groupRepository;
    RegistrationRequestMapper requestMapper;

    @Override
    public void addRequest(RegistrationRequestParentDto requestDto) throws RequestException {
        RegistrationRequest requestToBeAdded = requestMapper.toEntity(requestDto);
        if (requestRepository.findByChild(requestToBeAdded.getChild()) != null) {
            throw new RequestException("Mai există o cerere pentru același copil");
        }
        Child childToBeAdded = requestToBeAdded.getChild();
        Parent parent = new Parent();
        parent.setId(requestDto.getChild().getParentId());
        childToBeAdded.setParent(parent);
        childToBeAdded.setStatus(EChildStatus.PENDING);
        childrenRepository.save(childToBeAdded);
        requestRepository.save(requestToBeAdded);
    }

    @Override
    public List<RegistrationRequestParentDto> findAllRequestsByParent(Long id) {
        return requestMapper.toParentDtos(requestRepository.findAllByChildParentIdOrderByIdDesc(id));
    }

    @Override
    public RegistrationRequestParentDto findOneById(Long id) {
        return requestMapper.toParentDto(requestRepository.getById(id));
    }

    @Override
    public List<RegistrationRequestParentDto> findAllRequests() {
        return requestMapper.toParentDtos(requestRepository.findAll());
    }

    @Override
    public void updateRequestByAdmin(RegistrationRequestParentDto requestDto) throws RequestException {
        Long groupId = requestDto.getChild().getGroup().getId();
        int groupCapacity = groupRepository.findById(groupId).get().getCapacity();
        int unavailableSpots = childrenRepository.countByGroup_IdAndStatus(groupId, EChildStatus.APPROVED);
        if (ERequestStatus.valueOf(requestDto.getStatus()) == ERequestStatus.APPROVED) {
            if (groupCapacity - unavailableSpots == 0) {
                throw new RequestException("Toate locurile sunt ocupate!");
            }
        }

        requestRepository.save(requestMapper.toEntity(requestDto));

        Child child = childrenRepository.getById(requestDto.getChild().getCnp());
        if (ERequestStatus.valueOf(requestDto.getStatus()) == ERequestStatus.APPROVED) {
            child.setStatus(EChildStatus.APPROVED);
            childrenRepository.save(child);
        }
        if (ERequestStatus.valueOf(requestDto.getStatus()) == ERequestStatus.REJECTED) {
            child.setStatus(EChildStatus.REJECTED);
            childrenRepository.save(child);
        }
    }

    @Override
    public void updateRequestByParent(RegistrationRequestParentDto requestDto) {
        RegistrationRequest requestToBeAdded = requestMapper.toEntity(requestDto);
        Parent parent = new Parent();
        parent.setId(requestDto.getChild().getParentId());
        Child child = requestToBeAdded.getChild();
        child.setParent(parent);
        child.setStatus(EChildStatus.PENDING);
        childrenRepository.save(child);
        requestRepository.save(requestToBeAdded);
    }
}
