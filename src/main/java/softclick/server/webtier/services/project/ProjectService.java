package softclick.server.webtier.services.project;

import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import softclick.server.data.entities.Project;

import softclick.server.data.entities.Task;
import softclick.server.data.repositories.ProjectRepository;

import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.utils.exceptions.BusinessException;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service @Slf4j @Qualifier("rmiProjectService")
public class ProjectService extends BaseService<Project, Long> implements IProjectService {

    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;

    }

    @Override
    public void saveEntity(Project entity) {
        if (entity.getDateDebut().compareTo(entity.getDateFin())>0)
            throw new RuntimeException("Start date can't be greater than end date");
        super.saveEntity(entity);
    }


    @Override
    public Project patch(Long id_project , Map<Object,Object> fields){
        Project project = super.patch(id_project, fields,Project.class);
        if(project != null){
            saveEntity( project);
            return project;
        }
        return  null;
    }
    @Override
    public void updateProject(Long id, Project newProject) {
        Project project = projectRepository.getReferenceById(id);
        if (project == null)
            throw new DataNotFoundException("Project not found");
        if (newProject.getDateDebut() != null && newProject.getDateFin() != null){
            if (newProject.getDateDebut().compareTo(newProject.getDateFin())>0)
                throw new BusinessException("Start date can't be greater than end date");
            project.setDateDebut(newProject.getDateDebut() != null ? newProject.getDateDebut() : project.getDateDebut());
            project.setDateFin(newProject.getDateFin() != null ? newProject.getDateFin() : project.getDateFin());
        }
        if (newProject.getInvoices() != null)
            project.setInvoices(newProject.getInvoices() );
        if (newProject.getTasks() != null)
            project.setTasks(newProject.getTasks() );
        if (newProject.getProjectStatus() != null)
            project.setProjectStatus(newProject.getProjectStatus());

        project.setNameProject(newProject.getNameProject() != null ? newProject.getNameProject() : project.getNameProject());
        project.setDescriptionProject(newProject.getDescriptionProject() != null ? newProject.getDescriptionProject(): project.getDescriptionProject());
        project.setDomainProjet(newProject.getDomainProjet() != null ? newProject.getDomainProjet(): project.getDomainProjet());
        project.setProjectPriority(newProject.getProjectPriority() != null ? newProject.getProjectPriority(): project.getProjectPriority());
        project.setRevenueProject(newProject.getRevenueProject() != null ? newProject.getRevenueProject(): project.getRevenueProject());
        project.setChefProject(newProject.getChefProject() != null ? newProject.getChefProject(): project.getChefProject());

        projectRepository.save(project);
    }
    public List<Project> serachProject(Long id_domain,Long id_chef,Long id_status,Long id_priority, String name_project){
        List<Project> filteredProjects = projectRepository.serachProject(id_domain,id_chef,id_status,id_priority, name_project);
        return  filteredProjects;
    }
  /*  private Specification<Project> nameProjectlike(String name){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("nameProject"), "%"+name+"%");
    }
    private Specification<Project> doaminEqualTo(Long id_domain){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("domainProjet.nameDomain"), id_domain);
    }*/


}
