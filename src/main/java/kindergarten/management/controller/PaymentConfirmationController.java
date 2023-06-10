package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.NextIdDto;
import kindergarten.management.model.dto.PaymentConfirmationDto;
import kindergarten.management.service.PaymentConfirmationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.PAYMENT_CONFIRMATION)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PaymentConfirmationController {

    PaymentConfirmationService confirmationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = Endpoints.GET_ALL, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentConfirmationDto>> getAll() {
        return ResponseEntity.ok(confirmationService.findAllPaymentConfirmations());
    }

    @PreAuthorize("hasAuthority('PARENT')")
    @GetMapping(value = Endpoints.GET_ALL_BY_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentConfirmationDto>> getAllForParent(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(confirmationService.findAllPaymentConfirmationsByParent(id));
    }

    @GetMapping(value = Endpoints.NEXT_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<NextIdDto> getNextId() {
        return ResponseEntity.ok(new NextIdDto(confirmationService.findNextId()));
    }

    @PostMapping(value = Endpoints.ADD, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(final @RequestBody PaymentConfirmationDto paymentConfirmationDto) {
        try {
            confirmationService.addPaymentConfirmation(paymentConfirmationDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
