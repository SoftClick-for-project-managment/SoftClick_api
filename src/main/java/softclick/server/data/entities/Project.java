package softclick.server.data.entities;


import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "project")
public class Project implements Serializable, Comparable<Project> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Project(Long idProject, String nameProject, String descriptionProject, Double revenueProject, Domain domainProjet, Date dateDebut, Date dateFin, Employee chefProject, Status projectStatus, Priority projectPriority, Set<Invoice> invoices, Set<Task> tasks) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.descriptionProject = descriptionProject;
        this.revenueProject = revenueProject;
        this.domainProjet = domainProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.chefProject = chefProject;
        this.projectStatus = projectStatus;
        this.projectPriority = projectPriority;
        this.invoices = invoices;
        this.tasks = tasks;
    }

    public Project( String nameProject, String descriptionProject, Double revenueProject, Domain domainProjet, Date dateDebut, Date dateFin, Employee chefProject, Status projectStatus, Priority projectPriority) {
        this.nameProject = nameProject;
        this.descriptionProject = descriptionProject;
        this.revenueProject = revenueProject;
        this.domainProjet = domainProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.chefProject = chefProject;
        this.projectStatus = projectStatus;
        this.projectPriority = projectPriority;
    }

    @Override
    public int compareTo(Project project) {
        return Comparator.comparing(Project::getNameProject)
                .thenComparing(Project::getDescriptionProject)
                .thenComparing(Project::getRevenueProject)
                .thenComparing(Project::getDomainProjet)
                .thenComparing(Project::getDateDebut)
                .thenComparing(Project::getDateFin)
                .thenComparing(Project::getChefProject)
                .thenComparing(Project::getProjectStatus)
                .thenComparing(Project::getProjectPriority)
                .compare(this, project);
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getDescriptionProject() {
        return descriptionProject;
    }

    public void setDescriptionProject(String descriptionProject) {
        this.descriptionProject = descriptionProject;
    }

    public Double getRevenueProject() {
        return revenueProject;
    }

    public void setRevenueProject(Double revenueProject) {
        this.revenueProject = revenueProject;
    }

    public Domain getDomainProjet() {
        return domainProjet;
    }

    public void setDomainProjet(Domain domainProjet) {
        this.domainProjet = domainProjet;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Employee getChefProject() {
        return chefProject;
    }

    public void setChefProject(Employee chefProject) {
        this.chefProject = chefProject;
    }

    public Status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Status projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Priority getProjectPriority() {
        return projectPriority;
    }

    public void setProjectPriority(Priority projectPriority) {
        this.projectPriority = projectPriority;
    }


    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }


    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
