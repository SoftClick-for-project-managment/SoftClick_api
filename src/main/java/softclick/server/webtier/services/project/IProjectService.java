package softclick.server.webtier.services.project;

import softclick.server.data.entities.Project;
import softclick.server.webtier.services.IBaseService;

import java.util.List;
import java.util.Map;

public interface IProjectService extends IBaseService<Project, Long> {
    Project patch(Long id_project , Map<Object,Object> fields);
    public void updateProject(Long id, Project newProject);
    List<Project> serachProject(Long id_domain,Long id_chef,Long id_status,Long id_priority, String name_project);
}
