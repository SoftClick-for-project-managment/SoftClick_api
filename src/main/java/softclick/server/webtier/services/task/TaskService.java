package softclick.server.webtier.services.task;

import org.springframework.context.ApplicationContext;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.webtier.services.BaseService;

public class TaskService extends BaseService<Task, Long> implements ITaskService {

    public static TaskService init(ApplicationContext context){
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        return new TaskService(taskRepository);
    }

    protected TaskService(TaskRepository repository) {
        super(repository);
    }
}
