package softclick.server.webtier.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Role;
import softclick.server.data.entities.User;
import softclick.server.data.repositories.RoleRepository;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.services.BaseService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service @Transactional @Slf4j
public class UserService extends BaseService<User, Long> implements IUserService, UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    protected UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveEntity(User entity) {
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
            log.info("User with {} loged in", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findByUsername(username);
        Collection<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
