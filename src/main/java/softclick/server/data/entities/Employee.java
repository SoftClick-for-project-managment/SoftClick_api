package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Proxy(lazy=false)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private int employeeImage;
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
    @OneToOne
    private User user;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "Employee_Skill")
    private Set<Skill> skills;

    public Employee(int employeeImage, String employeeFirstName, String employeeLastName, String employeeFunction, String employeeEmail, String employeePhone) {
        this.employeeImage = employeeImage;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeFunction = employeeFunction;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
    }

}
