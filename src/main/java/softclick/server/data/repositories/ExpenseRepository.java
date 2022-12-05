package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import softclick.server.data.entities.Expense;
import softclick.server.data.entities.ExpenseCategory;
import softclick.server.data.entities.Task;

import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("select e from Expense e where e.typeExpense = ?1")
    List<Expense> findByTypeExpense(String typeExpense);

    @Query("select e from Expense e where e.date = ?1")
    List<Expense> findByDate(Date date);

    @Query("select e from Expense e where e.expenseCategory.category = ?1")
    List<Expense> findByExpenseCategory_Category(String category);

    @Query("select e from Expense e where e.task.id = ?1")
    List<Expense> findByTask_Id(Long id);

    @Query("select count(e) from Expense e where e.typeExpense = ?1")
    long countByTypeExpense(String typeExpense);

    @Query("select count(e) from Expense e where e.expenseCategory.category = ?1")
    long countByExpenseCategory_Category(String category);

    @Query("select count(e) from Expense e where e.task.id = ?1")
    long countByTask_Id(Long id);


    @Override
    void deleteById(Long aLong);

}