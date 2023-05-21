package kindergarten.management.model.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentStatusDto {

    String status;

    PaymentDto payment;
}
