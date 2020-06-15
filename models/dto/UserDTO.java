package techno.be.agencesoeur.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Data
public class UserDTO {
    private Long id;

    @NotBlank(message = "name is mandatory")
    private String nom;

    @NotBlank(message = "Username is mandatory")
    private String username;

    private String email;

    @NotBlank(message="A password is required")
    private String password;



    private Set<UserRoleDTO> userRolesDTO=new HashSet<>();
    private Set<UserGroupeDTO> userGroupeDTO= new HashSet<>();

}
