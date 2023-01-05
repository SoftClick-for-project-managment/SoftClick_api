package softclick.server.webtier.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Role;
import softclick.server.data.entities.User;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.data.repositories.RoleRepository;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.services.BaseService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service @Slf4j @Qualifier("rmiUserService")
public class UserService extends BaseService<User, Long> implements IUserService, UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    protected UserService(UserRepository userRepository, RoleRepository roleRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveEntity(User entity) {
        if (entity.getEmployee() != null){
            Employee employee = employeeRepository.save(entity.getEmployee());
            entity.setEmployee(employee);
        }
        if (entity.getRoles() instanceof Collection) {
            Collection<Role> roles = new ArrayList<>();
            entity.getRoles().forEach(r -> {
                roles.add(roleRepository.findByName(r.getName()));
            });
            entity.setRoles(roles);
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        super.saveEntity(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            log.info("Failed attempt to login");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User with {} logged in", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public void updateUser(Long id, User newUser){
        log.info("Updating user with id: {}", id.toString());
        User user = userRepository.getReferenceById(id);
        if (user == null)
            throw new EntityNotFoundException();
        User existedUserWithUsername = userRepository.findByUsername(newUser.getUsername());
        if ((existedUserWithUsername != null) && !existedUserWithUsername.getId().equals(user.getId()))
            throw new RuntimeException("A user with the given username already exists");
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setActive(newUser.isActive());
        userRepository.save(user);
    }

    @Override
    public void assignUserToEmployee(User user, Employee employee) {

    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findByUsername(username);
        if(user.getRoles().contains(role))
            throw new RuntimeException("User already assigned the given role");
        Collection<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User patch(Long aLong, Map<Object, Object> fields, Class<User> userClass) {
        User user = super.patch(aLong, fields, userClass);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
