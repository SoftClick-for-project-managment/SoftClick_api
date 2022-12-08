package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Domain;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.Status;
import softclick.server.webtier.services.domain.IDomainService;
import softclick.server.webtier.services.project.IProjectService;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class DomainController {
    private final IDomainService domainService;

    @Autowired
    public DomainController(@Qualifier("rmiDomainService") IDomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping(value = "/domains")
    public ResponseEntity<Object> index(){
        try{
            List<Domain> domains = domainService.getAllEntities();
            return new ResponseEntity<>(domains, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/domains/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Domain domain = domainService.findEntityByKey(Long.valueOf(id));
            if (domain == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(domain, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/domains")
    public ResponseEntity<Object> create(@RequestBody Domain domain){
        try{
            domainService.saveEntity(domain);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/domains")
    public ResponseEntity<Object> update(@RequestBody Domain domain){
        try{
            Domain storeddomain = domainService.findEntityByKey(domain.getIdDomain());
            if (storeddomain == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            System.out.println(domain);
            domainService.saveEntity(domain);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value = "/domains/{id}")
    public ResponseEntity<Object> patche(@PathVariable Long id , @RequestBody Map<Object,Object> fields){

        try{
            Domain storedDomain = domainService.patch(id,fields,Domain.class);
            if (storedDomain == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            domainService.saveEntity(storedDomain);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/domains/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            domainService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
