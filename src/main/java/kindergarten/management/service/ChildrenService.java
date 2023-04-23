package kindergarten.management.service;

import kindergarten.management.model.dto.child.ChildDto;

import java.util.List;

public interface ChildrenService {

    List<ChildDto> getAll();

    void addChild(ChildDto childDto);
}
