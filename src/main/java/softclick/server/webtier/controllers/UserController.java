package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.user.IUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(@Qualifier("rmiUserService") IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Object> index(){
        try{
            List<User> users = userService.getAllEntities();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            User user = userService.findEntityByKey(Long.valueOf(id));
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> create(@RequestBody User user){
        try{
            userService.saveEntity(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/users")
    public ResponseEntity<Object> update(@RequestBody User user){
        try{
            User storedUser = userService.findEntityByKey(user.getId());
            if (storedUser == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            System.out.println(user);
//            userService.saveEntity(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            userService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
