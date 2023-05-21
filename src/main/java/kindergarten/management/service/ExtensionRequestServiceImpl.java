package kindergarten.management.service;

import kindergarten.management.mapper.ExtensionRequestMapper;
import kindergarten.management.model.dto.request.extension.ExtensionRequestParentDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.ExtensionRequest;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.enums.EChildStatus;
import kindergarten.management.repository.ChildrenRepository;
import kindergarten.management.repository.ExtensionRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ExtensionRequestServiceImpl implements ExtensionRequestService {

    ExtensionRequestRepository requestRepository;
    ChildrenRepository childrenRepository;
    ExtensionRequestMapper requestMapper;

    @Override
    public void addRequest(ExtensionRequestParentDto requestDto) {
        ExtensionRequest requestToBeAdded = requestMapper.toEntity(requestDto);
        Child child = requestToBeAdded.getChild();
        Parent parent = new Parent();
        parent.setId(requestDto.getChild().getParentId());
        child.setParent(parent);
        child.setStatus(EChildStatus.PENDING);
        childrenRepository.save(child);
        requestRepository.save(requestToBeAdded);
    }

    @Override
    public List<ExtensionRequestParentDto> findAllRequestsByParent(Long id) {
        return requestMapper.toParentDtos(requestRepository.findAllByChildParentId(id));
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
    public void updateRequest(ExtensionRequestParentDto requestDto) {
        requestRepository.save(requestMapper.toEntity(requestDto));
    }
}
