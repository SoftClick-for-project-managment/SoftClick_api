package softclick.server.webtier.services.project;

import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import softclick.server.data.entities.Project;

import softclick.server.data.entities.Task;
import softclick.server.data.repositories.ProjectRepository;

import softclick.server.webtier.services.BaseService;


import java.lang.reflect.Field;
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

}
