package techno.be.agencesoeur.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserRoleDTO {
    private Long user;
    private Long role;
    private LocalDate dateDeb;
    private LocalDate dateFin;


}
