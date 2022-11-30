package softclick.server.webtier.services.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.entities.User;
import softclick.server.webtier.services.BaseService;

import javax.transaction.Transactional;

@Transactional @Slf4j
public class AuthService extends BaseService<User, Long> implements IAuthService {

    public static AuthService init(){
       UserRepository userRepository = null;
       return new AuthService(userRepository);
    }

    protected AuthService(UserRepository repository) {
        super(repository);
    }
}
