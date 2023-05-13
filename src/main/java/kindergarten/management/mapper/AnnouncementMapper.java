package kindergarten.management.mapper;

import kindergarten.management.model.dto.AnnouncementDto;
import kindergarten.management.model.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    @Mapping(source = "picture", target = "picture", qualifiedByName = "byteArrayToBase64")
    AnnouncementDto toDto(Announcement announcement);

    @Mapping(source = "picture", target = "picture", qualifiedByName = "base64ToByteArray")
    Announcement toEntity(AnnouncementDto announcementDto);

    List<AnnouncementDto> toDtos(List<Announcement> entities);

    @Named("base64ToByteArray")
    static byte[] base64ToByteArray(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    @Named("byteArrayToBase64")
    static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
