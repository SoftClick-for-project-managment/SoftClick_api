package softclick.server.webtier.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softclick.server.webtier.services.user.FakeUserService;
import softclick.server.webtier.services.user.IUserService;
import softclick.server.webtier.services.user.UserService;

@Configuration
public class ServiceProviderConfig {
    @Bean
    @Autowired
    IUserService userService(ApplicationContext context){
        IUserService userService = UserService.init(context);
//        IUserService userService = new FakeUserService();
        return userService;
    }
}
