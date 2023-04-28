package kindergarten.management.controller;

import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.teacher.TeacherAddDto;
import kindergarten.management.model.dto.teacher.TeacherDto;
import kindergarten.management.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Endpoints.TEACHER)
@CrossOrigin()
@AllArgsConstructor
@PreAuthorize("isAuthenticated() && hasAuthority('ADMIN')")
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping(value = Endpoints.GET_ALL_TEACHERS, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    @PostMapping(value = Endpoints.ADD_TEACHER, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity add(final @RequestBody TeacherAddDto teacherAddDto) {
        try {
            teacherService.addTeacher(teacherAddDto);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = Endpoints.GET_TEACHER, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDto> getOneById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(teacherService.findOneById(id));
    }

    @PutMapping(value = Endpoints.UPDATE_TEACHER, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @RequestBody final TeacherAddDto teacherDto) {
        try {
            teacherDto.setId(id);
            teacherService.addTeacher(teacherDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
