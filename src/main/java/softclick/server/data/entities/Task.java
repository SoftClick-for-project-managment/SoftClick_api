package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
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
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private Collection<Expense> expenses;
}
