package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.PaymentDto;
import kindergarten.management.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.PAYMENT)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(value = Endpoints.GET_ALL_PAYMENTS_BY_MONTH, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDto>> getAllForMonth(@PathVariable("month") final String month) {
        return ResponseEntity.ok(paymentService.findAllForMonth(month));
    }

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
}
