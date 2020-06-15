package techno.be.agencesoeur.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import techno.be.agencesoeur.models.dto.*;
import techno.be.agencesoeur.models.entities.Groupe;
import techno.be.agencesoeur.models.entities.User;
import techno.be.agencesoeur.models.entities.UserRoles;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

   private ModelMapper modelMapper;
   private PasswordEncoder passwordEncoder;

   @Autowired
   public UserMapper(ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
       this.modelMapper =modelMapper;
       this.passwordEncoder= passwordEncoder;
   }


    public User CreateUser(UserCreateDTO userCreateDTO) {
        User u = new User();
        u.setNom(userCreateDTO.getNom());
        u.setUserName(userCreateDTO.getUsername());
        u.setPassword(userCreateDTO.getPassword());
        u.setEmail(userCreateDTO.getEmail());


//        UserRoles userRoles = userRoleRepository.findAll()
//                    .stream()
//                    .filter(role -> role.getRole().equals("Role_Admin"))
//                    .
//            u.setRoles(userRoles);

            return u;


    }

    public UserDTO toDto(User entity){

        // TODO Auto-generated method stub
        UserDTO dto = new UserDTO();
        dto.setId(entity.getIdUser());
        dto.setNom(entity.getNom());
        dto.setUsername(entity.getUsername());
        dto.setPassword(passwordEncoder.encode(entity.getPassword()));
        dto.setEmail(entity.getEmail());
        Set<UserGroupeDTO> groups = entity.getGroupes().stream().map(gr -> modelMapper.map(gr, UserGroupeDTO.class)).collect(Collectors.toSet());
        dto.setUserGroupeDTO(groups);
        Set<UserRoleDTO> urDTO = entity.getUserRoles().stream().map(ur->modelMapper.map(ur,UserRoleDTO.class)).collect(Collectors.toSet());
        dto.setUserRolesDTO(urDTO);
        return dto;
    }

    public User toEntity(UserDTO dto) {
        // TODO Auto-generated method stub
        User entity = new User();
        entity.setIdUser(dto.getId());
        entity.setNom(dto.getNom());
        entity.setUserName(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());

       entity.setGroupes(dto.getUserGroupeDTO().stream().map(g ->  modelMapper.map(g, Groupe.class)).collect(Collectors.toSet()));
       entity.setUserRoles(dto.getUserRolesDTO().stream().map(ur->modelMapper.map(ur, UserRoles.class)).collect(Collectors.toSet()));

        return entity;
    }

}
