package softclick.server.webtier.services.expenseCategory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.ExpenseCategory;
import softclick.server.data.repositories.ExpenseCategoryRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.services.expenseCategory.IExpenseCategoryService;

import java.util.Map;

@Service
@Slf4j
@Qualifier("rmiExpenseCategoryService")
public class ExpenseCategoryService extends BaseService<ExpenseCategory, Long> implements IExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;
    @Autowired
    protected ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository) {
        super(expenseCategoryRepository);
        this.expenseCategoryRepository = expenseCategoryRepository;

    }




    @Override
    public ExpenseCategory patch(Long id_expenseCategory , Map<Object,Object> fields){
        ExpenseCategory expenseCategory = super.patch(id_expenseCategory, fields,ExpenseCategory.class);
        if(expenseCategory != null){
            saveEntity( expenseCategory);
            return expenseCategory;
        }
        return  null;
    }
}
