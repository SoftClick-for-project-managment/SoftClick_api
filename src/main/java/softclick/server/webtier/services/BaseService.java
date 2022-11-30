package softclick.server.webtier.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional @Slf4j
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
    public Optional<T> findEntityByKey(Key key) {
        log.info("Fetching one entity");
        return repository.findById(key);
    }

    @Override
    public List<T> getAllEntities() {
        log.info("Fetching all entity ");
        return repository.findAll();
    }

    @Override
    public void deleteEntity(Key key) {
        log.info("Deleting entity");
        repository.delete((T) repository.findById(key));
    }

    @Override
    public void deleteAllEntities(List<Key> keys) {
        log.info("Deleting all entities");
        repository.deleteAll(repository.findAllById(keys));
    }
}
