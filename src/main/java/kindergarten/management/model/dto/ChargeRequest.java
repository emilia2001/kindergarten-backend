package kindergarten.management.model.dto;

import lombok.Data;

@Data
public class ChargeRequest {
    private long paymentId;
    private int amount;
    private String token;
}
