package kindergarten.management.service;

import kindergarten.management.mapper.PaymentMapper;
import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentDto> findAllForMonth(String month) {
        YearMonth yearMonth = YearMonth.parse(month);

        return paymentMapper.toDtos(paymentRepository.findAllByMonth(yearMonth));
    }

    @Override
    public Payment updatePayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toEntity(paymentDto);
        return paymentRepository.save(payment);
    }
}
