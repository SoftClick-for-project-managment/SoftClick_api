package softclick.server.webtier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import softclick.server.data.entities.Task;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.auth.AuthService;
import softclick.server.webtier.services.auth.IAuthService;
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
//        IAuthService authService = AuthService.init(context);
//
//        List<User> users = authService.getAllEntities();
//
//        users.forEach(i -> System.out.println(i));
//
//        System.out.println("------------------- Testing task service ----------------------");
//        ITaskService taskService = TaskService.init(context);
//
//        List<Task> tasks = taskService.getAllEntities();
//
//        tasks.forEach(i -> System.out.println(i));
    }

}
