package kindergarten.management.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.ChargeRequest;
import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.model.dto.PaymentStatusDto;
import kindergarten.management.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.PAYMENT)
@CrossOrigin()
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
    @GetMapping(value = Endpoints.GET_ALL_PAYMENTS_BY_MONTH, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDto>> getAllForMonth(@PathVariable("month") final String month) {
        return ResponseEntity.ok(paymentService.findAllForMonth(month));
    }

    @PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
    @PutMapping(value = Endpoints.UPDATE_PAYMENT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @RequestBody final PaymentDto paymentDto) {
        try {
            paymentDto.setId(id);
            paymentService.updatePayment(paymentDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("isAuthenticated() && hasAuthority('PARENT')")
    @GetMapping(value = Endpoints.GET_ALL_PAYMENTS_BY_PARENT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDto>> getAllForMonth(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(paymentService.findAllForParent(id));
    }

    @PreAuthorize("isAuthenticated() && hasAuthority('PARENT')")
    @PostMapping(value = "/charge", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentStatusDto> charge(@RequestBody ChargeRequest chargeRequest)
            throws StripeException {
        Stripe.apiKey = "sk_test_51N2F4DBquZgmE331IWNgu36doCouc1wFi1cg8QnuoXlYfLDNoFc9lp9VGDG8qGvHSpByzp7e4YQLw1qAHdeuvGpV00I78eECzS";

        Map<String, Object> params = new HashMap<>();
        params.put("amount", chargeRequest.getAmount());
        params.put("currency", "RON");
        params.put("source", chargeRequest.getToken());
        params.put(
                "description",
                "My First Test Charge (created for API docs at https://www.stripe.com/docs/api)"
        );
        try {
            Charge charge = Charge.create(params);
            paymentService.updatePaymentStatus(chargeRequest.getPaymentId(), charge.getStatus());
            return new ResponseEntity<>(new PaymentStatusDto(charge.getStatus()), HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>(new PaymentStatusDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
