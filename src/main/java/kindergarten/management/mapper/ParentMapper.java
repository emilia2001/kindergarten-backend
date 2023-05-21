package kindergarten.management.mapper;

import kindergarten.management.model.dto.parent.ParentAddDto;
import kindergarten.management.model.entity.Parent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    ParentAddDto toDto(Parent parent);

    Parent toEntity(ParentAddDto parentAddDto);

    List<ParentAddDto> toDto(List<Parent> parent);
}
