package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.AnnouncementDto;
import kindergarten.management.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.ANNOUNCEMENT)
@CrossOrigin()
@AllArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping(value = Endpoints.GET_ALL, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnnouncementDto>> getAll() {
        return ResponseEntity.ok(announcementService.findAllAnnouncements());
    }

    @PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
    @PostMapping(value = Endpoints.ADD, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(final @RequestBody AnnouncementDto announcementDto) {
        try {
            announcementService.addAnnouncement(announcementDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
    @DeleteMapping(value = Endpoints.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
        try {
            announcementService.deleteAnnouncement(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
    @PutMapping(value = Endpoints.UPDATE, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody final AnnouncementDto announcementDto) {
        try {
            announcementService.addAnnouncement(announcementDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = Endpoints.GET_BY_ID, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AnnouncementDto> getOneById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(announcementService.findOneById(id));
    }
}
