package softclick.server.webtier.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.Status;
import softclick.server.webtier.services.project.IProjectService;
import softclick.server.webtier.services.status.IStatusService;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class StatusController {
    private final IStatusService statusService;

    @Autowired
    public StatusController(@Qualifier("rmiStatusService") IStatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(value = "/status")
    public ResponseEntity<Object> index() {
        try {
            List<Status> status = statusService.getAllEntities();
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<Object> single(@PathVariable String id) {
        try {
            Status status = statusService.findEntityByKey(Long.valueOf(id));
            if (status == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/status")
    public ResponseEntity<Object> create(@RequestBody Status status) {
        try {
            statusService.saveEntity(status);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/status")
    public ResponseEntity<Object> update(@RequestBody Status status) {
        try {
            Status storedStatus = statusService.findEntityByKey(status.getIdStatus());
            if (storedStatus == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            System.out.println(status);
            statusService.saveEntity(status);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/status/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try {
            statusService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
