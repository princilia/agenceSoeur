package techno.be.agencesoeur.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import techno.be.agencesoeur.EnumDestination;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="Paquets")
public class Paquets  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaquets;


    @ManyToOne
    @JoinColumn (name = "idEnvoyeur")
    private Client clientEnvoie;

    @ManyToOne
    @JoinColumn (name = "idReceptionne")
    private Client clientReceptionne;

    @ManyToOne
    @JoinColumn (name = "idUser" )
    private User user;

    private String numeropack;
    private String description;
    private String volume;
    private boolean etatPaye;
    private String chargement;

    private String destination;
    private LocalDate dateEntree;
    private LocalDate dateEnvoie;
    private LocalDate dateReception;

}
