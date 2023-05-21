package kindergarten.management.service;

import kindergarten.management.mapper.RegistrationRequestMapper;
import kindergarten.management.model.dto.request.registration.RegistrationRequestParentDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.entity.RegistrationRequest;
import kindergarten.management.model.enums.EChildStatus;
import kindergarten.management.repository.ChildrenRepository;
import kindergarten.management.repository.RegistrationRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationRequestServiceImpl implements RegistrationRequestService{

    RegistrationRequestRepository requestRepository;
    ChildrenRepository childrenRepository;
    RegistrationRequestMapper requestMapper;

    @Override
    public void addRequest(RegistrationRequestParentDto requestDto) {
        RegistrationRequest requestToBeAdded = requestMapper.toEntity(requestDto);
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
        return requestMapper.toParentDtos(requestRepository.findAllByChildParentId(id));
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
    public void updateRequest(RegistrationRequestParentDto requestDto) {
        requestRepository.save(requestMapper.toEntity(requestDto));
    }
}
