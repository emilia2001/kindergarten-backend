package kindergarten.management.model.entity;

import kindergarten.management.model.enums.EGroupType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="children_group")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_type")
    private EGroupType groupType;

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Child> children = new ArrayList<>();

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Teacher> teachers = new ArrayList<>();

}
