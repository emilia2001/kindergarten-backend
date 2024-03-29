package kindergarten.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDto {

    private Long id;

    private String picturePath;

    private String title;

    private String description;
}
