package kindergarten.management.service;

import kindergarten.management.model.dto.child.ChildDto;

import java.util.List;

public interface ChildrenService {

    List<ChildDto> findAllChildren();

    void addChild(ChildDto childDto);

    void updateChild(ChildDto childDto);

    ChildDto findOneById(String cnp);

    void deleteChild(String cnp);
}
