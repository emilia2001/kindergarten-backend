package kindergarten.management.service.job;

import kindergarten.management.service.PaymentService;
import kindergarten.management.mapper.PaymentMapper;
import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.model.enums.EPaymentStatus;
import kindergarten.management.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
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
            payment.setId(null);
            if (payment.getStatus() == EPaymentStatus.UNPAID) {
                payment.setOutstandingAmount(payment.getTotalAmount());
            } else {
                payment.setOutstandingAmount(0);
            }
            payment.setCurrentAmount(Math.toIntExact(previousMonthAttendances.get(payment.getChild().getCnp())) * 15);
            payment.setTotalAmount(0);
            payment.setMonth(yearMonth.plusMonths(1));
            payment.setStatus(EPaymentStatus.UNPAID);
        });
        paymentService.saveAll(paymentsCurrentMonth);
    }
}
