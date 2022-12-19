package softclick.server.webtier.services.project;

import softclick.server.data.entities.Project;
import softclick.server.webtier.services.IBaseService;

import java.util.Map;

public interface IProjectService extends IBaseService<Project, Long> {
    Project patch(Long id_project , Map<Object,Object> fields);
}
