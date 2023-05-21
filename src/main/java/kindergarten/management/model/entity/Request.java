package kindergarten.management.model.entity;

import kindergarten.management.model.enums.ERequestStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ERequestStatus status;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id")
    private Child child;
}
