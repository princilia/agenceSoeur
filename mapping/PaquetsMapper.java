package techno.be.agencesoeur.mapping;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import techno.be.agencesoeur.models.dto.*;
import techno.be.agencesoeur.models.entities.Client;
import techno.be.agencesoeur.models.entities.Paquets;
import techno.be.agencesoeur.models.entities.User;
import techno.be.agencesoeur.reposositories.ClientRepository;
import techno.be.agencesoeur.reposositories.UserRepository;

@Component
public class PaquetsMapper {

    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private ClientRepository clientRepository;

    @Autowired
    public PaquetsMapper(ModelMapper modelMapper,UserRepository userRepository,ClientRepository clientRepository){
        this.modelMapper =modelMapper;
        this.clientRepository=clientRepository;
        this.userRepository=userRepository;
    }

    public PaquetsDTO todto(Paquets entity){
        PaquetsDTO paquetsDTO = new PaquetsDTO();
        paquetsDTO.setIdPaquets(entity.getIdPaquets());
        paquetsDTO.setChargement(entity.getChargement());
        paquetsDTO.setNumeropack(entity.getNumeropack());
        paquetsDTO.setDescription(entity.getDescription());
        paquetsDTO.setDateArriver(entity.getDateEntree());
        paquetsDTO.setDateEnvoie(entity.getDateEnvoie());
        paquetsDTO.setDateReception(entity.getDateReception());
        paquetsDTO.setEtatPaye(entity.isEtatPaye());
        paquetsDTO.setVolume(entity.getVolume());
        paquetsDTO.setDestination(entity.getDestination());
       // UserPaquetsDTO userPaquetsDTO=modelMapper.map(entity.getUser(),UserPaquetsDTO.class);
        //paquetsDTO.setUser(userPaquetsDTO);
        PaquetsClientEnvoieDTO paquetsClientSendDTO = modelMapper.map(entity.getClientEnvoie(), PaquetsClientEnvoieDTO.class);
        paquetsDTO.setClientEnvoie(paquetsClientSendDTO);
        PaquetsClientReceptionneDTO paquetsClientReceiveDTO = modelMapper.map(entity.getClientReceptionne(), PaquetsClientReceptionneDTO.class);
        paquetsDTO.setClientReceptionne(paquetsClientReceiveDTO);

        return paquetsDTO;
    }


    public Paquets entity(PaquetsDTO dto){
        Paquets paquets = new Paquets();
        paquets.setIdPaquets(dto.getIdPaquets());
        paquets.setChargement(dto.getChargement());
        paquets.setNumeropack(dto.getNumeropack());
        paquets.setDescription(dto.getDescription());
        paquets.setEtatPaye(dto.isEtatPaye());
        paquets.setDateEntree(dto.getDateArriver());
        paquets.setDateEnvoie(dto.getDateEnvoie());
        paquets.setDateReception(dto.getDateReception());
        paquets.setDestination(dto.getDestination());
        paquets.setVolume(dto.getVolume());
        paquets.setUser((modelMapper.map(dto.getUser(),User.class)));
        paquets.setClientEnvoie(modelMapper.map(dto.getClientEnvoie(), Client.class));
        paquets.setClientReceptionne(modelMapper.map(dto.getClientReceptionne(),Client.class));
        return paquets;

    }
    public Paquets CreatePaquets(PaquetsCreateDTO dto) throws NotFoundException{
        Paquets paquets=new Paquets();
//        User user = userRepository.findById(dto.getUser()).orElseThrow(()->new  NotFoundException("pas utilisateur"));
     //   paquets.setUser(user);
        Client clientEnvoie1=clientRepository.findById(dto.getClientEnvoie()).orElseThrow(()->new NotFoundException("pas client enregistré"));
        paquets.setClientEnvoie(clientEnvoie1);
        Client clientReceptionne1=clientRepository.findById(dto.getClientReceptionne()).orElseThrow(()->new NotFoundException("pas client enregistré"));
        paquets.setClientReceptionne(clientReceptionne1);
        paquets.setVolume(dto.getVolume());
        paquets.setEtatPaye(dto.isEtatPaye());
        paquets.setNumeropack(dto.getNumeropack());
        paquets.setChargement(dto.getChargement());
        paquets.setDestination(dto.getDestination());
        paquets.setDateEntree(dto.getDateArriver());
        paquets.setDateEnvoie(dto.getDateEnvoie());
        paquets.setDateReception(dto.getDateReception());
        return paquets;
    }
}

