package techno.be.agencesoeur.service;

import javassist.NotFoundException;

import java.util.List;

public interface CrudService<TEntity, TDTO, TCreate,TId> {

    List<TDTO> getAll();
    TDTO getOne(TId id) throws NotFoundException;
    TDTO create(TCreate tdto) throws NotFoundException;
    TDTO update(TDTO entity, TId id) throws NotFoundException;
    Boolean delete(TId id) ;


}
