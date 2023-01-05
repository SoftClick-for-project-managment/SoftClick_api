package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Override
    Optional<Expense> findById(Long aLong);

    List<Expense> findByTypeExpense(String typeExpense);

    List<Expense> findByExpenseCategory_Category(String category);
}