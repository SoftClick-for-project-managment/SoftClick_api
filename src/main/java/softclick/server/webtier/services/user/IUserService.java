package softclick.server.webtier.services.user;

import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Role;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.IBaseService;

public interface IUserService extends IBaseService<User, Long> {
    void addRoleToUser(String username, String roleName);
    void updateUser(Long id, User user);
    void assignUserToEmployee(User user, Employee employee);
//    User getUserByUsername(String username);
}
