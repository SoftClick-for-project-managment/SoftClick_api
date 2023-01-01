package softclick.server.webtier.services.expense;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Expense;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.ExpenseRepository;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import javax.transaction.Transactional;

@Service @Transactional
@Slf4j
@Qualifier("rmiExpenseService")
public class ExpenseService extends BaseService<Expense, Long> implements IExpenseService{
    private final ExpenseRepository expenseRepository;
    private final TaskRepository taskRepository;
    @Autowired
    protected ExpenseService(ExpenseRepository expenseRepository, TaskRepository taskRepository) {
        super(expenseRepository);
        this.expenseRepository = expenseRepository;
        this.taskRepository = taskRepository;
    }

    public void updateExpense(Long id, Expense newExpense){
        log.info("Updating expense with id: {}", id.toString());
        Expense expense = expenseRepository.getReferenceById(id);
        if (expense== null)
            throw new DataNotFoundException("Expense not found");

        expense.setAmount(newExpense.getAmount());
        expense.setTypeExpense(newExpense.getTypeExpense());
        expense.setDate(newExpense.getDate());
        expense.setExpenseCategory(newExpense.getExpenseCategory());
        expense.setTask(newExpense.getTask());
        expenseRepository.save(expense);
    }

    @Override
    public void addTaskToExpense(Long expenseId, Long taskId) {
        log.info("Adding task with id {} to expense with id {}", expenseId, taskId);
        Task task= taskRepository.findById(taskId).get();
        Expense expense = expenseRepository.findById(expenseId).get();
        if(expense.getTask()!=null)
            throw new RuntimeException("The task is already selected");

        expense.setTask(task);
        expenseRepository.save(expense);
    }


}
