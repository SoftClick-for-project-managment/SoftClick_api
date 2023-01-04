package softclick.server.data.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

@Entity
@NoArgsConstructor
//@Data
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Team_Employee",
            joinColumns = @JoinColumn( name = "idEmployee" ),
            inverseJoinColumns = @JoinColumn( name = "idTeam" ))
    private Set<Team> teams;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFunction() {
        return employeeFunction;
    }

    public void setEmployeeFunction(String employeeFunction) {
        this.employeeFunction = employeeFunction;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    @JsonIgnoreProperties("members")
    public Set<Team> getTeams() {
        return teams;
    }

}
