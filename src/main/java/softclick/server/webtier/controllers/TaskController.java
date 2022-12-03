package softclick.server.webtier.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Task;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.client.ClientService;
import softclick.server.webtier.services.client.IClientService;
import softclick.server.webtier.services.task.ITaskService;
import softclick.server.webtier.services.user.IUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final ITaskService taskService;

    @Autowired
    public TaskController(@Qualifier("rmiTaskService") ITaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<Object> index(){
        try{
            List<Task> tasks = taskService.getAllEntities();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Task task = taskService.findEntityByKey(Long.valueOf(id));
            if (task == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(task, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Task task){
        try{
            taskService.saveEntity(task);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Task task){
        try{
            Task storedTask = taskService.findEntityByKey(task.getId());
//            Optional<Client> storedClient = clientService.findEntityByKey(Long.valueOf(id));

            if (storedTask == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            taskService.saveEntity(task);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            taskService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
