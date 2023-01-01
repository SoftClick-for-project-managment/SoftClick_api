package softclick.server.webtier;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
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
import softclick.server.data.entities.*;
import softclick.server.webtier.services.domain.IDomainService;
import softclick.server.webtier.services.employee.IEmployeeService;
import softclick.server.webtier.services.expense.IExpenseService;
import softclick.server.webtier.services.priority.IPriorityService;
import softclick.server.webtier.services.project.IProjectService;
import softclick.server.webtier.services.role.IRoleService;
import softclick.server.webtier.services.status.IStatusService;
import softclick.server.webtier.services.task.ITaskService;
import softclick.server.webtier.services.user.IUserService;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
@OpenAPIDefinition
public class WebTierApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebTierApplication.class, args);
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
    CommandLineRunner run(ApplicationContext context,
                          IUserService userService,
                          IRoleService roleService,
                          ITaskService taskService,
                          IPriorityService priorityService,
                          IStatusService statusService,
                          IEmployeeService employeeService,
                          IProjectService projectService,
                          IDomainService domainService,
                          IExpenseService expenseService){
        return args -> {
//            userService.saveEntity(new User("othmane", "password", true));
//            userService.saveEntity(new User("wafae", "password", true));
//            userService.saveEntity(new User("youssef", "password", true));
//            userService.saveEntity(new User("hajar", "password", true));
//
//            roleService.saveEntity(new Role("ROLE_ADMIN"));
//            roleService.saveEntity(new Role("ROLE_DIRECTOR"));
//            roleService.saveEntity(new Role("ROLE_EMPLOYEE"));
//            roleService.saveEntity(new Role("ROLE_TEAM_MANAGER"));
//            roleService.saveEntity(new Role("ROLE_PROJECT_MANAGER"));
//
//            userService.addRoleToUser("othmane", "ROLE_ADMIN");
//            userService.addRoleToUser("othmane", "ROLE_DIRECTOR");
//            userService.addRoleToUser("wafae", "ROLE_EMPLOYEE");
//            userService.addRoleToUser("hajar", "ROLE_EMPLOYEE");
//            userService.addRoleToUser("hajar", "ROLE_TEAM_MANAGER");
//            userService.addRoleToUser("youssef", "ROLE_DIRECTOR");
//            userService.addRoleToUser("youssef", "ROLE_EMPLOYEE");
//            userService.addRoleToUser("youssef", "ROLE_PROJECT_MANAGER");
//
//            statusService.saveEntity(new Status("OPEN"));
//            statusService.saveEntity(new Status("IN PROGRESS"));
//            statusService.saveEntity(new Status("DONE"));
//
//            priorityService.saveEntity(new Priority("Prio1", 1D));
//            priorityService.saveEntity(new Priority("Prio2", 2D));
//            priorityService.saveEntity(new Priority("Prio3", 3D));
//
//            employeeService.saveEntity(new Employee("employee1.png", "Wafae", "Abouzbiba", "Data engineer", "wafae.abouzbiba@gmail.com", "+212639837323"));
//            employeeService.saveEntity(new Employee("employee2.png", "Youssef", "Zahi", "Backend engineer", "youssef.zahi@gmail.com", "+212639837323"));
//            employeeService.saveEntity(new Employee("employee3.png", "Hajar", "Faiz", "Frontend engineer", "hajar.faiz@gmail.com", "+212639837323"));
//
//            domainService.saveEntity(new Domain("Finance"));
//            domainService.saveEntity(new Domain("Commerce"));
//            domainService.saveEntity(new Domain("Industry"));
//            domainService.saveEntity(new Domain("Other"));
//
//            Date currentDate = new Date();
//            Date currentDatePlusDay = (Date) currentDate.clone();
//            currentDatePlusDay.setHours(currentDate.getHours()+24);
//            projectService.saveEntity(new Project("ProjectName", "this is the description", 1000000D, domainService.findEntityByKey(1L), currentDate, currentDatePlusDay, employeeService.findEntityByKey(1L), statusService.findEntityByKey(1L), priorityService.findEntityByKey(1L)));
//
//            taskService.saveEntity(new Task("complete web tier", LocalDateTime.now(), LocalDateTime.now().plusDays(1), null, null, null, null, null, null));
//            taskService.updateTask(1L, new Task("complete web tier", null, null, null, statusService.findEntityByKey(1L), projectService.findEntityByKey(1L), null, null, null));
//
//            ExpenseCategoryRepository expenseCategoryRepository = context.getBean(ExpenseCategoryRepository.class);
//            expenseCategoryRepository.save(new ExpenseCategory("category1"));
//            Expense expense = new Expense(1L, "type1", new Date(), expenseCategoryRepository.getReferenceById(1L));
//            expense.setTask(taskService.findEntityByKey(1L));
//            expenseService.saveEntity(expense);
        };
    }

}
