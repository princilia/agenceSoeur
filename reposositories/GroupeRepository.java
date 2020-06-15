package techno.be.agencesoeur.reposositories;

import org.springframework.data.jpa.repository.JpaRepository;
import techno.be.agencesoeur.models.entities.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {
}
