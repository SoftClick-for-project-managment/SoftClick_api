package softclick.server.webtier.services.task;

import softclick.server.data.entities.Task;
import softclick.server.webtier.services.IBaseService;

import java.util.List;

public interface ITaskService extends IBaseService<Task, Long> {
    void updateTask(Long id, Task newTask);
    List<Task> getAllByProject(Long projectId);
}
