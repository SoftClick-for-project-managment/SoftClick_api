package softclick.server.webtier;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import softclick.server.data.entities.Task;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.user.UserService;
import softclick.server.webtier.services.user.IUserService;
import softclick.server.webtier.services.task.ITaskService;
import softclick.server.webtier.services.task.TaskService;

import java.util.List;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
public class WebTierApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebTierApplication.class, args);

//        System.out.println("------------------- Testing auth service ----------------------");
//        IUserService authService = context.getBean(UserService.class);
//
//        List<User> users = authService.getAllEntities();
//
//        users.forEach(i -> System.out.println(i));
//
//        System.out.println("------------------- Testing task service ----------------------");
//        ITaskService taskService = context.getBean(TaskService.class);
//
//        List<Task> tasks = taskService.getAllEntities();
//
//        tasks.forEach(i -> System.out.println(i));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(IUserService userService){
        return args -> {
//            userService.saveEntity(new User("othmane", "password", true));
//            userService.saveEntity(new User("wafae", "password", true));
//            userService.saveEntity(new User("youssef", "password", true));
//            userService.saveEntity(new User("hajar", "password", true));
//
//            userService.addRoleToUser("othmane", "ROLE_ADMIN");
//            userService.addRoleToUser("othmane", "ROLE_DIRECTOR");
//            userService.addRoleToUser("wafae", "ROLE_EMPLOYEE");
//            userService.addRoleToUser("hajar", "ROLE_EMPLOYEE");
//            userService.addRoleToUser("hajar", "ROLE_TEAM_MANAGER");
//            userService.addRoleToUser("youssef", "ROLE_DIRECTOR");
//            userService.addRoleToUser("youssef", "MANAGER");
//            userService.addRoleToUser("youssef", "ROLE_PROJECT_MANAGER");
        };
    }

}
