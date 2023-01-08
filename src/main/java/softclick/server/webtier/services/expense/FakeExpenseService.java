package softclick.server.webtier.services.expense;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Expense;
import softclick.server.data.entities.ExpenseCategory;
import softclick.server.data.entities.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service @Qualifier("fakeExpenseService")
public class FakeExpenseService implements  IExpenseService{
    private final List<Expense> expenses = new ArrayList<>();
    private final Task task =new Task();
    ExpenseCategory category= new ExpenseCategory("Design");
    Date time= new Date();
    public FakeExpenseService(){
        expenses.add(new Expense(1230000L,"income",time,category,null));
    }
    @Override
    public void saveEntity(Expense entity) {

    }

    @Override
    public Expense findEntityByKey(Long aLong) {
        return expenses.get(aLong.intValue());
    }

    @Override
    public List<Expense> getAllEntities() {
        return expenses;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }

    @Override
    public void updateExpense(Long id, Expense newExpense) {

    }

    @Override
    public void addTaskToExpense(Long expenseId, Long taskId) {

    }

    @Override
    public void addCategoryToExpense(Long expenseId, String expenseCategoryName) {

    }

    @Override
    public List<Expense> serachExpense(String type, ExpenseCategory expenseCategory, Task task) {
        return null;
    }

    @Override
    public Expense patch(Long aLong, Map<Object, Object> fields, Class<Expense> expenseClass) {
        return null;
    }
}
