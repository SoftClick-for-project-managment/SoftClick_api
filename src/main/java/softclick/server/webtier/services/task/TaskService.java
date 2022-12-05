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

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@Slf4j
@Qualifier("rmiTaskService")
public class TaskService extends BaseService<Task, Long> implements ITaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;
    private final PriorityRepository priorityRepository;
    private final EmployeeRepository employeeRepository
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
        if (entity.getStartDate().compareTo(entity.getEndDate()) != -1)
            throw new RuntimeException("Start date can't be greater than end date");
        super.saveEntity(entity);
    }

    @Override
    public void updateTask(Long id, Task newTask) {
        Task task = taskRepository.getReferenceById(id);
        if (task == null)
            throw new EntityNotFoundException();
        if (newTask.getStartDate().isBefore(newTask.getEndDate()))
            throw new RuntimeException("Start date can't be greater than end date");
        if (newTask.getProject() != null)
            task.setProject(projectRepository.getReferenceById(newTask.getProject().getIdProject()));
        if (newTask.getStatus() != null)
            task.setStatus(statusRepository.getReferenceById(newTask.getStatus().getIdStatus()));
        if (newTask.getPriority() != null)
            task.setPriority(priorityRepository.getReferenceById(newTask.getPriority().getIdPriority()));
        if (newTask.getEmployee() != null)
            task.setEmployee(employeeRepository.getReferenceById(newTask.getEmployee().getId()));
        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());
        task.setStartDate(newTask.getStartDate());
        task.setEndDate(newTask.getEndDate());
        taskRepository.save(task);
    }
}
