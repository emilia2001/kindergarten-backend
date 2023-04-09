package kindergarten.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum EUserRole {
    ADMIN("ADMIN"), PARENT("TEACHER");

    private String role;

}
