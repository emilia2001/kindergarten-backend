package kindergarten.management.service;

import kindergarten.management.mapper.ChildrenMapper;
import kindergarten.management.mapper.ParentMapper;
import kindergarten.management.model.dto.parent.ParentAddDto;
import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.enums.EChildStatus;
import kindergarten.management.repository.ChildrenRepository;
import kindergarten.management.repository.ParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChildrenServiceImpl implements ChildrenService {

    private final ChildrenRepository childrenRepository;
    private final ParentRepository parentRepository;
    private final ChildrenMapper childrenMapper;
    private final ParentMapper parentMapper;

    @Override
    public List<ChildDto> findAllChildren() {
        return childrenMapper.toDtos(childrenRepository.findAllByStatus(EChildStatus.APPROVED));
    }

    @Override
    public void addChild(final ChildDto childDto) {
        ParentAddDto parentAddDto = childDto.getParent();
        Parent parent = parentRepository.findByPhoneNumber(parentAddDto.getPhoneNumber());
        Child childToBeAdded = childrenMapper.toEntity(childDto);
        childToBeAdded.setStatus(EChildStatus.APPROVED);
        if (parent != null) {
            childToBeAdded.setParent(parent);
        } else {
            parent = parentMapper.toEntity(parentAddDto);
            childToBeAdded.setParent(parentRepository.save(parent));
        }
        childrenRepository.save(childToBeAdded);
    }

    @Override
    public void updateChild(ChildDto childDto) {
        ParentAddDto parentAddDto = childDto.getParent();
        Parent parent = parentRepository.findByPhoneNumber(parentAddDto.getPhoneNumber());
        Child childToBeAdded = childrenMapper.toEntity(childDto);
        childToBeAdded.setStatus(EChildStatus.APPROVED);
        childToBeAdded.setParent(parent);
        childrenRepository.save(childToBeAdded);
    }

    @Override
    public ChildDto findOneById(String cnp) {
        return childrenMapper.toDto(childrenRepository.getById(cnp));
    }

    @Override
    public void deleteChild(String cnp) {

    }
}
