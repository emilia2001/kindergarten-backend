package kindergarten.management.controller;

import com.stripe.exception.StripeException;
import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.ChargeRequest;
import kindergarten.management.model.dto.ChargeRequestAdmin;
import kindergarten.management.model.dto.payment.PaymentDto;
import kindergarten.management.model.dto.payment.PaymentStatusDto;
import kindergarten.management.service.PaymentService;
import kindergarten.management.service.email.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.PAYMENT)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PaymentController {

    private final PaymentService paymentService;
    private final EmailSenderService emailSenderService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = Endpoints.GET_ALL_PAYMENTS_BY_MONTH, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDto>> getAllForMonth(@PathVariable("month") final String month) {
        return ResponseEntity.ok(paymentService.findAllForMonth(month));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = Endpoints.UPDATE, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @RequestBody final PaymentDto paymentDto) {
        try {
            paymentDto.setId(id);
            paymentService.updatePayment(paymentDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('PARENT')")
    @GetMapping(value = Endpoints.GET_ALL_PAYMENTS_BY_PARENT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDto>> getAllForMonth(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(paymentService.findAllForParent(id));
    }

    @PreAuthorize("hasAuthority('PARENT')")
    @PostMapping(value = Endpoints.CHARGE, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentStatusDto> charge(@RequestBody ChargeRequest chargeRequest) {
        try {
            PaymentDto paymentDto = paymentService.chargeByParent(chargeRequest);
            return new ResponseEntity<>(new PaymentStatusDto("Success", paymentDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PaymentStatusDto(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = Endpoints.CHARGE_BY_ADMIN, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentStatusDto> chargeByAdmin(@RequestBody ChargeRequestAdmin chargeRequest) {
        try {
            PaymentDto paymentDto = paymentService.updatePaymentStatusByAdmin(chargeRequest.getPaymentId(), chargeRequest.getAmount());
            return new ResponseEntity<>(new PaymentStatusDto("success", paymentDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PaymentStatusDto(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = Endpoints.SEND_EMAIL, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDto>> sendEmail() {
        try {
            emailSenderService.sendEmail();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
