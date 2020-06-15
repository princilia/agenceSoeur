package techno.be.agencesoeur.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@EqualsAndHashCode
public class UserCreateDTO {

    @NotBlank(message = "name is mandatory")
    private String nom;

    @NotBlank(message = "Username is mandatory")
    private String username;
    private String email;

    @NotBlank(message="A password is required")
    private String password;


}
