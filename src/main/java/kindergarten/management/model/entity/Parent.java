package kindergarten.management.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kindergarten.management.model.enums.EUserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="parent")
@EqualsAndHashCode(callSuper=false)
public class Parent extends User {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(
            mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    @ToString.Exclude
    private List<Child> children = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(EUserRole.PARENT.getRole()));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return super.getUsername() != null ? super.getUsername() : "";
    }

    @Override
    public String getPassword() {
        return super.getPassword() != null ? super.getPassword() : "";
    }
}
