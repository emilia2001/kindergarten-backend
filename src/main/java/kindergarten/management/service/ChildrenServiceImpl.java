package kindergarten.management.service;

import kindergarten.management.mapper.ChildrenMapper;
import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.repository.ChildrenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChildrenServiceImpl implements ChildrenService {

    private final ChildrenRepository childrenRepository;
    private final ChildrenMapper childrenMapper;

    @Override
    public List<ChildDto> getAll() {
        return childrenMapper.toDtos(childrenRepository.findAll());
    }

    @Override
    public void addChild(final ChildDto childDto) {
        childrenRepository.save(childrenMapper.toEntity(childDto));
    }
}
