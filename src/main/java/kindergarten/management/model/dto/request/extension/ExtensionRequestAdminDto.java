package kindergarten.management.model.dto.request.extension;

import kindergarten.management.model.entity.Child;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionRequestAdminDto {

    private Long id;

    private String applicationForm;

    private String comments;

    private String status;

    private Child child;
}