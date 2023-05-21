package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.AttendanceDto;
import kindergarten.management.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.ATTENDANCE)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping(value = Endpoints.GET_ALL_ATTENDANCES_BY_MONTH, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttendanceDto>> getAllForMonth(@PathVariable("month") final String month) {
        return ResponseEntity.ok(attendanceService.findAllForMonth(month));
    }

    @PostMapping(value = Endpoints.ADD)
    public ResponseEntity<Void> saveAttendances(@RequestBody List<AttendanceDto> attendanceDtos) {
        try {
            attendanceService.saveAll(attendanceDtos);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
