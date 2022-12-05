package softclick.server.webtier.services.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Skill;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.data.repositories.SkillRepository;
import softclick.server.webtier.services.BaseService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Set;

@Service @Transactional @Slf4j @Qualifier("rmiEmployeeService")
public class EmployeeService extends BaseService<Employee, Long> implements IEmployeeService {

    /*public static EmployeeService init(ApplicationContext context){
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        return new EmployeeService(employeeRepository);
    }*/
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    @Autowired
    protected EmployeeService(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    public void updateEmployee(Long id, Employee newEmployee){
        log.info("Updating employee with id: {}", id.toString());
        Employee employee = employeeRepository.getReferenceById(id);
        if (employee == null)
            throw new EntityNotFoundException();

        employee.setEmployeeFirstName(newEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(newEmployee.getEmployeeLastName());
        employee.setEmployeeEmail(newEmployee.getEmployeeEmail());
        employee.setEmployeePhone(newEmployee.getEmployeePhone());
        employee.setEmployeeImage(newEmployee.getEmployeeImage());
        employee.setEmployeeFunction(newEmployee.getEmployeeFunction());

        employeeRepository.save(employee);
    }

    @Override
    public void addSkillToEmployee(Long employeeId, String skillName) {
        log.info("Adding skill {} to employee with id {}", employeeId, skillName);
        Skill skill = skillRepository.findBySkillName(skillName);
        Employee employee = employeeRepository.findById(employeeId).get();
        if(employee.getSkills().contains(skill))
            throw new RuntimeException("Employee already has the given skill");
        Set<Skill> skills = employee.getSkills();
        skills.add(skill);
        employee.setSkills(skills);
        employeeRepository.save(employee);
    }

}
