package kindergarten.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ERequestType {
    REGISTRATION("REGISTRATION"), EXTENSION("EXTENSION");

    private String type;
}
