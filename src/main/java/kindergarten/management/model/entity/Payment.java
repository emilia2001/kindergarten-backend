package kindergarten.management.model.entity;

import kindergarten.management.model.enums.EPaymentStatus;
import kindergarten.management.utils.YearMonthAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.YearMonth;

@Data
@Entity
@Table(name="payment")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;

    @Column(name = "month", columnDefinition = "varchar(255)")
    @Convert(converter = YearMonthAttributeConverter.class)
    private YearMonth month;

    @Column(name = "outstanding_amount")
    private Integer outstandingAmount;

    @Column(name = "current_amount")
    private Integer currentAmount;

    @Formula("outstanding_amount + current_amount")
    private Integer totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private EPaymentStatus status;

}
