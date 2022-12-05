package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import softclick.server.data.entities.Expense;
import softclick.server.webtier.services.expense.IExpenseService;


import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final IExpenseService expenseService;

    @Autowired
    public ExpenseController(@Qualifier("rmiExpenseService") IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(value = "/expense")
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
            Expense expense = expenseService.findEntityByKey(Long.valueOf(id));
            if (expense == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/expenses")
    public ResponseEntity<Object> create(@RequestBody Expense expense){
        try{
            expenseService.saveEntity(expense);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/expenses")
    public ResponseEntity<Object> update(@RequestBody Expense expense){
        try{
            Expense storedExpense= expenseService.findEntityByKey(expense.getId());
            if (storedExpense == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            expenseService.saveEntity(expense);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/expenses/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            expenseService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
