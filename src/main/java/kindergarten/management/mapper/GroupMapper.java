package kindergarten.management.mapper;

import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.model.entity.Group;
import kindergarten.management.model.enums.EGroupType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupDto groupDto);

    @Mapping(source = "groupType", target = "groupType", qualifiedByName = "enumToString")
    GroupDto toDto(Group group);

    List<GroupDto> entitiesToDtos(List<Group> entities);

    @Named("enumToString")
    static String enumToString(EGroupType type) {
        return type.getType();
    }
}
