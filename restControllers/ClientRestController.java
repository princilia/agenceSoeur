package techno.be.agencesoeur.restControllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techno.be.agencesoeur.models.dto.ClientDTO;
import techno.be.agencesoeur.service.ClientService;
import techno.be.agencesoeur.util.CrudController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Api/client")
public class ClientRestController implements CrudController<Long, ClientDTO,ClientDTO,ClientDTO> {

    private ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService){
        this.clientService=clientService;
    }
    @Override
    /**
     * création de mon nouveau client
     *
     */
    @RequestMapping(value = {"/create"},method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> createAction(@Valid ClientDTO obj)  {
        return ResponseEntity.ok(clientService.create(obj));
    }

    @Override
    @RequestMapping(value = {"{id}"},method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> getOneByIdAction(Long id) throws NotFoundException {
        return ResponseEntity.ok(clientService.getOne(id));
    }

    @Override
    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getAllAction() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @Override
    @RequestMapping(value= {"/{id}"}, method= {RequestMethod.PUT})
    public ResponseEntity<ClientDTO> updateOneAction(@PathVariable("id") Long id, @RequestBody ClientDTO obj) throws NotFoundException {
        return ResponseEntity.ok(clientService.update(obj,id));
    }

    @Override
    @RequestMapping(value= {"/{id}"}, method= {RequestMethod.DELETE})
    public ResponseEntity<Boolean> deleteOneAction(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clientService.delete(id));
    }
}
