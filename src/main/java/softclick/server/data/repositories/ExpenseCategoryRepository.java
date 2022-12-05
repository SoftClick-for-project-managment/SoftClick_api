package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.ExpenseCategory;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}