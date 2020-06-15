package techno.be.agencesoeur.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ClientPaquetsEnvoiDTO {
    private String description;
    private boolean etatPaye;
    private LocalDate dateEnvoie;
    //private PaquetsClientEnvoieDTO envoie;
}
