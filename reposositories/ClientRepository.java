package techno.be.agencesoeur.reposositories;

import org.springframework.data.jpa.repository.JpaRepository;
import techno.be.agencesoeur.models.entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByNom(String Name);
}
