package kindergarten.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EGroupType {
    JUNIOR("JUNIOR"), MIDDLE("MIDDLE"), SENIOR("SENIOR");

    private String type;

}
