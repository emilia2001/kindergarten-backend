package kindergarten.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private Long id;

    private String childCnp;

    private LocalDate date;
}
