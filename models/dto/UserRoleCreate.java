package techno.be.agencesoeur.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class UserRoleCreate {
    //private User user;
    //private Role role;
    private LocalDate dateDeb;
    private LocalDate dateFin;

}
