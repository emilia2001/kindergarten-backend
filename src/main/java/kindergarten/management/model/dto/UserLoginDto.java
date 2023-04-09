package kindergarten.management.model.dto;

import kindergarten.management.model.EUserRole;
import lombok.Data;

@Data
public class UserLoginDto {

    private String username;
    private String password;
    private EUserRole role;

}
