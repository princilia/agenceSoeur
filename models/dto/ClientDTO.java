package techno.be.agencesoeur.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class ClientDTO {

    private Long idClient;
    private String numeroCli;
    private String nom;
    private String prenom;
    private String adresse;

    @NotBlank(message = "The email is mandatory !")
    @Email(message = "The email is invalid !")
    private String email;
    private String telephone;
    private Set<ClientPaquetsEnvoiDTO> paquetsenvoie = new HashSet<>();
    private Set<ClientPaquetsReceiveDTO> paquetsreceive = new HashSet<>();
    //private Set<UserClientDTO> users = new HashSet<>();


}
