package kindergarten.management.mapper;

import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.model.entity.Child;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring", uses = ParentMapper.class)
public interface ChildrenMapper {

    @Mapping(source = "picture", target = "picture", qualifiedByName = "base64ToByteArray")
    Child toEntity(ChildDto childDto);

    @Mapping(source = "picture", target = "picture", qualifiedByName = "byteArrayToBase64")
    ChildDto toDto(Child child);
    List<ChildDto> toDtos(List<Child> child);

    @Named("base64ToByteArray")
    static byte[] base64ToByteArray(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    @Named("byteArrayToBase64")
    static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

}
