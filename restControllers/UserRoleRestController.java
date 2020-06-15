package techno.be.agencesoeur.restControllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techno.be.agencesoeur.models.dto.UserRoleDTO;
import techno.be.agencesoeur.models.entities.UserRoleID;
import techno.be.agencesoeur.service.UserRolesService;
import techno.be.agencesoeur.service.UserService;
import techno.be.agencesoeur.util.CrudController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Api/userRole")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRoleRestController implements CrudController<UserRoleID, UserRoleDTO,UserRoleDTO,UserRoleDTO> {

    private UserRolesService userRolesService;

    @Autowired
    public UserRoleRestController(UserRolesService userRolesService){
        this.userRolesService=userRolesService;
    }


    @Override
    @RequestMapping(value = {"/create"},method = RequestMethod.POST)
    public ResponseEntity<UserRoleDTO> createAction(@Valid UserRoleDTO obj) throws NotFoundException {
        return ResponseEntity.ok(userRolesService.create(obj));
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
    public ResponseEntity<UserRoleDTO> getOneByIdAction(@PathVariable ("id") UserRoleID id)throws NotFoundException {
        return ResponseEntity.ok(userRolesService.getOne(id));
    }

    @Override
    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public ResponseEntity<List<UserRoleDTO>> getAllAction() {
        return  ResponseEntity.ok(userRolesService.getAll());
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = RequestMethod.PUT)
    public ResponseEntity<UserRoleDTO> updateOneAction(@PathVariable ("id") UserRoleID id,@RequestBody UserRoleDTO obj) throws NotFoundException {
        return ResponseEntity.ok(userRolesService.update(obj,id));
    }

    @Override
    @RequestMapping(value = {"/{id}"},method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteOneAction(@PathVariable ("id") UserRoleID id) {
        return ResponseEntity.ok(userRolesService.delete(id));
    }
}
