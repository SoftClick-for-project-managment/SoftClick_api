package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployeeFirstName(String employeeFirstName);

    Employee findByEmployeeLastName(String employeeLastName);
}
