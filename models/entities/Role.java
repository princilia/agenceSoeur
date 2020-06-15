package techno.be.agencesoeur.models.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name="role")
@Entity
@Data

public class Role implements  GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    @Column(unique = true,nullable = false)
    private String label;
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<UserRoles> userRoles = new HashSet<>();

    @ManyToMany(targetEntity = Groupe.class)
    private Set<Groupe> groupes = new HashSet<>();


    @Override
    public String getAuthority() {
        return this.label;
    }
}
