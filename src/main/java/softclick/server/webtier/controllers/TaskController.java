package softclick.server.webtier.controllers;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Role;
import softclick.server.data.entities.Task;
import softclick.server.data.entities.User;
import softclick.server.webtier.dtos.Task.TaskCreateAndUpdateDto;
import softclick.server.webtier.dtos.Task.TaskListAndSingleDto;
import softclick.server.webtier.services.task.ITaskService;
import softclick.server.webtier.services.user.IUserService;
import softclick.server.webtier.utils.exceptions.BusinessException;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;
import softclick.server.webtier.utils.time.DateTimeConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static softclick.server.webtier.security.SecurityConfig.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TaskController {

    private final ITaskService taskService;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(@Qualifier("rmiTaskService") ITaskService taskService,
                          @Qualifier("rmiUserService") IUserService userService,
                          ModelMapper modelMapper) {
        this.taskService = taskService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(Task.class, TaskListAndSingleDto.class)
                .addMappings(mapper -> {
                    mapper.using(ctx -> DateTimeConverter.toString((LocalDateTime) ctx.getSource())).map(Task::getStartDate, TaskListAndSingleDto::setStartDate);
                    mapper.using(ctx -> DateTimeConverter.toString((LocalDateTime) ctx.getSource())).map(Task::getEndDate, TaskListAndSingleDto::setEndDate);
                    mapper.map(src -> src.getProject().getIdProject(), TaskListAndSingleDto::setProjectId);
                });
        this.modelMapper.createTypeMap(TaskCreateAndUpdateDto.class, Task.class)
                .addMappings(mapper -> {
                    mapper.using(ctx -> DateTimeConverter.valueOf((String) ctx.getSource())).map(src -> src.getStartDate(), Task::setStartDate);
                    mapper.using(ctx -> DateTimeConverter.valueOf((String) ctx.getSource())).map(src -> src.getEndDate(), Task::setEndDate);
                })
                .addMappings(new PropertyMap<TaskCreateAndUpdateDto, Task>() {
                    @Override
                    protected void configure() {
                        map().setId(source.getProjectId());
                    }
                });
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<Object> index(@RequestParam(value = "pid", required = false) String projectId) {
        try{
           /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getUserByUsername(authentication.getPrincipal().toString());
            ArrayList<String> userRoleList = (ArrayList<String>) user.getRoles().stream().map(s -> s.getName()).collect(Collectors.toList());*/
            List<Task> tasks = new ArrayList<>();

            /*if (projectId == null) {
                if ( userRoleList.contains(ROLE_ADMIN) || userRoleList.contains(ROLE_DIRECTOR) ) {*/
                    tasks = taskService.getAllEntities();
               /* } else if ( userRoleList.contains(ROLE_EMPLOYEE) ) {
                    tasks = taskService.getAllByEmployee(user.getEmployee().getId());
                }
            } else {
                if ( userRoleList.contains(ROLE_EMPLOYEE) ) {
                    tasks = taskService.getAllByProjectAndEmployee(user.getEmployee().getId(), Long.valueOf(projectId));
                } else if (userRoleList.contains(ROLE_DIRECTOR) || userRoleList.contains(ROLE_PROJECT_MANAGER) ) {
                    tasks = taskService.getAllByProject(Long.valueOf(projectId));
                }
            }*/
            List<TaskListAndSingleDto> taskListDto = tasks.stream().map(t -> modelMapper.map(t, TaskListAndSingleDto.class)).collect(Collectors.toList());
            return new ResponseEntity<>(taskListDto, HttpStatus.OK);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Task task = taskService.findEntityByKey(Long.valueOf(id));
            TaskListAndSingleDto taskDto = modelMapper.map(task, TaskListAndSingleDto.class);

            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        }catch(DataNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(BusinessException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/tasks")
    public ResponseEntity<Object> create(@RequestBody TaskCreateAndUpdateDto taskDto){
        try{
            Task task = modelMapper.map(taskDto, Task.class);
            taskService.saveEntity(task);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(DataNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(BusinessException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody TaskCreateAndUpdateDto taskDto){
        try{
            Task task = modelMapper.map(taskDto, Task.class);
            taskService.updateTask(Long.valueOf(id), task);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(DataNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(BusinessException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            taskService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(DataNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(BusinessException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
