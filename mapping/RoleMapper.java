package techno.be.agencesoeur.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import techno.be.agencesoeur.models.dto.*;
import techno.be.agencesoeur.models.entities.Role;

@Component
public class RoleMapper {
    private ModelMapper modelMapper;


    @Autowired
    public RoleMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public RoleDTO toDTO(Role role){
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getIdRole());
        dto.setLabel(role.getLabel());
        dto.setDescription(role.getDescription());
//        Set<RoleGroupeDTO>rgroups=role.getGroupes().stream().map(groupe -> modelMapper.map(groupe,RoleGroupeDTO.class)).collect(Collectors.toSet());
//        dto.setRolegroupesDTO(rgroups);
//        Set<UserRoleDTO>urRole=role.getUserRoles().stream().map(userRoles -> modelMapper.map(userRoles,UserRoleDTO.class)).collect(Collectors.toSet());
//        dto.setUserRolesDTO(urRole);
        return dto;
    }

    public Role toEntity(RoleDTO dto){
        Role role = new Role();
        role.setIdRole(dto.getId());
        role.setLabel(dto.getLabel());
        role.setDescription(dto.getDescription());
        //role.setGroupes(dto.getRolegroupesDTO().stream().map(groupeDTO -> modelMapper.map(groupeDTO, Groupe.class)).collect(Collectors.toSet()));
       //   role.setUserRoles(dto.getUserRolesDTO().stream().map(userRoleDTO -> modelMapper.map(userRoleDTO, UserRoles.class)).collect(Collectors.toSet()));
        return role;
    }

    public Role createRole(RoleCreateDTO roleCreateDTO){
        Role role= new Role();
        role.setLabel(roleCreateDTO.getLabel());
        role.setDescription(roleCreateDTO.getDescription());
        return role;


    }



}
