package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Project;
import softclick.server.webtier.services.project.IProjectService;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    private final IProjectService projectService;

    @Autowired
    public ProjectController(@Qualifier("rmiProjectService") IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/projects")
    public ResponseEntity<Object> index(){
        try{
            List<Project> projects = projectService.getAllEntities();
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Project project = projectService.findEntityByKey(Long.valueOf(id));
            if (project == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(project, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/projects")
    public ResponseEntity<Object> create(@RequestBody Project project){
        try{
            projectService.saveEntity(project);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/projects")
    public ResponseEntity<Object> update(@RequestBody Project project){
        try{
            Project storedProject = projectService.findEntityByKey(project.getIdProject());
            if (storedProject == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            System.out.println(project);
            projectService.saveEntity(project);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            projectService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
