package softclick.server.webtier.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import softclick.server.data.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<User> findEntityByKey(Long aLong) {
        return Optional.empty();
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
    public void addRoleToUser(String username, String roleName) {

    }
}
