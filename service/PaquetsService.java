package techno.be.agencesoeur.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno.be.agencesoeur.mapping.PaquetsMapper;
import techno.be.agencesoeur.models.dto.PaquetsCreateDTO;
import techno.be.agencesoeur.models.dto.PaquetsDTO;
import techno.be.agencesoeur.models.entities.Paquets;
import techno.be.agencesoeur.reposositories.PaquetsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaquetsService implements CrudService<Paquets, PaquetsDTO, PaquetsCreateDTO,Long> {

    private PaquetsMapper paquetsMapper;
    private ModelMapper modelMapper;
    private PaquetsRepository paquetsRepository;

    @Autowired
    public PaquetsService(PaquetsMapper paquetsMapper,PaquetsRepository paquetsRepository,ModelMapper modelMapper){
        this.paquetsRepository=paquetsRepository;
        this.modelMapper=modelMapper;
        this.paquetsMapper=paquetsMapper;
    }
    @Override
    public List<PaquetsDTO> getAll() {
        return paquetsRepository.findAll().stream().map(paquets -> paquetsMapper.todto(paquets)).collect(Collectors.toList());
    }

    @Override
    public PaquetsDTO getOne(Long aLong) throws NotFoundException {
        Paquets paquets= paquetsRepository.findById(aLong).orElseThrow(()->new NotFoundException("pas paquets"));
        return paquetsMapper.todto(paquets);
    }

    @Override
    /**
     * Methode permettant la création et l'ajout en base de données d'un paquet
     * @param PaquetsCreateDTO tdto != null
     * @throws NotFoundException | tdto == null || tdto.sender == null ...
     * @return PaquetsDTO
     */
    public PaquetsDTO create(PaquetsCreateDTO tdto) throws NotFoundException {
        //if (tdto == null) throw new NotFoundException("!! ajouter nouveau paquets ");
        Paquets paquets=paquetsRepository.save(paquetsMapper.CreatePaquets(tdto));
        return paquetsMapper.todto(paquets);
    }

    @Override
    public PaquetsDTO update(PaquetsDTO entity, Long aLong) throws NotFoundException {
        Paquets paquets =paquetsRepository.findById( aLong).orElseThrow(()->new NotFoundException("pas trouve"));
        paquetsRepository.save(paquetsMapper.entity(entity));
        return paquetsMapper.todto(paquets);
    }

    @Override
    public Boolean delete(Long aLong) {
        if(paquetsRepository.existsById(aLong)){
            paquetsRepository.deleteById(aLong);
            return true;
        }
        else
            return false;

    }
}
