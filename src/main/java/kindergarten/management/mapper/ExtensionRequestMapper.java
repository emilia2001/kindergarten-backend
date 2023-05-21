package kindergarten.management.mapper;

import kindergarten.management.model.dto.request.extension.ExtensionRequestAdminDto;
import kindergarten.management.model.dto.request.extension.ExtensionRequestParentDto;
import kindergarten.management.model.entity.ExtensionRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChildrenMapper.class)
public interface ExtensionRequestMapper {

    ExtensionRequest toEntity(ExtensionRequestParentDto requestDto);

    ExtensionRequestParentDto toParentDto(ExtensionRequest request);

    ExtensionRequestAdminDto toAdminDto(ExtensionRequest request);

    List<ExtensionRequestParentDto> toParentDtos(List<ExtensionRequest> requests);
}
