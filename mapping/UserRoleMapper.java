package techno.be.agencesoeur.mapping;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import techno.be.agencesoeur.models.dto.UserRoleDTO;
import techno.be.agencesoeur.models.entities.Role;
import techno.be.agencesoeur.models.entities.User;
import techno.be.agencesoeur.models.entities.UserRoles;
import techno.be.agencesoeur.reposositories.RoleRpository;
import techno.be.agencesoeur.reposositories.UserRepository;
import techno.be.agencesoeur.reposositories.UserRoleRepository;

@Component
public class UserRoleMapper {

    private ModelMapper modelMapper;
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private RoleRpository roleRpository;

    @Autowired
    public UserRoleMapper(ModelMapper modelMapper,UserRoleRepository userRoleRepository,UserRepository userRepository,RoleRpository roleRpository){
        this.modelMapper=modelMapper;
        this.userRoleRepository=userRoleRepository;
        this.userRepository=userRepository;
        this.roleRpository=roleRpository;
    }

    public UserRoleDTO todto(UserRoles userRoles){
        UserRoleDTO userRoleDTO =new UserRoleDTO();
        userRoleDTO.setDateDeb(userRoles.getDateDeb());
        userRoleDTO.setDateFin(userRoles.getDateFin());
        userRoleDTO.setUser(userRoles.getUser().getIdUser());
        userRoleDTO.setRole(userRoles.getRole().getIdRole());
        return userRoleDTO;
    }


    public UserRoles toEntity(UserRoleDTO dto) throws NotFoundException{
        UserRoles userRoles =new UserRoles();
        userRoles.setDateDeb(dto.getDateDeb());
        userRoles.setDateFin(dto.getDateFin());
        User user= userRepository.findById(dto.getUser()).orElseThrow(()->new NotFoundException("pas d'utilisateur"));
        userRoles.setUser(user);
        Role role = roleRpository.findById(dto.getRole()).orElseThrow(()->new NotFoundException("role not existe"));
        userRoles.setRole(role);
        return userRoles;
    }


//    public UserRoles create(UserRoleCreate userRoleCreate){
//        UserRoles userRoles=new UserRoles();
//        userRoles.setDateDeb(userRoleCreate.getDateDeb());
//        userRoles.setDateFin(userRoleCreate.getDateFin());
//
//        return userRoles;
//
//    }



}
