package techno.be.agencesoeur.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno.be.agencesoeur.mapping.UserMapper;
import techno.be.agencesoeur.models.dto.UserCreateDTO;
import techno.be.agencesoeur.models.dto.UserDTO;
import techno.be.agencesoeur.models.entities.Groupe;
import techno.be.agencesoeur.models.entities.Role;
import techno.be.agencesoeur.models.entities.User;
import techno.be.agencesoeur.models.entities.UserRoles;
import techno.be.agencesoeur.reposositories.GroupeRepository;
import techno.be.agencesoeur.reposositories.RoleRpository;
import techno.be.agencesoeur.reposositories.UserRepository;
import techno.be.agencesoeur.reposositories.UserRoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements CrudService<User,UserDTO,UserCreateDTO,Long> {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserMapper userMapper;
    private RoleRpository roleRpository;
    private UserRoleRepository userRoleRepository;
    private GroupeRepository groupeRepository;
    private final Long idDefault=3L;


    @Autowired
    public UserService(UserRepository userRepository,ModelMapper modelMapper,UserMapper userMapper,
                       RoleRpository roleRpository,UserRoleRepository userRoleRepository,GroupeRepository groupeRepository){
        this.userRepository= userRepository;
        this.userMapper=userMapper;
        this.modelMapper=modelMapper;
        this.roleRpository=roleRpository;
        this.userRoleRepository=userRoleRepository;
        this.groupeRepository=groupeRepository;



    }


    @Override
    public List<UserDTO> getAll() {
        return  userRepository.findAll()
                .stream()
                 .map(u -> userMapper.toDto(u))
                .collect(Collectors.toList());

    }

    @Override
    public UserDTO getOne(Long id) throws NotFoundException{
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("pas user trouvé"));

        UserDTO userDTO = userMapper.toDto(user);
       return userDTO;
    }

    @Override
    public UserDTO create(UserCreateDTO todto) throws NotFoundException {
        Role role= roleRpository.findById(idDefault).orElseThrow(()-> new NotFoundException("pas role"));
        Groupe groupe=groupeRepository.findById(idDefault).orElseThrow(()->new NotFoundException("pas groupe"));
        UserRoles userRoles = new UserRoles();
        User user = userRepository.save(userMapper.CreateUser(todto));
        Set<Groupe>listgroup = new HashSet<>();
        listgroup.add(groupe);
        user.setGroupes(listgroup);
        userRoles.setUser(user);
        userRoles.setRole(role);
        userRoleRepository.save(userRoles);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO update(UserDTO userdto, Long id) {
        User user = userRepository.findById(id).orElse(userMapper.toEntity(userdto));
        user.setNom(userdto.getNom());
        user.setUserName(userdto.getUsername());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setGroupes(userdto.getUserGroupeDTO().stream().map(g ->  modelMapper.map(g, Groupe.class)).collect(Collectors.toSet()));
        user.setUserRoles(userdto.getUserRolesDTO().stream().map(ur->modelMapper.map(ur, UserRoles.class)).collect(Collectors.toSet()));

        userRepository.save(user);
        return userMapper.toDto(user);

    }



    @Override
    public Boolean delete(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        else
            return false;
        }

    }

//
//    public UserDTO create(UserDTO todto) {
//        User user = userRepository.save(userMapper.toEntity(todto));
//        return userMapper.toDto(user);
//    }
//
//    return userMapper.toDto(user);
//        user.setNom(userdto.getNom());
//        user.setUsername(userdto.getUsername());
//        user.setEmail(userdto.getEmail());
//        user.setPassword(userdto.getPassword());
//
//    User user1 = userRepository.save(user);
//
//    //return user1;


