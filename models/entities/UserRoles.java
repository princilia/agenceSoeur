package techno.be.agencesoeur.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "user_roles")
@IdClass(UserRoleID.class)
public class UserRoles {

    @Id
    @ManyToOne (optional = false)
    @JoinColumn(name = "idUser")
    private User user;

    @Id
    @ManyToOne(optional = false)
    //@JoinColumn(unique = true)
    private Role role;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeb;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;
}
