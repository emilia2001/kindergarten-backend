package kindergarten.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EGroupType {
    JUNIOR("MICA"), MIDDLE("MIJLOCIE"), SENIOR("MARE");

    private String type;

}
