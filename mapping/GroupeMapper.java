package techno.be.agencesoeur.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import techno.be.agencesoeur.models.dto.GroupeCreateDTO;
import techno.be.agencesoeur.models.dto.GroupeDTO;
import techno.be.agencesoeur.models.entities.Groupe;

@Component
public class GroupeMapper {
    private ModelMapper modelMapper;

    @Autowired
    public GroupeMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public GroupeDTO todto(Groupe groupe){
        GroupeDTO dto = new GroupeDTO();
        dto.setId(groupe.getIdGroupe());
        dto.setLabel(groupe.getLabel());
        dto.setDescription(groupe.getDescription());
//        Set<GroupeUserDTO>groupeUser = groupe.getUsers().stream().map(user -> modelMapper.map(user,GroupeUserDTO.class)).collect(Collectors.toSet());
//        dto.setGroupeUserDTO(groupeUser);
//        Set<RoleGroupeDTO>rolegroup = groupe.getRoles().stream().map(role -> modelMapper.map(role,RoleGroupeDTO.class)).collect(Collectors.toSet());
//        dto.setRolesGroupDTO(rolegroup);
        return dto;
    }

    public Groupe toEntity(GroupeDTO dto){
        Groupe groupe = new Groupe();
        groupe.setIdGroupe(dto.getId());
        groupe.setLabel(dto.getLabel());
        groupe.setDescription(dto.getDescription());
//        groupe.setUsers(dto.getGroupeUserDTO().stream().map(userDTO -> modelMapper.map(userDTO, User.class)).collect(Collectors.toSet()));
//        groupe.setRoles(dto.getRolesGroupDTO().stream().map(roleDTO->modelMapper.map(roleDTO,Role.class)).collect(Collectors.toSet()));
        return groupe;
    }

    public Groupe createdto (GroupeCreateDTO createDTO){
        Groupe groupe = new Groupe();
        groupe.setLabel(createDTO.getLabel());
        groupe.setDescription(createDTO.getDescription());
        return groupe;


    }

}
