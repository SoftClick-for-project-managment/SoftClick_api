package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.user.IUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final IUserService userService;

    @Autowired
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<Object> create(@RequestBody User user){
//        try{
//            Optional<User> storedUser = userService.;
//            userService.saveEntity(user);
//            if (storedUser.isEmpty())
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }catch(Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody User user){
        try{
//            Optional<User> storedUser = userService.findEntityByKey(Long.valueOf(id));
            Optional<User> storedUser = userService.findEntityByKey(user.getId());
            if (storedUser.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            userService.saveEntity(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
