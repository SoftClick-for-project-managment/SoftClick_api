package softclick.server.webtier.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Expense;
import softclick.server.webtier.dtos.Expense.ExpenseCreateAndUpdateDto;
import softclick.server.webtier.dtos.Expense.ExpenseListAndSingleDto;
import softclick.server.webtier.services.expense.IExpenseService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final IExpenseService expenseService;
    private final ModelMapper modelMapper;

    @Autowired
    public ExpenseController(@Qualifier("rmiExpenseService") IExpenseService expenseService, ModelMapper modelMapper) {
        this.expenseService = expenseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/expenses")
    public ResponseEntity<Object> index(){
        try{
            List<Expense> expenses = expenseService.getAllEntities();
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/expenses/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Expense expense= expenseService.findEntityByKey(Long.valueOf(id));
            ExpenseListAndSingleDto expenseDto = modelMapper.map(expense, ExpenseListAndSingleDto.class);
            if (expense == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/expenses")
    public ResponseEntity<Object> create(@RequestBody ExpenseCreateAndUpdateDto expenseDto){
        try{
            Expense expense = modelMapper.map(expenseDto, Expense.class);
            expenseService.saveEntity(expense);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/expenses/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody ExpenseCreateAndUpdateDto expenseDto){
        try{
            Expense expense = modelMapper.map(expenseDto, Expense.class);
            expenseService.updateExpense(Long.valueOf(id), expense);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(DataNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/expenses/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{            expenseService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}