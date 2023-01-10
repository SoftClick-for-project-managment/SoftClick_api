package softclick.server.webtier.services.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.Skill;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.data.repositories.SkillRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
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
            throw new DataNotFoundException("Employee not found");

        if(newEmployee.getEmployeeFirstName() != null)
            employee.setEmployeeFirstName(newEmployee.getEmployeeFirstName());
        if(newEmployee.getEmployeeLastName() != null)
            employee.setEmployeeLastName(newEmployee.getEmployeeLastName());
        if(newEmployee.getEmployeeEmail() != null)
            employee.setEmployeeEmail(newEmployee.getEmployeeEmail());
        if(newEmployee.getEmployeePhone() != null)
            employee.setEmployeePhone(newEmployee.getEmployeePhone());
        if(newEmployee.getEmployeeImage() != null)
            employee.setEmployeeImage(newEmployee.getEmployeeImage());
        if(newEmployee.getEmployeeFunction() != null)
            employee.setEmployeeFunction(newEmployee.getEmployeeFunction());
        if(newEmployee.getSkills() != null)
            employee.setSkills(newEmployee.getSkills());

        employeeRepository.save(employee);
    }

    public Employee saveEmployee(Employee newEmployee) {
        log.info("Saving new entity of type " + newEmployee.getClass().toString());
        return repository.save(newEmployee);
    }

    @Override
    public Employee findByEmployeeFirstName(String employeeFirstName) {
        return employeeRepository.findByEmployeeFirstName(employeeFirstName);
    }

    @Override
    public Employee findByEmployeeLastName(String employeeLastName) {
        return employeeRepository.findByEmployeeLastName(employeeLastName);
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
    public List<Employee> serachEmploye(String firstName , String lastName , String function , Skill skill){
        List<Employee> filteredEmployees = employeeRepository.searchEmploye(firstName , lastName , function ,  skill);
        return  filteredEmployees;
    }

}
