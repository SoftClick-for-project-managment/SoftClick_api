package softclick.server.webtier.services.expense;

import softclick.server.data.entities.Expense;
import softclick.server.webtier.services.IBaseService;

public interface IExpenseService extends IBaseService<Expense, Long> {
    void updateExpense(Long id, Expense newExpense);
    void addTaskToExpense(Long expenseId, Long taskId);


    void addCategoryToExpense(Long expenseId, String expenseCategoryName);
}
