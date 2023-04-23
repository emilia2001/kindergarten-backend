package kindergarten.management.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="attendance")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@IdClass(AttendanceId.class)
public class Attendance {

    @Id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", referencedColumnName="cnp")
    private Child child;

    @Id
    private String month;

    @Column(name = "no_attendances")
    private Integer noAttendances;

}
