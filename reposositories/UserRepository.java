package techno.be.agencesoeur.reposositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import techno.be.agencesoeur.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);





//    // ATTENTION !!! JPQL
//    @Query(value = "SELECT u FROM User u WHERE u.nom LIKE :nomValue")
//    User searchByName(@Param(value = "nomValue") String nom);
//
//    @Query(value="SELECT * FROM user WHERE nom LIKE ?", nativeQuery = true)
//    User searByNameNativeQuery(String name);
//
//    @Query(value="SELECT u FROM User u INNER JOIN u.role c WHERE c.id = :idrole")
//    List<User> searchByRole(@Param("idRole") Long id);
//



}
