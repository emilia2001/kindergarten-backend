package kindergarten.management.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name="registration_request")
@EqualsAndHashCode(callSuper=false)
public class RegistrationRequest extends Request {

    @Column(name = "application_form")
    private byte[] application_form;

    @Column(name = "parent_identity_card")
    private byte[] parentIdentityCard;

    @Column(name = "extra_documents")
    private byte[] extraDocuments;

    @Column(name = "child_birth_certificate")
    private byte[] childBirthCertificate;

    @Column(name = "parents_employee_certificates")
    private byte[] parentsEmployeeCertificates;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}
