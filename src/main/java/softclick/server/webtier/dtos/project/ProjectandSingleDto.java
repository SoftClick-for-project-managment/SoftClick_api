package softclick.server.webtier.dtos.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import softclick.server.data.entities.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProjectandSingleDto {


    private Long idProject;
    private String nameProject;
    private String descriptionProject;
    private Double revenueProject;
    private Domain domainProjet;
    private Date dateDebut;
    private Date dateFin;
    private Employee chefProject;
    private Status projectStatus;
    private Priority projectPriority;
    private Set<Invoice> invoices = new HashSet<>();

        public ProjectandSingleDto() {
        }

        public ProjectandSingleDto(Long idProject, String nameProject, String descriptionProject, Double revenueProject, Domain domainProjet, Date dateDebut, Date dateFin, Employee chefProject, Status projectStatus, Priority projectPriority, Set<Invoice> invoices) {
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


    @JsonIgnoreProperties("idProject")
        public Set<Invoice> getInvoices() {
                return invoices;
        }

        public void setInvoices(Set<Invoice> invoices) {
                this.invoices = invoices;
        }


}
