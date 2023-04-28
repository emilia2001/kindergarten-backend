package kindergarten.management.mapper;

import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChildrenMapper.class)
public interface PaymentMapper {

    PaymentDto toDto(Payment payment);

    Payment toEntity(PaymentDto paymentDto);

    List<PaymentDto> toDtos(List<Payment> payments);

    List<Payment> toEntities(List<PaymentDto> paymentDtos);

}
