package kindergarten.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum EUserRole {
    ADMIN("ADMIN"), PARENT("PARENT");

    private String role;

}
