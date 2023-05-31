package kindergarten.management.mapper;

import kindergarten.management.model.dto.AdminDto;
import kindergarten.management.model.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toEntity(AdminDto adminDto);
}
