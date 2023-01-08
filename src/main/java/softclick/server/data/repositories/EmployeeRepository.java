package softclick.server.data.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Skill;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Employee findByEmployeeFirstName(String employeeFirstName);

    Employee findByEmployeeLastName(String employeeLastName);

    @Query("SELECT e FROM Employee as e WHERE e.employeeFirstName like :firstName and e.employeeLastName like :lastName  and" +
            " e.employeeFunction like :function and ( :skill is null or :skill in elements(e.skills) )")
    List<Employee> searchEmploye(String firstName , String lastName , String function , Skill skill);




}
