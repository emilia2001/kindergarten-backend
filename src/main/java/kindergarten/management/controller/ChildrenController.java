package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.child.ChildDto;
import kindergarten.management.service.ChildrenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.CHILDREN)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
public class ChildrenController {

    private final ChildrenService childrenService;


    @GetMapping(value = Endpoints.GET_ALL_CHLDREN, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChildDto>> getAll() {
        return ResponseEntity.ok(childrenService.getAll());
    }

    @PostMapping(value = Endpoints.ADD_CHILD, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity add(final ChildDto childDto) {
        try {
            childrenService.addChild(childDto);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
