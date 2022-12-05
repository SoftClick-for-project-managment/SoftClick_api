package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Proxy(lazy = false)
public class Task implements Serializable {
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
    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "idPriority")
    private Priority priority;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
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
}
