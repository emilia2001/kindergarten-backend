package kindergarten.management.model.dto.child;

import kindergarten.management.model.dto.GroupDto;
import kindergarten.management.model.dto.ParentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildDto {

    private String cnp;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String picture;

    private GroupDto group;

    private ParentDto parent;
}
