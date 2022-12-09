package softclick.server.webtier.services.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service @Qualifier("fakeUserService")
public class FakeUserService implements IUserService {

    private List<User> users = new ArrayList<>();

    public FakeUserService() {
        users.add(new User("othmane", "password", true));
        users.add(new User("wafae", "password", false));
    }

    @Override
    public void saveEntity(User entity) {

    }

    @Override
    public User findEntityByKey(Long aLong) {
        return users.get(aLong.intValue());
    }

    @Override
    public List<User> getAllEntities() {
        return users;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }

    @Override
    public User patch(Long aLong, Map<Object, Object> fields, Class<User> userClass) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public void updateUser(Long id, User user) {

    }

    @Override
    public void assignUserToEmployee(User user, Employee employee) {

    }
}
