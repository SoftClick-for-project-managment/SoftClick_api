package softclick.server.webtier.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBaseService<T, Key> {
    void saveEntity(T entity);
    T findEntityByKey(Key key);
    List<T> getAllEntities();
    void deleteEntity(Key key);
    void deleteAllEntities(List<Key> keys);
    T patch(Key key , Map<Object,Object> fields , Class<T> tClass);
}
