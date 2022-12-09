package softclick.server.webtier.services.priority;

import softclick.server.data.entities.Priority;
import softclick.server.data.entities.Project;
import softclick.server.webtier.services.IBaseService;

import java.util.Map;

public interface IPriorityService extends IBaseService<Priority, Long> {
    public Priority patch(Long id_priority , Map<Object,Object> fields);
}
