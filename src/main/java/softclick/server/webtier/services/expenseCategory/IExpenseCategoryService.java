package softclick.server.webtier.services.expenseCategory;

import softclick.server.data.entities.ExpenseCategory;
import softclick.server.data.entities.Priority;
import softclick.server.webtier.services.IBaseService;

import java.util.Map;

public interface IExpenseCategoryService extends IBaseService<ExpenseCategory, Long> {
    ExpenseCategory patch(Long id_expenseCategory , Map<Object,Object> fields);
}

