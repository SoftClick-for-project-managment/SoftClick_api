package softclick.server.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Proxy(lazy=false)
public class Expense implements Serializable {
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

    public Expense( Long amount, String typeExpense, Date date, ExpenseCategory expenseCategory, Task task) {

        this.amount = amount;
        this.typeExpense = typeExpense;
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTypeExpense() {
        return typeExpense;
    }

    public void setTypeExpense(String typeExpense) {
        this.typeExpense = typeExpense;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    @JsonIgnoreProperties("expenses")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
