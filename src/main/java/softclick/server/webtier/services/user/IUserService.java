package softclick.server.webtier.services.user;

import softclick.server.data.entities.Role;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.IBaseService;

public interface IUserService extends IBaseService<User, Long> {
    void addRoleToUser(String username, String roleName);
//    User getUserByUsername(String username);
}
