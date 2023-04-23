package kindergarten.management.mapper;

import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.model.entity.Group;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupDto groupDto);

    GroupDto toDto(Group group);

    List<GroupDto> entitiesToDtos(List<Group> entities);
}
