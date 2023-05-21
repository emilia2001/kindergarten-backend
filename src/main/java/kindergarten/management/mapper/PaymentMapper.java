package kindergarten.management.mapper;

import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.entity.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChildrenMapper.class)
public interface PaymentMapper {

    PaymentDto toDto(Payment payment);

    Payment toEntity(PaymentDto paymentDto);

    List<PaymentDto> toDtos(List<Payment> payments);

    List<Payment> toEntities(List<PaymentDto> paymentDtos);

}
