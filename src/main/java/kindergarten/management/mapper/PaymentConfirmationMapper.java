package kindergarten.management.mapper;

import kindergarten.management.model.dto.PaymentConfirmationDto;
import kindergarten.management.model.entity.PaymentConfirmation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentConfirmationMapper {

    List<PaymentConfirmationDto> toDtos(List<PaymentConfirmation> payments);

    @Mapping(target = "paymentId", source = "payment.id")
    PaymentConfirmationDto toDto(PaymentConfirmation paymentConfirmation);

    @Mapping(target = "payment.id", source = "paymentId")
    PaymentConfirmation toEntity(PaymentConfirmationDto paymentConfirmationDto);
}
