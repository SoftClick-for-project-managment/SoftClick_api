package softclick.server.webtier.services.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import softclick.server.data.entities.User;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.services.BaseService;

import javax.transaction.Transactional;

@Transactional @Slf4j
public class AuthService extends BaseService<User, Long> implements IAuthService {

    public static AuthService init(ApplicationContext context){
        UserRepository userRepository = context.getBean(UserRepository.class);
        return new AuthService(userRepository);
    }

    protected AuthService(UserRepository repository) {
        super(repository);
    }
}
