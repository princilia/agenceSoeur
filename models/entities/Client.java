package techno.be.agencesoeur.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Client")
public class Client  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String numeroCli;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String adresse;

    @NotBlank(message = "The email is mandatory !")
    @Email(message = "The email is invalid !")
    private String email;
    private String telephone;

    @ManyToMany(mappedBy = "clientsEnregistrer")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "clientEnvoie")
    private Set<Paquets> paquetsEnvoyer = new HashSet<>();

    @OneToMany(mappedBy = "clientReceptionne")
    private Set<Paquets> paquetsReceptionner = new HashSet<>();


}
