package softclick.server.webtier.services.expense;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Expense;
import softclick.server.data.entities.ExpenseCategory;
import softclick.server.data.entities.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service @Qualifier("fakeExpenseService")
public class FakeExpenseService implements  IExpenseService{
    private List<Expense> expenses = new ArrayList<>();
    private Task task =new Task();
    ExpenseCategory category= new ExpenseCategory("Design");
    Date time= new Date();
    public FakeExpenseService(){
        expenses.add(new Expense(1230000L,"income",time,category));
    }
    @Override
    public void saveEntity(Expense entity) {

    }

    @Override
    public Expense findEntityByKey(Long aLong) {
        return null;
    }

    @Override
    public List<Expense> getAllEntities() {
        return null;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }
}
