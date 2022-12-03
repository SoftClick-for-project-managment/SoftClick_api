package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    private Date startDate;
    private Date endDate;
    private String Description;
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

    public Task(String name, Date startDate, Date endDate,String Description,Status status,Project project,Employee employee,Priority priority,Set<Expense> expenses){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Description = Description;
        this.status = status;
        this.project = project;
        this.employee = employee;
        this.priority = priority;
        this.expenses = expenses;
    }
}
