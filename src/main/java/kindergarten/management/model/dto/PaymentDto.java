package kindergarten.management.model.dto;

import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.model.dto.child.ChildPaymentDto;
import kindergarten.management.model.enums.EPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Long id;

    private ChildPaymentDto child;

    private YearMonth month;

    private Integer outstandingAmount;

    private Integer currentAmount;

    private Integer totalAmount;

    private String status;
}
