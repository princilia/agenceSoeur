package techno.be.agencesoeur.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno.be.agencesoeur.mapping.GroupeMapper;
import techno.be.agencesoeur.models.dto.GroupeCreateDTO;
import techno.be.agencesoeur.models.dto.GroupeDTO;
import techno.be.agencesoeur.models.entities.Groupe;
import techno.be.agencesoeur.reposositories.GroupeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupeService implements CrudService<Groupe, GroupeDTO, GroupeCreateDTO,Long>{
    private GroupeRepository groupeRepository;
    private ModelMapper modelMapper;
    private GroupeMapper groupeMapper;

    @Autowired
    public GroupeService(GroupeRepository groupeRepository, ModelMapper modelMapper,GroupeMapper groupeMapper){
        this.groupeRepository=groupeRepository;
        this.modelMapper=modelMapper;
        this.groupeMapper=groupeMapper;

    }


    @Override
    public List<GroupeDTO> getAll() {
        return groupeRepository.findAll()
                .stream()
                .map(groupe -> modelMapper
                        .map(groupe,GroupeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GroupeDTO getOne(Long aLong) {
        Groupe groupe= groupeRepository.findById(aLong).orElse(null);
        if (groupe == null){
            return null;
        }
        GroupeDTO groupedto = groupeMapper.todto(groupe);
        return groupedto;
    }

    @Override
    public GroupeDTO create(GroupeCreateDTO tdto) {
       Groupe groupe = groupeRepository.save(groupeMapper.createdto(tdto));
       //GroupDTO  groupedto= groupeMapper.todto(groupe)
        //return groupedto;
        return groupeMapper.todto(groupe);

    }

    @Override
    public GroupeDTO update(GroupeDTO entity, Long aLong) {
        Groupe groupe = groupeRepository.findById(aLong).orElse(groupeMapper.toEntity(entity));
        groupe.setLabel(entity.getLabel());
        groupe.setDescription(entity.getDescription());
        groupeRepository.save(groupe);
        return groupeMapper.todto(groupe);
    }

    @Override
    public Boolean delete(Long aLong) {
        if (groupeRepository.existsById(aLong)){
            groupeRepository.deleteById(aLong);
            return true;
        }
        else
            return false;

    }
}
