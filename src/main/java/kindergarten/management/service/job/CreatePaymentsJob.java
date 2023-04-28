package kindergarten.management.service.job;

import kindergarten.management.mapper.PaymentMapper;
import kindergarten.management.model.dto.AttendanceDto;
import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.model.enums.EPaymentStatus;
import kindergarten.management.service.AttendanceService;
import kindergarten.management.service.ChildrenService;
import kindergarten.management.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CreatePaymentsJob {

    private final PaymentService paymentService;

    private final AttendanceService attendanceService;

    private final PaymentMapper paymentMapper;

//    @Scheduled(fixedRate = 30 * 1000)
    public void createAttendances() {
        YearMonth yearMonth = YearMonth.now().minusMonths(1);
        Map<String, Long> previousMonthAttendances = attendanceService.findAllNoAttendancesForMonth(yearMonth.toString());
        List<PaymentDto> previousMonthPayments = paymentService.findAllForMonth(yearMonth.toString());
        List<Payment> paymentsCurrentMonth = paymentMapper.toEntities(previousMonthPayments);
        paymentsCurrentMonth.forEach(payment -> {
            if (payment.getStatus() == EPaymentStatus.UNPAID) {
                payment.setOutstandingAmount(payment.getTotalAmount());
            } else {
                payment.setOutstandingAmount(0);
            }
            payment.setCurrentAmount(Math.toIntExact(previousMonthAttendances.get(payment.getChild().getCnp())));
            payment.setTotalAmount(0);
            payment.setStatus(EPaymentStatus.UNPAID);
        });
    }
}
