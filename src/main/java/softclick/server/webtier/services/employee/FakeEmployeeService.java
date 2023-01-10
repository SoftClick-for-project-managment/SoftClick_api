package softclick.server.webtier.services.employee;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service @Qualifier("rmiFakeEmployeeService")
public class FakeEmployeeService implements IEmployeeService{

    private final List<Employee> employees = new ArrayList<>();
    public FakeEmployeeService() {
        employees.add(new Employee(null, "Tiger", "Nixon", "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675"));
        employees.add(new Employee(null, "Garrett", "Winters", "FullStack Developer", "garrettwinters@gmail.com", "+2120065354675"));
        employees.add(new Employee(null, "Brielle", "Williamson", "Front-End Developer", "briellewilliamson@gmail.com", "+2120065354675"));
    }
    @Override
    public void saveEntity(Employee entity) {

    }

    @Override
    public Employee findEntityByKey(Long aLong) {
        return employees.get(aLong.intValue());
    }

    @Override
    public List<Employee> getAllEntities() {
        return employees;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }

    @Override
    public Employee patch(Long aLong, Map<Object, Object> fields, Class<Employee> employeeClass) {
        return null;
    }

    @Override
    public void updateEmployee(Long id, Employee newEmployee) {

    }

    @Override
    public Employee saveEmployee(Employee newEmployee) {
        return null;
    }

    @Override
    public Employee findByEmployeeFirstName(String employeeFirstName) {
        return null;
    }

    @Override
    public Employee findByEmployeeLastName(String employeeLastName) {
        return null;
    }

    @Override
    public void addSkillToEmployee(Long employeeId, String skillName) {

    }

    @Override
    public List<Employee> serachEmploye(String firstName, String lastName, String function, Skill skill) {
        return null;
    }
}
