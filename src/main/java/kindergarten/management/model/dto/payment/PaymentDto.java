package kindergarten.management.model.dto.payment;

import kindergarten.management.model.dto.child.ChildPaymentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer totalUnpaidAmount;

    private String status;
}
