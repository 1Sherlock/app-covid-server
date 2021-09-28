package uz.rootec.appcovidserver.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import uz.rootec.appcovidserver.entity.enums.RoleName;

import javax.persistence.*;

@Data
@Entity(name = "role")
public class Role implements GrantedAuthority {
    @Id
    private Integer id;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName name;

    private String description;

    @Override
    public String getAuthority() {
        return this.name.toString();
    }
}
