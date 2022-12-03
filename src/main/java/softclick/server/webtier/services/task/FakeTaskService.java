package softclick.server.webtier.services.task;

import org.springframework.beans.factory.annotation.Qualifier;
import softclick.server.data.entities.*;
import softclick.server.webtier.services.user.IUserService;

import java.util.*;

@Qualifier("rmiFakeTaskService")

public class FakeTaskService {

    private List<Task> tasks = new ArrayList<>();

    Project project = new Project();
    Employee employee = new Employee(123, "Tiger", "Nixon", "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");

    Status status = new Status(Long.valueOf(2),  "TO do");

    Priority priority = new Priority(Long.valueOf(2), "Urgent",(float) 12);
    Date aujourdhui = new Date();
    ExpenseCategory expenseCategory= new ExpenseCategory("categorie");

    Expense expense1 = new Expense(Long.valueOf(100) , "expenses", aujourdhui, expenseCategory);
    Expense expense2 = new Expense(Long.valueOf(200) , "expenses", aujourdhui, expenseCategory);
    Expense expense3 = new Expense(Long.valueOf(300) , "expenses", aujourdhui, expenseCategory);


    Collection<Expense> collection =
            new ArrayList<Expense>(Arrays.asList(new Expense[] { expense1, expense2,expense3 }));


    public FakeTaskService(){
        tasks.add(new Task("design","20/01/2023","23/05/2023","tache tres importante",status,project,employee,priority,collection));
    }

    public FakeTaskService(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void saveEntity(Task entity){

    }

    public Task findEntityByKey(Long aLong){
        return tasks.get(aLong.intValue());
    }


    public List<Task> getAllEntities() {
        return null;
    }


    public void deleteEntity(Long aLong) {

    }


    public void deleteAllEntities(List<Long> longs) {

    }
}
