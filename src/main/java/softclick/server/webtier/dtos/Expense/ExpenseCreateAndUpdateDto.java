package softclick.server.webtier.dtos.Expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.ExpenseCategory;
import softclick.server.data.entities.Task;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseCreateAndUpdateDto implements Serializable {
    private Long amount;
    private String typeExpense;

    private Date date;
    private ExpenseCategory expenseCategory;
    private long idTask;
}
