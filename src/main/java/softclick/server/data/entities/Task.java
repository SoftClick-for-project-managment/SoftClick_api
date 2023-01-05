package softclick.server.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;

@Entity
@NoArgsConstructor
@Proxy(lazy = false)
public class Task implements Serializable, Comparable<Task> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "idStatus")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "idProject")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "idPriority")
    private Priority priority;
    @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "task", fetch = FetchType.EAGER)
    private Set<Expense> expenses;

    public Task(String name, LocalDateTime startDate, LocalDateTime endDate,String description,Status status,Project project,Employee employee,Priority priority,Set<Expense> expenses){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.status = status;
        this.project = project;
        this.employee = employee;
        this.priority = priority;
        this.expenses = expenses;
    }

    @Override
    public int compareTo(Task task) {
        return Comparator.comparing(Task::getName)
                .thenComparing(Task::getDescription)
                .thenComparing(Task::getStartDate)
                .thenComparing(Task::getEndDate)
                .thenComparing(Task::getStatus)
                .thenComparing(Task::getPriority)
                .thenComparing(Task::getEmployee)
                .compare(this, task);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @JsonIgnoreProperties("task")
    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }
}
