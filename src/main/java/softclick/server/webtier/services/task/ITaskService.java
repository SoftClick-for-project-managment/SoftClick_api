package softclick.server.webtier.services.task;

import softclick.server.data.entities.Task;
import softclick.server.webtier.services.IBaseService;

public interface ITaskService extends IBaseService<Task, Long> {
    void updateTask(Long id, Task newTask);
}
