package kindergarten.management.model.dto.parent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRegisterDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String username;

    private String password;
}
