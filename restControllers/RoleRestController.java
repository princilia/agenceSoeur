package techno.be.agencesoeur.restControllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import techno.be.agencesoeur.models.dto.RoleCreateDTO;
import techno.be.agencesoeur.models.dto.RoleDTO;
import techno.be.agencesoeur.models.entities.Role;
import techno.be.agencesoeur.reposositories.RoleRpository;
import techno.be.agencesoeur.service.RoleService;
import techno.be.agencesoeur.util.CrudController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Api/role")
public class RoleRestController implements CrudController<Long, RoleDTO, RoleCreateDTO,RoleDTO> {

    private RoleService roleService;
    private RoleRpository roleRpository;

    @Autowired
    public RoleRestController(RoleRpository roleRpository,RoleService roleService){
        this.roleRpository=roleRpository;
        this.roleService=roleService;
    }

    @Override
    @RequestMapping(value = {"/create"},method = {RequestMethod.POST})
    public ResponseEntity<RoleDTO> createAction(@Valid RoleCreateDTO obj) {
        return ResponseEntity.ok(roleService.create(obj));
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<RoleDTO> getOneByIdAction(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(roleService.getOne(id));
    }

    @Override
    @RequestMapping(value = {"/list"},method = {RequestMethod.GET})
    public ResponseEntity<List<RoleDTO>> getAllAction() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = {RequestMethod.PUT})
    public ResponseEntity<RoleDTO> updateOneAction(@PathVariable("id") Long id,@RequestBody RoleDTO obj ) {
        return ResponseEntity.ok(roleService.update(obj,id));
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> deleteOneAction(@PathVariable("id") Long id) {

        return ResponseEntity.ok(roleService.delete(id));
    }


}
