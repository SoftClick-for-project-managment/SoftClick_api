package softclick.server.data.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Employee findByEmployeeFirstName(String employeeFirstName);

    Employee findByEmployeeLastName(String employeeLastName);

    private Specification<Client> firstNameEmployeLike(String employeeFirstName){
        if (employeeFirstName == null || employeeFirstName.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("employeeFirstName"), "%"+employeeFirstName+"%");
    }
    private Specification<Client> lastNameEmployeLike(String employeeLastName){
        if (employeeLastName == null || employeeLastName.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("employeeLastName"), "%"+employeeLastName+"%");
    }

    private Specification<Client> functionEmployeLike(String employeeFunction){
        if (employeeFunction == null || employeeFunction.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("employeeFunction"), "%"+employeeFunction+"%");
    }


}
