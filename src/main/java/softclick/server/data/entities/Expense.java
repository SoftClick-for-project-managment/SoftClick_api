package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Proxy(lazy=false)
public class Expense implements Serializable, Comparable<Expense> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private String typeExpense;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "idCategory")
    private ExpenseCategory expenseCategory;
    @ManyToOne
    @JoinColumn(name = "idTask")
    private Task task;
    public Expense(Long amount , String typeExpense, Date date, ExpenseCategory expenseCategory,Task task){
        this.amount=amount;
        this.typeExpense=typeExpense;
        this.expenseCategory=expenseCategory;
        this.date=date;
        this.task=task;

    }


    @Override
    public int compareTo(Expense expense) {
        return Comparator.comparing(Expense::getAmount)
                .thenComparing(Expense::getDate)
                .thenComparing(Expense::getExpenseCategory)
                .thenComparing(Expense::getTypeExpense)
                .thenComparing(Expense::getTask)

                .compare(this, expense);
    }
}
