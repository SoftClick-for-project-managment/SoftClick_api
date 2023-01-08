package softclick.server.webtier.services.expense;

import softclick.server.data.entities.*;
import softclick.server.webtier.services.IBaseService;

import java.util.List;

public interface IExpenseService extends IBaseService<Expense, Long> {
    void updateExpense(Long id, Expense newExpense);
    void addTaskToExpense(Long expenseId, Long taskId);


    void addCategoryToExpense(Long expenseId, String expenseCategoryName);
    public List<Expense> serachExpense(String type , ExpenseCategory expenseCategory, Task task);
}
