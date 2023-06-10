package kindergarten.management.service.email;

import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Payment;
import kindergarten.management.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class EmailSenderService {

    private JavaMailSender mailSender;
    private PaymentRepository paymentRepository;

    public void sendEmail() {
        YearMonth yearMonth = YearMonth.now();
        List<Payment> payments = paymentRepository.findAllByMonth(yearMonth);
        yearMonth = yearMonth.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
        String formattedYearMonth = yearMonth.format(formatter);
        payments.forEach(payment -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("emilia.boamba@gmail.com");
            Child child = payment.getChild();
            message.setTo(child.getParent().getEmail());
            message.setSubject("Plată grădiniță");
            if (payment.getTotalUnpaidAmount() == 0) {
                message.setText(getMessageForNoAmount(child, formattedYearMonth));
            } else {
                message.setText(getMessageForAmount(payment, formattedYearMonth));
            }
            mailSender.send(message);
        });
    }

    public String getMessageForNoAmount(Child child, String month) {
        return "Vă anunțăm ca nu aveti nimic de achitat pe luna " + month + " pentru " + child.getLastName() + " " + child.getFirstName();
    }

    public String getMessageForAmount(Payment payment, String month) {
        String text = "Vă anunțăm ca aveți de achitat " + payment.getTotalUnpaidAmount() + " RON pe luna " + month + " pentru " + payment.getChild().getLastName() + " " + payment.getChild().getFirstName()+ ".\n";
        text += "Sumă restantă: " + payment.getOutstandingAmount() + " RON" + "\n";
        text += "Sumă curentă: " + payment.getCurrentAmount() + " RON" + "\n";
        text += "\n";
        text += "Plata poate fi efectuată la sediul grădiniței sau online în contul creat.";
        return text;
    }
}
