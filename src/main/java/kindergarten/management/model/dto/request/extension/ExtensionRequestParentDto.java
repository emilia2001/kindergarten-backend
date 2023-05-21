package kindergarten.management.model.dto.request.extension;

import kindergarten.management.model.dto.child.ChildAddDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionRequestParentDto {

    private Long id;

    private String applicationForm;

    private String comments;

    private String status;

    private ChildAddDto child;
}
