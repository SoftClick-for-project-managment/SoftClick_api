package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Proxy(lazy=false)
public class Employee implements Serializable, Comparable<Employee> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String employeeImage;
    @Column(nullable = false)
    private String employeeFirstName;
    @Column(nullable = false)
    private String employeeLastName;
    @Column(nullable = false)
    private String employeeFunction;
    @Column(nullable = false)
    private String employeeEmail;
    @Column(nullable = false)
    private String employeePhone;
//    @OneToOne
//    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "Employee_Skill",
            joinColumns = @JoinColumn( name = "idEmployee" ),
            inverseJoinColumns = @JoinColumn( name = "idSkill" ) )
    private Set<Skill> skills;

    public Employee(String employeeImage, String employeeFirstName, String employeeLastName, String employeeFunction, String employeeEmail, String employeePhone) {
        this.employeeImage = employeeImage;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeFunction = employeeFunction;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
    }

    @Override
    public int compareTo(Employee employee) {
        return Comparator.comparing(Employee::getEmployeeImage)
                .thenComparing(Employee::getEmployeeFirstName)
                .thenComparing(Employee::getEmployeeLastName)
                .thenComparing(Employee::getEmployeeFunction)
                .thenComparing(Employee::getEmployeeEmail)
                .thenComparing(Employee::getEmployeePhone)
                .compare(this, employee);
    }
}
