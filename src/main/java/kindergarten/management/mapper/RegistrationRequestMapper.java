package kindergarten.management.mapper;

import kindergarten.management.model.dto.request.registration.RegistrationRequestAdminDto;
import kindergarten.management.model.dto.request.registration.RegistrationRequestParentDto;
import kindergarten.management.model.entity.RegistrationRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChildrenMapper.class)
public interface RegistrationRequestMapper {

    RegistrationRequest toEntity(RegistrationRequestParentDto requestDto);

    RegistrationRequestParentDto toParentDto(RegistrationRequest request);
    
    RegistrationRequestAdminDto toAdminDto(RegistrationRequest request);

    List<RegistrationRequestParentDto> toParentDtos(List<RegistrationRequest> requests);

}
