package softclick.server.webtier.services.task;

import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.services.auth.AuthService;

public class TaskService extends BaseService<Task, Long> implements ITaskService {

    public static TaskService init(ApplicationContext context){
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        return new TaskService(taskRepository);
    }

    protected TaskService(TaskRepository repository) {
        super(repository);
    }
}
