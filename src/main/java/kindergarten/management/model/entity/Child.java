package kindergarten.management.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kindergarten.management.model.enums.EChildStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name="child")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Child {

    @Id
    private String cnp;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "picture")
    private String picturePath;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EChildStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_person_id")
    private Parent parent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @JsonIgnore
    @OneToMany(
            mappedBy = "child",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Attendance> attendances = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "child",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Payment> payments = new ArrayList<>();

}

