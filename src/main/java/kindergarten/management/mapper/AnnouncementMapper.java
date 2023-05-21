package kindergarten.management.mapper;

import kindergarten.management.model.dto.AnnouncementDto;
import kindergarten.management.model.entity.Announcement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    AnnouncementDto toDto(Announcement announcement);

    Announcement toEntity(AnnouncementDto announcementDto);

    List<AnnouncementDto> toDtos(List<Announcement> entities);

}
