package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.*;

import java.util.List;

@Repository

public interface ExpenseRepository extends JpaRepository<Expense, Long> {



    @Query("SELECT e FROM Expense as e WHERE ( :typeExpense  ='' or e.typeExpense = :typeExpense ) and ( :expenseCategory is null or e.expenseCategory = :expenseCategory  ) and " +
            " ( :task is null or e.task = :task  ) ")
    List<Expense> searchExpense(String typeExpense , ExpenseCategory expenseCategory , Task task);
}