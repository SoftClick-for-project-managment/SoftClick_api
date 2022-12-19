package softclick.server.webtier.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.User;
import softclick.server.webtier.dtos.User.UserCreateAndUpdateDto;
import softclick.server.webtier.dtos.User.UserListAndSingleDto;
import softclick.server.webtier.services.user.IUserService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(@Qualifier("rmiUserService") IUserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Object> index(){
        try{
            List<UserListAndSingleDto> users = userService.getAllEntities().stream().map(user -> modelMapper.map(user, UserListAndSingleDto.class)).collect(Collectors.toList());
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            User user = userService.findEntityByKey(Long.valueOf(id));
            UserListAndSingleDto userDto = modelMapper.map(user, UserListAndSingleDto.class);
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> create(@RequestBody UserCreateAndUpdateDto userDto){
        try{
            User user = modelMapper.map(userDto, User.class);
            userService.saveEntity(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UserCreateAndUpdateDto userDto){
        try{
            User user = modelMapper.map(userDto, User.class);
            userService.updateUser(Long.valueOf(id), user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(DataNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
