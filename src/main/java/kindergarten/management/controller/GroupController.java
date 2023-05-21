package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.model.dto.GroupSpotsDto;
import kindergarten.management.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.GROUP)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class GroupController {
    private final GroupService groupService;

    @GetMapping(value = Endpoints.GET_ALL, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupDto>> getGroups() {
        return ResponseEntity.ok(groupService.findAllGroups());
    }

    @GetMapping(value = Endpoints.GET_SPOTS_COUNT_BY_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupSpotsDto> getUnavailableSpotsCount(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(groupService.findSpotsInformatiom(id));
    }

}
