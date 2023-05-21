package kindergarten.management.model.dto.child;

import kindergarten.management.model.dto.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildAddDto {

    private String cnp;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String picturePath;

    private GroupDto group;

    private Long parentId;
}
