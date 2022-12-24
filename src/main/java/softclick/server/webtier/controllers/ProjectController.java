package softclick.server.webtier.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Project;
import softclick.server.webtier.dtos.project.ProjectandSingleDto;
import softclick.server.webtier.services.project.IProjectService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    private final IProjectService projectService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectController(@Qualifier("rmiProjectService") IProjectService projectService, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/projects")
    public ResponseEntity<Object> index(){
        try{
            List<Project> projects = projectService.getAllEntities();
           List<ProjectandSingleDto> projectDtoList = projects.stream().map(t -> modelMapper.map(t, ProjectandSingleDto.class)).collect(Collectors.toList());
            return new ResponseEntity<>(projectDtoList, HttpStatus.OK);
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
            System.out.println("my date format is : "+project.getDateDebut().toString());
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


            projectService.saveEntity(project);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value = "/projects/{id}")
    public ResponseEntity<Object> patche(@PathVariable Long id , @RequestBody Map<Object,Object> fields){

        try{
            System.out.println(fields.toString());
            Project storedProject = projectService.patch(id,fields);
            if (storedProject == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
