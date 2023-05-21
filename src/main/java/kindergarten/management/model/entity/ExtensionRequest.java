package kindergarten.management.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name="extension_request")
@EqualsAndHashCode(callSuper=false)
public class ExtensionRequest extends Request {

    @Column(name = "application_form")
    private String applicationForm;

}
