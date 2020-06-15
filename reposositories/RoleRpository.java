package techno.be.agencesoeur.reposositories;

import org.springframework.data.jpa.repository.JpaRepository;
import techno.be.agencesoeur.models.entities.Role;

public interface RoleRpository extends JpaRepository<Role,Long> {
    Role findByLabel(String label);

}
