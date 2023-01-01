package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
