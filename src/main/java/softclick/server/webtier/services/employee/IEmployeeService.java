package softclick.server.webtier.services.employee;

import softclick.server.data.entities.Employee;
import softclick.server.webtier.services.IBaseService;

public interface IEmployeeService extends IBaseService<Employee, Long> {
    void addSkillToEmployee(String employeeName, String skillName);
}
