package kindergarten.management.mapper;

import kindergarten.management.model.dto.child.ChildAddDto;
import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.model.dto.child.ChildPaymentDto;
import kindergarten.management.model.entity.Child;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParentMapper.class, GroupMapper.class})
public interface ChildrenMapper {

    Child toEntity(ChildDto childDto);

    Child toEntityFromAddDto(ChildAddDto childDto);

    ChildDto toDto(Child child);

    Child toEntityFromChildPaymentDto(ChildPaymentDto childPaymentDto);

    List<ChildDto> toDtos(List<Child> child);

    ChildPaymentDto toPaymentDto(Child child);

}
