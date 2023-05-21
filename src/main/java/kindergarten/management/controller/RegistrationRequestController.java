package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.request.registration.RegistrationRequestParentDto;
import kindergarten.management.service.RegistrationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.REGISTRATION_REQUEST)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RegistrationRequestController {
    RegistrationRequestService requestService;

    @PreAuthorize("hasAuthority('PARENT')")
    @PostMapping(value = Endpoints.ADD, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@RequestBody final RegistrationRequestParentDto requestDto) {
        try {
            requestService.addRequest(requestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = Endpoints.UPDATE, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody final RegistrationRequestParentDto requestDto) {
        try {
            requestService.updateRequest(requestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = Endpoints.GET_ALL_BY_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegistrationRequestParentDto>> getRequestsForParent(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(requestService.findAllRequestsByParent(id));
    }

    @GetMapping(value = Endpoints.GET_ALL, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegistrationRequestParentDto>> getRequests() {
        return ResponseEntity.ok(requestService.findAllRequests());
    }

    @GetMapping(value = Endpoints.GET_BY_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationRequestParentDto> getOneById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(requestService.findOneById(id));
    }
}
