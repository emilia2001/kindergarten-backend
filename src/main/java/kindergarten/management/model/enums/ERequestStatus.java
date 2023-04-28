package kindergarten.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ERequestStatus {
    PENDING("IN ASTEPARE"), APPROVED("APROBATA"), REJECTED("RESPINSA"), ONGOING("IN DESFASURARE");

    private String status;
}
