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
import softclick.server.webtier.services.status.IStatusService;
import softclick.server.webtier.services.task.ITaskService;
import softclick.server.webtier.services.user.IUserService;

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
//            userService.addRoleToUser("othmane", "ROLE_ADMIN");
//            userService.addRoleToUser("othmane", "ROLE_DIRECTOR");
//            userService.addRoleToUser("wafae", "ROLE_EMPLOYEE");
//            userService.addRoleToUser("hajar", "ROLE_EMPLOYEE");
//            userService.addRoleToUser("hajar", "ROLE_TEAM_MANAGER");
//            userService.addRoleToUser("youssef", "ROLE_DIRECTOR");
//            userService.addRoleToUser("youssef", "MANAGER");
//            userService.addRoleToUser("youssef", "ROLE_PROJECT_MANAGER");

//            statusService.saveEntity(new Status("OPEN"));
//            statusService.saveEntity(new Status("IN PROGRESS"));
//            statusService.saveEntity(new Status("DONE"));
//
//            priorityService.saveEntity(new Priority("Prio1", 1F));
//            priorityService.saveEntity(new Priority("Prio2", 2F));
//            priorityService.saveEntity(new Priority("Prio3", 3F));
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
//            projectService.saveEntity(new Project("ProjectName", "this is the description", 1000000D, domainService.findEntityByKey(1L), new Date(), null, employeeService.findEntityByKey(1L), statusService.findEntityByKey(1L), priorityService.findEntityByKey(1L)));

//            taskService.saveEntity(new Task("complete web tier", new Date(), new Date(), null, null, null, null, null, null));

//            ExpenseCategoryRepository expenseCategoryRepository = context.getBean(ExpenseCategoryRepository.class);
//            expenseCategoryRepository.save(new ExpenseCategory("category1"));
//            Expense expense = new Expense(2299L, "type1", new Date(), expenseCategoryRepository.getReferenceById(1L));
//            expense.setTask(taskService.findEntityByKey(10002L));
//            expenseService.saveEntity(expense);
        };
    }

}
