package kindergarten.management.service;

import kindergarten.management.mapper.ChildrenMapper;
import kindergarten.management.mapper.ParentMapper;
import kindergarten.management.model.dto.ParentDto;
import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Parent;
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
        return childrenMapper.toDtos(childrenRepository.findAll());
    }

    @Override
    public void addChild(final ChildDto childDto) {
        ParentDto parentDto = childDto.getParent();
        Parent parent = parentRepository.findByPhoneNumber(parentDto.getPhoneNumber());
        Child childToBeAdded = childrenMapper.toEntity(childDto);
        if (parent != null) {
            childToBeAdded.setParent(parent);
        } else {
            parent = parentMapper.toEntity(parentDto);
            childToBeAdded.setParent(parentRepository.save(parent));
        }
        childrenRepository.save(childToBeAdded);
    }

    @Override
    public ChildDto findOneById(String cnp) {
        return childrenMapper.toDto(childrenRepository.getById(cnp));
    }
}
