package softclick.server.webtier.services.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Role;
import softclick.server.data.entities.User;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.webtier.services.BaseService;

import javax.transaction.Transactional;
import java.util.Collection;

@Service @Transactional @Slf4j @Qualifier("rmiEmployeeService")
public class EmployeeService extends BaseService<Employee, Long> implements IEmployeeService {

    /*public static EmployeeService init(ApplicationContext context){
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        return new EmployeeService(employeeRepository);
    }*/

    protected EmployeeService(EmployeeRepository repository) {
        super(repository);
    }

    @Override
    public void addSkillToEmployee(String employeeName, String skillName) {

    }
}
