package softclick.server.webtier.services.employee;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Skill;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.data.repositories.SkillRepository;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.builder.CompareToBuilder.reflectionCompare;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock private EmployeeRepository employeeRepository;

    @Mock private SkillRepository skillRepository;

    private IEmployeeService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new EmployeeService(employeeRepository, skillRepository);
    }

    @Test
    void canGetAllEmployees() {
        // When
        serviceUnderTest.getAllEntities();
        // Then
        verify(employeeRepository).findAll();
    }

    @Test
    void itShouldVerifyEmployeeAdded() {
        // Given
        Employee employee = new Employee(
                null,
                "Tiger",
                "Nixon" ,
                "Talent Acquisition Specialist",
                "tigernixon@gmail.com",
                "+2120065354675");

        // When
        serviceUnderTest.saveEntity(employee);

        // Then
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(employee);

    }

    @Test
    void itShouldVerifyEmployeeImageUpdated() {
        // Given
        Employee newEmployee = new Employee("Image.png", null, null, null, null, null);
        Employee employee = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        employee.setId(1L);
        Employee oldEmployeeCopy = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        oldEmployeeCopy.setId(1L);
        given(employeeRepository.getReferenceById(1L)).willReturn(employee);

        // When
        serviceUnderTest.updateEmployee(1L, newEmployee);

        // Then
        verify(employeeRepository).save(any());
        assertThat(employee.getEmployeeImage()).isEqualTo(newEmployee.getEmployeeImage());
        assertThat(reflectionCompare(employee, oldEmployeeCopy, "employeeImage")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyEmployeeFirstNameUpdated() {
        // Given
        Employee newEmployee = new Employee(null, "Hajar", null, null, null, null);
        Employee employee = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        employee.setId(1L);
        Employee oldEmployeeCopy = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        oldEmployeeCopy.setId(1L);
        given(employeeRepository.getReferenceById(1L)).willReturn(employee);

        // When
        serviceUnderTest.updateEmployee(1L, newEmployee);

        // Then
        verify(employeeRepository).save(any());
        assertThat(employee.getEmployeeFirstName()).isEqualTo(newEmployee.getEmployeeFirstName());
        assertThat(reflectionCompare(employee, oldEmployeeCopy, "employeeFirstName")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyEmployeeLastNameUpdated() {
        // Given
        Employee newEmployee = new Employee(null, null, "FAIZ", null, null, null);
        Employee employee = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        employee.setId(1L);
        Employee oldEmployeeCopy = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        oldEmployeeCopy.setId(1L);
        given(employeeRepository.getReferenceById(1L)).willReturn(employee);

        // When
        serviceUnderTest.updateEmployee(1L, newEmployee);

        // Then
        verify(employeeRepository).save(any());
        assertThat(employee.getEmployeeLastName()).isEqualTo(newEmployee.getEmployeeLastName());
        assertThat(reflectionCompare(employee, oldEmployeeCopy, "employeeLastName")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyEmployeeFunctionUpdated() {
        // Given
        Employee newEmployee = new Employee(null, null, null, "Function1", null, null);
        Employee employee = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        employee.setId(1L);
        Employee oldEmployeeCopy = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        oldEmployeeCopy.setId(1L);
        given(employeeRepository.getReferenceById(1L)).willReturn(employee);

        // When
        serviceUnderTest.updateEmployee(1L, newEmployee);

        // Then
        verify(employeeRepository).save(any());
        assertThat(employee.getEmployeeFunction()).isEqualTo(newEmployee.getEmployeeFunction());
        assertThat(reflectionCompare(employee, oldEmployeeCopy, "employeeFunction")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyEmployeeEmailUpdated() {
        // Given
        Employee newEmployee = new Employee(null, null, null, null, "hajar@gmail.com", null);
        Employee employee = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        employee.setId(1L);
        Employee oldEmployeeCopy = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        oldEmployeeCopy.setId(1L);
        given(employeeRepository.getReferenceById(1L)).willReturn(employee);

        // When
        serviceUnderTest.updateEmployee(1L, newEmployee);

        // Then
        verify(employeeRepository).save(any());
        assertThat(employee.getEmployeeEmail()).isEqualTo(newEmployee.getEmployeeEmail());
        assertThat(reflectionCompare(employee, oldEmployeeCopy, "employeeEmail")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyEmployeePhoneUpdated() {
        // Given
        Employee newEmployee = new Employee(null, null, null, null, null, "06556748839");
        Employee employee = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        employee.setId(1L);
        Employee oldEmployeeCopy = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        oldEmployeeCopy.setId(1L);
        given(employeeRepository.getReferenceById(1L)).willReturn(employee);

        // When
        serviceUnderTest.updateEmployee(1L, newEmployee);

        // Then
        verify(employeeRepository).save(any());
        assertThat(employee.getEmployeePhone()).isEqualTo(newEmployee.getEmployeePhone());
        assertThat(reflectionCompare(employee, oldEmployeeCopy, "employeePhone")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyEmployeeSkillsUpdated() {
        // Given
        Employee newEmployee = new Employee(null, null, null, null, null, null);
        Set<Skill> newSkill = new HashSet<>();
        newSkill.add(new Skill(1L, "PHP"));
        newSkill.add(new Skill(2L, "ANGULAR"));
        newEmployee.setSkills(newSkill);
        Employee employee = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        employee.setId(1L);
        Employee oldEmployeeCopy = new Employee(null, "Tiger", "Nixon" , "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
        oldEmployeeCopy.setId(1L);
        given(employeeRepository.getReferenceById(1L)).willReturn(employee);

        // When
        serviceUnderTest.updateEmployee(1L, newEmployee);

        // Then
        verify(employeeRepository).save(any());
        assertThat(employee.getSkills()).isEqualTo(newEmployee.getSkills());
        assertThat(reflectionCompare(employee, oldEmployeeCopy, "skills")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyEmployeeWasNotUpdated_employeeNotFoundException() {
        // given
        Employee newEmployee = new Employee(
                null,
                null,
                null,
                null,
                "hajar@gmail.com",
                null);

        given(employeeRepository.getReferenceById(1L))
                .willReturn(null);

        // when and then
        AssertionsForClassTypes.assertThatThrownBy(() -> serviceUnderTest.updateEmployee(1L, newEmployee))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Employee not found");

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void canDeleteEmployee() {
        // given
        Employee employee = new Employee(
                null,
                "Tiger",
                "Nixon" ,
                "Talent Acquisition Specialist",
                "tigernixon@gmail.com",
                "+2120065354675");
        employee.setId(1L);

        // when
        serviceUnderTest.deleteEntity(1L);

        // then
        verify(employeeRepository).deleteById(1L);
    }

}
