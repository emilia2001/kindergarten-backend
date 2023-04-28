package kindergarten.management.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="attendance")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", referencedColumnName="cnp")
    private Child child;

    @Column(name = "date")
    private LocalDate date;

}
