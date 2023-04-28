package kindergarten.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EPaymentStatus {
    UNPAID("NEPLATITA"), PAID("PLATITA");

    private String status;
}
