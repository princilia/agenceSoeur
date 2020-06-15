package techno.be.agencesoeur.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
public class PaquetsDTO {

    private Long idPaquets;

    private PaquetsClientEnvoieDTO clientEnvoie;
    private PaquetsClientReceptionneDTO clientReceptionne;

    private UserPaquetsDTO user;
    private String numeropack;
    private String description;
    private String volume;
    private boolean etatPaye;
    private String chargement;

    private String destination;
    private LocalDate dateArriver;
    private LocalDate dateEnvoie;
    private LocalDate dateReception;
}
