package kindergarten.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EChildStatus {
    PENDING("IN ASTEPARE"), APPROVED("APROBATA"), REJECTED("RESPINSA");

    private String status;
}
