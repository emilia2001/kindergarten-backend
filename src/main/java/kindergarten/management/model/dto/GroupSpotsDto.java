package kindergarten.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSpotsDto {

    Integer availableCount;
    Integer unavailableCount;
    Integer pendingCount;
}
