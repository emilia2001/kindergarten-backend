package kindergarten.management.model.dto;

import kindergarten.management.model.enums.EGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private long id;

    private String name;

    private String groupType;
}
