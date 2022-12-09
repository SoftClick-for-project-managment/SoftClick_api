package softclick.server.webtier.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ReflectionUtils;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class BaseService<T, Key> implements IBaseService<T, Key>{
    protected JpaRepository<T, Key> repository;

    public BaseService(JpaRepository<T, Key> repository) {
        this.repository = repository;
    }

    @Override
    public void saveEntity(T entity) {
        log.info("Saving new entity of type " + entity.getClass().toString());
        repository.save(entity);
    }

    @Override
    public T findEntityByKey(Key key) {
        log.info("Fetching one entity");
        try{
            T data = repository.getReferenceById(key);
            return data;
        }catch(EntityNotFoundException e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<T> getAllEntities() {
        log.info("Fetching all entities ");
        return repository.findAll();
    }

    @Override
    public void deleteEntity(Key key) {
        log.info("Deleting entity");
        try{
            repository.delete(repository.getReferenceById(key));
        }catch(EntityNotFoundException e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteAllEntities(List<Key> keys) {
        log.info("Deleting all entities");
        try{
            repository.deleteAll(repository.findAllById(keys));
        }catch(EntityNotFoundException e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    public T patch(Key key , Map<Object,Object> fields , Class<T> tClass){
        T entity = this.findEntityByKey(key);
        if(entity != null){
            fields.forEach((cles,value)->{
                Field field = ReflectionUtils.findField(tClass,cles.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field,entity,value);
            });
            return  entity;
        }
        return  null;
    }
}
