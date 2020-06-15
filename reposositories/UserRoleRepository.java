package techno.be.agencesoeur.reposositories;

import org.springframework.data.jpa.repository.JpaRepository;

import techno.be.agencesoeur.models.entities.UserRoleID;
import techno.be.agencesoeur.models.entities.UserRoles;


public interface UserRoleRepository extends JpaRepository<UserRoles, UserRoleID> {

}
