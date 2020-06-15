package techno.be.agencesoeur.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno.be.agencesoeur.mapping.RoleMapper;
import techno.be.agencesoeur.models.dto.RoleCreateDTO;
import techno.be.agencesoeur.models.dto.RoleDTO;
import techno.be.agencesoeur.models.entities.Role;
import techno.be.agencesoeur.reposositories.RoleRpository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements CrudService<Role, RoleDTO, RoleCreateDTO,Long> {

    private RoleRpository roleRpository;
    private ModelMapper modelMapper;
    private RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRpository roleRpository,ModelMapper modelMapper,RoleMapper roleMapper){
        this.roleRpository=roleRpository;
        this.modelMapper=modelMapper;
        this.roleMapper=roleMapper;

    }

    @Override
    public List<RoleDTO> getAll() {
        return roleRpository.findAll()
                .stream()
                .map(role -> modelMapper.map(role,RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getOne(Long aLong)throws NotFoundException {
        Role  role = roleRpository.findById(aLong).orElseThrow(()->new NotFoundException("role not exist"));

        RoleDTO roleDTO = roleMapper.toDTO(role);
        return roleDTO;
    }

    @Override
    public RoleDTO create(RoleCreateDTO tdto) {
        Role role = roleRpository.save(roleMapper.createRole(tdto));

        return roleMapper.toDTO(role);
    }

    @Override
    public RoleDTO update(RoleDTO entity, Long aLong) {
        Role role = roleRpository.findById(aLong).orElse(roleMapper.toEntity(entity));
        role.setLabel(entity.getLabel());
        role.setDescription(entity.getDescription());
        roleRpository.save(role);
        return roleMapper.toDTO(role);
    }

    @Override
    public Boolean delete(Long aLong) {
        if (roleRpository.existsById(aLong)){
            roleRpository.deleteById(aLong);
            return true;
        }
        else
            return false;

    }

}
