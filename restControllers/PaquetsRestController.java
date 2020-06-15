package techno.be.agencesoeur.restControllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techno.be.agencesoeur.models.dto.PaquetsCreateDTO;
import techno.be.agencesoeur.models.dto.PaquetsDTO;
import techno.be.agencesoeur.service.PaquetsService;
import techno.be.agencesoeur.util.CrudController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("Api/paquets")
public class PaquetsRestController implements CrudController<Long, PaquetsDTO,PaquetsCreateDTO,PaquetsDTO> {

    private PaquetsService paquetsService;

    @Autowired
    public PaquetsRestController(PaquetsService paquetsService){
        this.paquetsService=paquetsService;
    }

    @Override
    @RequestMapping(value = {"/create"},method = RequestMethod.POST)
    public ResponseEntity<PaquetsDTO> createAction(@Valid PaquetsCreateDTO obj) throws NotFoundException {
        return ResponseEntity.ok(paquetsService.create(obj));

    }

    @Override
    @RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
    public ResponseEntity<PaquetsDTO> getOneByIdAction(@PathVariable ("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(paquetsService.getOne(id));
    }

    @Override
    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public ResponseEntity<List<PaquetsDTO>> getAllAction() {
        return ResponseEntity.ok(paquetsService.getAll());
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = RequestMethod.PUT)
    public ResponseEntity<PaquetsDTO> updateOneAction(@PathVariable ("id") Long id,@RequestBody PaquetsDTO obj) throws NotFoundException {
        return ResponseEntity.ok(paquetsService.update(obj,id));
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteOneAction(@PathVariable ("id") Long id) {

        return ResponseEntity.ok(paquetsService.delete(id));
    }
}
