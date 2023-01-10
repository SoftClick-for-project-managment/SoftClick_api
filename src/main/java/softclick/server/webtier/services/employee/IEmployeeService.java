package softclick.server.webtier.services.employee;

import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Skill;
import softclick.server.webtier.services.IBaseService;

import java.util.List;

public interface IEmployeeService extends IBaseService<Employee, Long> {
    void updateEmployee(Long id, Employee newEmployee);
    Employee saveEmployee(Employee newEmployee);

    Employee findByEmployeeFirstName(String employeeFirstName);

    Employee findByEmployeeLastName(String employeeLastName);
    void addSkillToEmployee(Long employeeId, String skillName);
    List<Employee> serachEmploye(String firstName , String lastName , String function , Skill skill);
}
