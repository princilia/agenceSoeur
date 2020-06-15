package techno.be.agencesoeur.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techno.be.agencesoeur.models.dto.GroupeCreateDTO;
import techno.be.agencesoeur.models.dto.GroupeDTO;
import techno.be.agencesoeur.service.GroupeService;
import techno.be.agencesoeur.util.CrudController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Api/groupe")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupeRestController implements CrudController<Long, GroupeDTO, GroupeCreateDTO,GroupeDTO> {

    private GroupeService groupeService;
    @Autowired
    public GroupeRestController(GroupeService groupeService){
        this.groupeService=groupeService;
    }



    @Override
    @RequestMapping(value ={ "/create"},method = RequestMethod.POST)
    public ResponseEntity<GroupeDTO> createAction(@Valid GroupeCreateDTO obj) {
        return ResponseEntity.ok(groupeService.create(obj));
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<GroupeDTO> getOneByIdAction(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(groupeService.getOne(id));
    }

    @Override
    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public ResponseEntity<List<GroupeDTO>> getAllAction() {
        return ResponseEntity.ok(groupeService.getAll());
    }

    @Override
    @RequestMapping(value = {"/{id}}"},method = RequestMethod.PUT)
    public ResponseEntity<GroupeDTO> updateOneAction(@PathVariable("id") Long id,@RequestBody GroupeDTO obj) {
        return ResponseEntity.ok(groupeService.update(obj,id));
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteOneAction(@PathVariable("id") Long id) {

        return ResponseEntity.ok(groupeService.delete(id));
    }
}
