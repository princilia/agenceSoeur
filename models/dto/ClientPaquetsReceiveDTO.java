package techno.be.agencesoeur.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class ClientPaquetsReceiveDTO {

    private String description;
    private boolean etatPaye;
    private LocalDate dateReception;
    //private PaquetsClientReceptionneDTO receive;
}
