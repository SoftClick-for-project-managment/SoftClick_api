package softclick.server.webtier.services.priority;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Priority;
import softclick.server.data.entities.Project;
import softclick.server.data.repositories.PriorityRepository;
import softclick.server.webtier.services.BaseService;

import java.util.Map;


@Service
@Slf4j
@Qualifier("rmiPriorityService")
public class PriorityService extends BaseService<Priority, Long> implements IPriorityService {
    private final PriorityRepository priorityRepository;
    @Autowired
    protected PriorityService(PriorityRepository priorityRepository) {
        super(priorityRepository);
        this.priorityRepository = priorityRepository;

    }




    @Override
    public Priority patch(Long id_priority , Map<Object,Object> fields){
        Priority priority = super.patch(id_priority, fields,Priority.class);
        if(priority != null){
            saveEntity( priority);
            return priority;
        }
        return  null;
    }
}
