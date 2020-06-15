package techno.be.agencesoeur.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno.be.agencesoeur.mapping.UserRoleMapper;
import techno.be.agencesoeur.models.dto.UserRoleDTO;
import techno.be.agencesoeur.models.entities.UserRoleID;
import techno.be.agencesoeur.models.entities.UserRoles;
import techno.be.agencesoeur.reposositories.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRolesService implements CrudService<UserRoles,UserRoleDTO, UserRoleDTO, UserRoleID> {
    private UserRoleRepository userRoleRepository;
    private ModelMapper modelMapper;
    private UserRoleMapper userRoleMapper;

    @Autowired
    public UserRolesService(UserRoleRepository userRoleRepository, ModelMapper modelMapper,UserRoleMapper userRoleMapper){
        this.userRoleRepository=userRoleRepository;
        this.modelMapper=modelMapper;
        this.userRoleMapper=userRoleMapper;

    }


    @Override
    public List<UserRoleDTO> getAll() {
        return userRoleRepository.findAll().stream().map(userRoles -> userRoleMapper.todto(userRoles)
        ).collect(Collectors.toList());
    }

    @Override
    public UserRoleDTO getOne(UserRoleID userRoleID) throws NotFoundException{
        UserRoles userRoles = userRoleRepository.findById(userRoleID).orElseThrow(()-> new NotFoundException("pas id "));
        UserRoleDTO userRoleDTO =userRoleMapper.todto(userRoles);
        return userRoleDTO;
    }

    @Override
    public UserRoleDTO create(UserRoleDTO tdto) throws NotFoundException {
        UserRoles userRoles=userRoleRepository.save(userRoleMapper.toEntity(tdto));
        return userRoleMapper.todto(userRoles);

    }

    @Override
    public UserRoleDTO update(UserRoleDTO todto, UserRoleID userRoleID) throws NotFoundException {
        UserRoles userRoles = userRoleRepository.findById(userRoleID).orElse(userRoleMapper.toEntity(todto));
        userRoles.setDateDeb(todto.getDateDeb());
        userRoles.setDateFin(todto.getDateFin());
        userRoleRepository.save(userRoles);
        return userRoleMapper.todto(userRoles);
    }

    @Override
    public Boolean delete(UserRoleID userRoleID) {
        if (userRoleRepository.existsById(userRoleID)){
            userRoleRepository.deleteById(userRoleID);
            return true;
        }

        else
            return false;


    }
}
