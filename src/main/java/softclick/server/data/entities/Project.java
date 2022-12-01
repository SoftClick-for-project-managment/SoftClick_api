package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProject;

    @Column(name = "nameProject", nullable = false)
    private String nameProject;

    @Column(name = "descriptionProject", nullable = false)
    private String descriptionProject;

    private Double revenueProject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDomain")
    private Domain domainProjet;

    @Column(name = "dateDebut", nullable = false)
    private Date dateDebut;

    @Column(name = "dateFin", nullable = true)
    private Date dateFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private Employee chefProject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idStatus", nullable = true)
    private Status projectStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPriority", nullable = false)
    private Priority projectPriority;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
    private Set<Invoice> invoices = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
    private Set<Task> tasks = new HashSet<>();


}
