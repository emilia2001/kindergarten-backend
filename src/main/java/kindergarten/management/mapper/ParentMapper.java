package kindergarten.management.mapper;

import kindergarten.management.model.dto.ParentDto;
import kindergarten.management.model.entity.Parent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    ParentDto toDto(Parent parent);
    List<ParentDto> toDto(List<Parent> parent);
}
