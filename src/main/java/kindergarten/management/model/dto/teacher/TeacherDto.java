package kindergarten.management.model.dto.teacher;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String description;

    private LocalDate dateOfBirth;

    private byte[] picture;

    private long groupId;
}
