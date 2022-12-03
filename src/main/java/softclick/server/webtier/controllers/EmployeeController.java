package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Employee;
import softclick.server.webtier.services.employee.EmployeeService;
import softclick.server.webtier.services.employee.IEmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(@Qualifier("rmiEmployeeService") IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<Object> index(){
        try{
            List<Employee> employees = employeeService.getAllEntities();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Employee employee = employeeService.findEntityByKey(Long.valueOf(id));
            if (employee == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Object> create(@RequestBody Employee employee){
        try{
            employeeService.saveEntity(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/employees")
    public ResponseEntity<Object> update(@RequestBody Employee employee){
        try{
            Employee storedEmployee = employeeService.findEntityByKey(employee.getId());
            if (storedEmployee == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            //System.out.println(employee);
            employeeService.saveEntity(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            employeeService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
