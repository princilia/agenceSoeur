package techno.be.agencesoeur.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import techno.be.agencesoeur.models.dto.ClientDTO;
import techno.be.agencesoeur.models.dto.ClientPaquetsEnvoiDTO;
import techno.be.agencesoeur.models.dto.ClientPaquetsReceiveDTO;
import techno.be.agencesoeur.models.entities.Client;
import techno.be.agencesoeur.models.entities.Paquets;
import techno.be.agencesoeur.reposositories.ClientRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    private ClientRepository clientRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ClientMapper(ModelMapper modelMapper,ClientRepository clientRepository){
        this.clientRepository=clientRepository;
        this.modelMapper=modelMapper;
    }

    public ClientDTO todto(Client entity){
        System.out.println("voici mon mapper entity to dto");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setIdClient(entity.getIdClient());
        clientDTO.setNumeroCli(entity.getNumeroCli());
        clientDTO.setNom(entity.getNom());
        clientDTO.setAdresse(entity.getAdresse());
        clientDTO.setEmail(entity.getEmail());
        clientDTO.setPrenom(entity.getPrenom());
        clientDTO.setTelephone(entity.getTelephone());
        Set<ClientPaquetsEnvoiDTO>clientPaquetsSendDTO = entity.getPaquetsEnvoyer().stream().map(paquets -> modelMapper.map(paquets, ClientPaquetsEnvoiDTO.class)).collect(Collectors.toSet());
        Set<ClientPaquetsReceiveDTO>clientPaquetsRecDTO = entity.getPaquetsReceptionner().stream().map(paquets -> modelMapper.map(paquets, ClientPaquetsReceiveDTO.class)).collect(Collectors.toSet());

        clientDTO.setPaquetsenvoie(clientPaquetsSendDTO);
        clientDTO.setPaquetsreceive(clientPaquetsRecDTO);

        return clientDTO;

    }


    public Client entity(ClientDTO todto){
        Client client=new Client();
        client.setIdClient(todto.getIdClient());
        client.setNumeroCli(todto.getNumeroCli());
        client.setNom(todto.getNom());
        client.setPrenom(todto.getPrenom());
        client.setAdresse(todto.getAdresse());
        client.setEmail(todto.getEmail());
        client.setTelephone(todto.getTelephone());
        client.setPaquetsEnvoyer(todto.getPaquetsenvoie().stream().map(paquetsDTO -> modelMapper.map(paquetsDTO, Paquets.class)).collect(Collectors.toSet()));
        client.setPaquetsReceptionner(todto.getPaquetsreceive().stream().map(paquetsDTO -> modelMapper.map(paquetsDTO, Paquets.class)).collect(Collectors.toSet()));
        return client;

    }



}
