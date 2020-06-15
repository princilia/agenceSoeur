package techno.be.agencesoeur.restControllers;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techno.be.agencesoeur.models.dto.*;
import techno.be.agencesoeur.reposositories.UserRepository;
import techno.be.agencesoeur.service.UserService;
import techno.be.agencesoeur.util.CrudController;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Api/user")
public class UserResController implements CrudController<Long, UserDTO, UserCreateDTO, UserDTO> {

    private UserRepository userRepository;
    private  UserService userService;

    @Autowired
    public UserResController(UserRepository userRepository, UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }


    @Override
    @RequestMapping(value = {"/create"}, method = {RequestMethod.POST})
    public ResponseEntity<UserDTO> createAction(@Valid @RequestBody UserCreateDTO obj) throws NotFoundException {
        return ResponseEntity.ok(userService.create(obj));

    }


    @Override
    @RequestMapping(value= {"/{id}"}, method= {RequestMethod.GET})
    public ResponseEntity<UserDTO> getOneByIdAction(@PathVariable("id") Long id) throws NotFoundException {

        return ResponseEntity.ok(userService.getOne(id));

    }

    @Override
    @RequestMapping(value= {"/list"}, method= {RequestMethod.GET})
    public ResponseEntity<List<UserDTO>> getAllAction() {
        return ResponseEntity.ok( userService.getAll());

    }

    @Override
    @RequestMapping(value= {"/{id}"}, method= {RequestMethod.PUT})
    public ResponseEntity<UserDTO> updateOneAction(@PathVariable("id") Long id, @RequestBody UserDTO obj) {

        return ResponseEntity.ok(userService.update(obj, id));
    }

    @Override
    @RequestMapping(value= {"/{id}"}, method= {RequestMethod.DELETE})
    public ResponseEntity<Boolean> deleteOneAction(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}





//    @GetMapping("/search/{name}")
//    public String getByName(@PathVariable String name) {
//        User user = userRepository.searByNameNativeQuery(name);
//        model.addAttribute("user", user);
//
//        if (user == null) {
//            return "redirect:/user";
//        }
//
//        return "user/list";
//    }



