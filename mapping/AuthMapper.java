package techno.be.agencesoeur.mapping;

import org.springframework.stereotype.Component;
import techno.be.agencesoeur.models.dto.AuthentificationDTO;
import techno.be.agencesoeur.models.entities.User;

@Component
public class AuthMapper {

    public User toEntity(AuthentificationDTO dto){
        User entity = new User();
        entity.setUserName(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public AuthentificationDTO toDto(User entity){
        AuthentificationDTO authentificationDTO= new AuthentificationDTO();
        authentificationDTO.setUsername(entity.getUsername());
        authentificationDTO.setPassword(entity.getPassword());
        return authentificationDTO;
    }
}
