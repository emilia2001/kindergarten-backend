package kindergarten.management.model.entity;

import javax.persistence.*;

import kindergarten.management.model.enums.EUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="admin")
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Admin extends User {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(EUserRole.ADMIN.getRole()));
        return authorityList;
    }

}
