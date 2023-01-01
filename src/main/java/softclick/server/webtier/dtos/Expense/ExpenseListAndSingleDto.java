package softclick.server.webtier.dtos.Expense;

import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Task;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
public class ExpenseListAndSingleDto implements Serializable {

    private Long id;
    private Long amount;
    private String typeExpense;
    private Date date;
    private Task task;
}
