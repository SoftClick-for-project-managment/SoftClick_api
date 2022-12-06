package softclick.server.webtier.services.expense;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Expense;
import softclick.server.data.repositories.ExpenseRepository;
import softclick.server.webtier.services.BaseService;
@Service @Qualifier("rmiExpenseService")
public class ExpenseService extends BaseService<Expense, Long> implements IExpenseService{
    protected ExpenseService(ExpenseRepository repository) {
        super(repository);
    }
}
