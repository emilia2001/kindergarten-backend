package kindergarten.management.model.dto.request.registration;

import kindergarten.management.model.dto.child.ChildDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestAdminDto {

    private Long id;

    private String applicationForm;

    private String parentIdentityCard;

    private String extraDocuments;

    private String childBirthCertificate;

    private String parentsEmployeeCertificates;

    private String comments;

    private String status;

    private ChildDto child;
}
