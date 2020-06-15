package techno.be.agencesoeur.util;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface CrudController<TKey, TDTO, TCreate, TUpdate> {
    ResponseEntity<TDTO> createAction(@Valid @RequestBody TCreate obj) throws NotFoundException;
    ResponseEntity<TDTO> getOneByIdAction(@PathVariable("id") TKey id) throws NotFoundException;
    ResponseEntity<List<TDTO>> getAllAction();
    ResponseEntity<TDTO> updateOneAction(@PathVariable("id") TKey id, @RequestBody TUpdate obj) throws NotFoundException;
    ResponseEntity<Boolean>deleteOneAction(@PathVariable("id") TKey id);
}
