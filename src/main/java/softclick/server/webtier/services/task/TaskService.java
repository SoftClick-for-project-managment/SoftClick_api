package softclick.server.webtier.services.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.webtier.services.BaseService;
@Service
@Transactional
@Slf4j
@Qualifier("rmiTaskService")
public class TaskService extends BaseService<Task, Long> implements ITaskService {



    protected TaskService(TaskRepository repository) {
        super(repository);
    }
}
