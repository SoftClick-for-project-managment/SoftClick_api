package softclick.server.webtier.services.expense;


import softclick.server.data.entities.Expense;
import softclick.server.data.repositories.ExpenseRepository;
import softclick.server.webtier.services.BaseService;

public class ExpenseService extends BaseService<Expense, Long> implements IExpenseService{
    protected ExpenseService(ExpenseRepository repository) {
        super(repository);
    }
}
