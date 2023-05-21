package kindergarten.management.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name="registration_request")
@EqualsAndHashCode(callSuper=false)
public class RegistrationRequest extends Request {

    @Column(name = "application_form")
    private String applicationForm;

    @Column(name = "parent_identity_card")
    private String parentIdentityCard;

    @Column(name = "extra_documents")
    private String extraDocuments;

    @Column(name = "child_birth_certificate")
    private String childBirthCertificate;

    @Column(name = "parents_employee_certificates")
    private String parentsEmployeeCertificates;

}
