package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.ExceptionDto;
import kindergarten.management.model.dto.request.extension.ExtensionRequestParentDto;
import kindergarten.management.service.ExtensionRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.EXTENSION_REQUEST)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ExtensionRequestController {

    ExtensionRequestService requestService;

    @PreAuthorize("hasAuthority('PARENT')")
    @PostMapping(value = Endpoints.ADD, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExceptionDto> add(@RequestBody final ExtensionRequestParentDto requestDto) {
        try {
            requestService.addRequest(requestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = Endpoints.UPDATE_BY_ADMIN, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateByAdmin(@RequestBody final ExtensionRequestParentDto requestDto) {
        try {
            requestService.updateRequestByAdmin(requestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = Endpoints.UPDATE_BY_PARENT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateByParent(@RequestBody final ExtensionRequestParentDto requestDto) {
        try {
            requestService.updateRequestByParent(requestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = Endpoints.GET_ALL_BY_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExtensionRequestParentDto>> getRequestsForParent(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(requestService.findAllRequestsByParent(id));
    }

    @GetMapping(value = Endpoints.GET_ALL, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExtensionRequestParentDto>> getRequests() {
        return ResponseEntity.ok(requestService.findAllRequests());
    }

    @GetMapping(value = Endpoints.GET_BY_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExtensionRequestParentDto> getOneById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(requestService.findOneById(id));
    }
}
