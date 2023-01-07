package softclick.server.webtier.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import softclick.server.data.entities.ExpenseCategory;

import softclick.server.webtier.services.expenseCategory.IExpenseCategoryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ExpenseCategoryController {
    private final IExpenseCategoryService expenseCategoryService;

    @Autowired
    public ExpenseCategoryController(@Qualifier("rmiExpenseCategoryService") IExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping(value = "/expenseCategories")
    public ResponseEntity<Object> index() {
        try {
            List<ExpenseCategory> expenseCategories = expenseCategoryService.getAllEntities();
            return new ResponseEntity<>(expenseCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/expenseCategories/{id}")
    public ResponseEntity<Object> single(@PathVariable String id) {
        try {
            ExpenseCategory expenseCategory = expenseCategoryService.findEntityByKey(Long.valueOf(id));
            if (expenseCategory == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(expenseCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/expenseCategories")
    public ResponseEntity<Object> create(@RequestBody ExpenseCategory expenseCategory) {
        try {
            expenseCategoryService.saveEntity(expenseCategory);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/expenseCategories")
    public ResponseEntity<Object> update(@RequestBody ExpenseCategory expenseCategory) {
        try {
            ExpenseCategory storedexpenseCategory = expenseCategoryService.findEntityByKey(expenseCategory.getId());
            if (storedexpenseCategory == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            System.out.println(expenseCategory);
            expenseCategoryService.saveEntity(expenseCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/expenseCategories/{id}")
    public ResponseEntity<Object> patche(@PathVariable Long id , @RequestBody Map<Object,Object> fields){

        try{
            ExpenseCategory storedExpenseCategory = expenseCategoryService.patch(id,fields,ExpenseCategory.class);
            if (storedExpenseCategory == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            expenseCategoryService.saveEntity(storedExpenseCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/expenseCategories/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try {
            expenseCategoryService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
