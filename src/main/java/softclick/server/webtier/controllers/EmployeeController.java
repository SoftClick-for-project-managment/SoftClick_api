package softclick.server.webtier.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Employee;
import softclick.server.webtier.dtos.Empoyee.EmployeeCreateAndUpdateDto;
import softclick.server.webtier.dtos.Empoyee.EmployeeListAndSingleDto;
import softclick.server.webtier.services.employee.IEmployeeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(@Qualifier("rmiEmployeeService") IEmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
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
            EmployeeListAndSingleDto employeeDto = modelMapper.map(employee, EmployeeListAndSingleDto.class);
            if (employee == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Object> create(@RequestBody EmployeeCreateAndUpdateDto employeeDto){
        try{
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            employeeService.saveEntity(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody EmployeeCreateAndUpdateDto employeeDto){
        try{
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            employeeService.updateEmployee(Long.valueOf(id), employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
