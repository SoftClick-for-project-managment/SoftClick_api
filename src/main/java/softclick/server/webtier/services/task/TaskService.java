package softclick.server.webtier.services.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.*;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.utils.exceptions.BusinessException;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import java.util.List;

@Service
@Transactional
@Slf4j
@Qualifier("rmiTaskService")
public class TaskService extends BaseService<Task, Long> implements ITaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;
    private final PriorityRepository priorityRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    protected TaskService(TaskRepository taskRepository,
                          ProjectRepository projectRepository,
                          StatusRepository statusRepository,
                          PriorityRepository priorityRepository,
                          EmployeeRepository employeeRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
        this.priorityRepository = priorityRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEntity(Task entity) {
        if (entity.getStartDate().isAfter(entity.getEndDate()))
            throw new BusinessException("Start date can't be greater than end date");
        super.saveEntity(entity);
    }

    @Override
    public void updateTask(Long id, Task newTask) {
        Task task = taskRepository.getReferenceById(id);
        if (task == null)
            throw new DataNotFoundException("Task not found");
        if (newTask.getStartDate() != null && newTask.getEndDate() != null){
            if (newTask.getStartDate().isAfter(newTask.getEndDate()))
                throw new BusinessException("Start date can't be greater than end date");
            task.setStartDate(newTask.getStartDate() != null ? newTask.getStartDate() : task.getStartDate());
            task.setEndDate(newTask.getEndDate() != null ? newTask.getEndDate() : task.getEndDate());
        }
        if (newTask.getProject() != null)
            task.setProject(projectRepository.getReferenceById(newTask.getProject().getIdProject()));
        if (newTask.getStatus() != null)
            task.setStatus(statusRepository.getReferenceById(newTask.getStatus().getIdStatus()));
        if (newTask.getPriority() != null)
            task.setPriority(priorityRepository.getReferenceById(newTask.getPriority().getIdPriority()));
        if (newTask.getEmployee() != null)
            task.setEmployee(employeeRepository.getReferenceById(newTask.getEmployee().getId()));
        task.setName(newTask.getName() != null ? newTask.getName() : task.getName());
        task.setDescription(newTask.getDescription() != null ? newTask.getDescription() : task.getDescription());
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllByProject(Long projectId) {
        return taskRepository.getByProjectPage(projectRepository.getReferenceById(projectId), null).getContent();
    }
}
