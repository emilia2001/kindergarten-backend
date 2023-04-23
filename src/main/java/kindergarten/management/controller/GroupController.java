package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.GROUP)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
public class GroupController {
    private final GroupService groupService;

    @GetMapping(value = Endpoints.GET_ALL_GROUPS, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupDto>> getGroups() {
        return ResponseEntity.ok(groupService.findAllGroups());
    }
}
