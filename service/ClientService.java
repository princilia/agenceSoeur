package techno.be.agencesoeur.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno.be.agencesoeur.mapping.ClientMapper;
import techno.be.agencesoeur.models.dto.ClientDTO;
import techno.be.agencesoeur.models.entities.Client;
import techno.be.agencesoeur.reposositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements CrudService<Client, ClientDTO,ClientDTO,Long> {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository,ClientMapper clientMapper){
        this.clientMapper=clientMapper;
        this.clientRepository=clientRepository;
    }

    @Override
    public List<ClientDTO> getAll() {
        return clientRepository.findAll()
                .stream()
                .map( client -> clientMapper.todto(client))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getOne(Long aLong) throws NotFoundException {
        Client client= clientRepository.findById(aLong).orElseThrow(()-> new NotFoundException("client pas trouvé"));

        return clientMapper.todto(client);
    }

    @Override
    public ClientDTO create(ClientDTO tdto)  {
        Client client=clientRepository.save(clientMapper.entity(tdto));
        return clientMapper.todto(client);
    }

    @Override
    public ClientDTO update(ClientDTO entity, Long aLong) throws NotFoundException {
        Client client=clientRepository.findById(aLong).orElseThrow(()->new NotFoundException("pas client trouvé"));
        clientRepository.save(clientMapper.entity(entity));
        return clientMapper.todto(client);
    }

    @Override
    public Boolean delete(Long aLong) {
        if(clientRepository.existsById(aLong)) {
            clientRepository.deleteById(aLong);
            return true;
        }
        else
            return false;
    }

}

