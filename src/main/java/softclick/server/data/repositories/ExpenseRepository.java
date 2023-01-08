package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.*;

import java.util.List;

@Repository

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Override
    Expense getReferenceById(Long aLong);

    List<Expense> findByTypeExpense(String typeExpense);
    List<Expense> findByExpenseCategory(ExpenseCategory expenseCategory);


    Expense findByTask(Task task);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    boolean existsByTask(Task task);

    @Query("SELECT e FROM Expense as e WHERE ( :typeExpense  ='' or e.typeExpense = :typeExpense ) and ( :expenseCategory is null or e.expenseCategory = :expenseCategory  ) and " +
            " ( :task is null or e.task = :task  ) ")
    List<Expense> searchExpense(String typeExpense , ExpenseCategory expenseCategory , Task task);
}