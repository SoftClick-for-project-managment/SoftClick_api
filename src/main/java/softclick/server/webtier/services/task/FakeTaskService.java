package softclick.server.webtier.services.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softclick.server.data.entities.*;
import softclick.server.webtier.services.user.IUserService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@Slf4j
@Qualifier("fakeTaskService")
public class FakeTaskService implements ITaskService {

    private final List<Task> tasks = new ArrayList<>();

    public FakeTaskService(){
        tasks.add(new Task("design",LocalDateTime.now(),LocalDateTime.now().plusHours(8),"tache tres importante",null,null,null,null,null));
        tasks.add(new Task("development",LocalDateTime.now(),LocalDateTime.now().plusHours(8),"tache tres importante",null,null,null,null,null));
        tasks.add(new Task("deployment",LocalDateTime.now(),LocalDateTime.now().plusHours(8),"tache tres importante",null,null,null,null,null));
    }

    @Override
    public void updateTask(Long id, Task newTask) {

    }

    @Override
    public List<Task> getAllByProject(Long projectId) {
        return null;
    }

    @Override
    public List<Task> getAllByEmployee(Long employeeId) {
        return null;
    }

    @Override
    public List<Task> getAllByProjectAndEmployee(Long employeeId, Long projectId) {
        return null;
    }

    @Override
    public void saveEntity(Task entity) {

    }

    @Override
    public Task findEntityByKey(Long aLong) {
        return null;
    }

    @Override
    public List<Task> getAllEntities() {
        return tasks;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }

    @Override
    public Task patch(Long aLong, Map<Object, Object> fields, Class<Task> taskClass) {
        return null;
    }
}
