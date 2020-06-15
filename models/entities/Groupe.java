package techno.be.agencesoeur.models.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@Table(name ="Groupe")
public class Groupe  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdGroupe;
    @Column(unique = true, nullable = false)
    private String label;
    private String description;

    @ManyToMany(targetEntity= User.class,mappedBy = "groupes")
    private Set<User> users = new HashSet<>();

    @ManyToMany(targetEntity= Role.class,mappedBy = "groupes")
    private Set<Role> roles = new HashSet<>();


}
