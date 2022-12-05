package softclick.server.webtier.services.task;

import org.springframework.beans.factory.annotation.Qualifier;
import softclick.server.data.entities.*;
import softclick.server.webtier.services.user.IUserService;

import java.util.*;

@Qualifier("rmiFakeTaskService")

public class FakeTaskService implements ITaskService {

    private List<Task> tasks = new ArrayList<>();

    public FakeTaskService(){
//        tasks.add(new Task("design",new Date(),new Date(),"tache tres importante",status,project,employee,priority,collection));
    }

    @Override
    public void updateTask(Long id, Task newTask) {

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
        return null;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }
}
