package kindergarten.management.service.job;

import kindergarten.management.mapper.ChildrenMapper;
import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.service.ChildrenService;
import kindergarten.management.service.PaymentService;
import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.model.enums.EPaymentStatus;
import kindergarten.management.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CreatePaymentsJob {

    private final PaymentService paymentService;
    private final AttendanceService attendanceService;
    private final ChildrenService childrenService;
    private final ChildrenMapper childrenMapper;

//    @Scheduled(fixedRate = 30 * 10000)
    public void createAttendances() {
        YearMonth yearMonth = YearMonth.now().minusMonths(1);
        Map<String, Long> previousMonthAttendances = attendanceService.findAllNoAttendancesForMonth(yearMonth.toString());
        List<PaymentDto> previousMonthPayments = paymentService.findAllForMonth(yearMonth.toString());
        List<ChildDto> children = childrenService.findAllChildren();
        List<Payment> paymentsCurrentMonth = new ArrayList<>();
        children.forEach(child -> {
            Payment payment = new Payment();
            payment.setChild(childrenMapper.toEntity(child));
            // Setting the current payment based on attendances
            if (previousMonthAttendances.get(child.getCnp()) != null)
                payment.setCurrentAmount(Math.toIntExact(previousMonthAttendances.get(child.getCnp())) * 15);
            else
                payment.setCurrentAmount(0);
            // Get the previous month payment of child
            Optional<PaymentDto> childPreviousMonthPayments = previousMonthPayments.stream().filter(paymentDto -> paymentDto.getChild().getCnp().equals(child.getCnp())).findFirst();
            // If payment, check the status and set outstanding amount
            childPreviousMonthPayments.ifPresent(paymentDto -> {
                if (paymentDto.getStatus().equals(EPaymentStatus.UNPAID.toString()))
                    payment.setOutstandingAmount(paymentDto.getTotalUnpaidAmount());
                else
                    payment.setOutstandingAmount(0);
            });
            // If absent, set outstanding amount to 0
            if (childPreviousMonthPayments.isEmpty())
                payment.setOutstandingAmount(0);
            payment.setMonth(yearMonth.plusMonths(1));
            payment.setTotalUnpaidAmount(payment.getOutstandingAmount() + payment.getCurrentAmount());
            if (payment.getTotalUnpaidAmount() == 0)
                payment.setStatus(EPaymentStatus.PAID);
            else
                payment.setStatus(EPaymentStatus.UNPAID);
            paymentsCurrentMonth.add(payment);
        });
        paymentService.saveAll(paymentsCurrentMonth);
    }
}
