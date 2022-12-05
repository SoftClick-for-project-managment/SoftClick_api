

package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Priority;
import softclick.server.webtier.services.priority.IPriorityService;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class PriorityController {
    private final IPriorityService priorityService;

    @Autowired
    public PriorityController(@Qualifier("rmiPriorityService") IPriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping(value = "/priorities")
    public ResponseEntity<Object> index() {
        try {
            List<Priority> priorities = priorityService.getAllEntities();
            return new ResponseEntity<>(priorities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/priorities/{id}")
    public ResponseEntity<Object> single(@PathVariable String id) {
        try {
            Priority priority = priorityService.findEntityByKey(Long.valueOf(id));
            if (priority == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(priority, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/priorities")
    public ResponseEntity<Object> create(@RequestBody Priority priority) {
        try {
            priorityService.saveEntity(priority);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/priorities")
    public ResponseEntity<Object> update(@RequestBody Priority priority) {
        try {
            Priority storedpriority = priorityService.findEntityByKey(priority.getIdPriority());
            if (storedpriority == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            System.out.println(priority);
            priorityService.saveEntity(priority);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/priorities/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try {
            priorityService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
