package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@NoArgsConstructor
@Data
@Proxy(lazy=false)
public class ExpenseCategory implements Serializable, Comparable<ExpenseCategory> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    public ExpenseCategory(String category){
        this.category=category;
    }

    @Override
    public int compareTo(ExpenseCategory expenseCategory) {
        return Comparator.comparing(ExpenseCategory::getCategory).compare(this, expenseCategory);
    }
}
