package techno.be.agencesoeur.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
@Getter
@Setter
@EqualsAndHashCode
public class AuthentificationDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message="A password is required")
    private String password;


}
