package softclick.server.webtier.services.expense;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softclick.server.data.entities.*;
import softclick.server.data.repositories.ExpenseCategoryRepository;
import softclick.server.data.repositories.ExpenseRepository;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;
import java.util.Date;
import static org.apache.commons.lang3.builder.CompareToBuilder.reflectionCompare;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;
    private ExpenseCategoryRepository expenseCategoryRepository;
    private TaskRepository taskRepository;
    private IExpenseService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new ExpenseService(expenseRepository, expenseCategoryRepository,taskRepository);
    }

    @Test
    void canGetAllExpenses() {
        // When
        serviceUnderTest.getAllEntities();
        // Then
        verify(expenseRepository).findAll();
    }

    @Test
    void itShouldVerifyExpenseAdded() {
        // Given
        Expense expense = new Expense(10L , "amount", new Date(2020,12,01) , null);

        // When
        serviceUnderTest.saveEntity(expense);

        // Then
        ArgumentCaptor<Expense> expenseArgumentCaptor = ArgumentCaptor.forClass(Expense.class);
        verify(expenseRepository).save(expenseArgumentCaptor.capture());
        Expense capturedExpense = expenseArgumentCaptor.getValue();
        assertThat(capturedExpense).isEqualTo(expense);

    }

    @Test
    void itShouldVerifyExpenseAmountUpdated() {
        // Given
        Expense newExpense = new Expense(10L , "amount", new Date(2020,12,01) , null);
        Expense expense = new Expense(20L , "amount", new Date(2020,12,01) , null);

        expense.setId(1L);
        Expense oldExpenseCopy= new Expense(20L , "amount", new Date(2020,12,01) , null);

        oldExpenseCopy.setId(1L);
        given(expenseRepository.getReferenceById(1L)).willReturn(expense);

        // When
        serviceUnderTest.updateExpense(1L, newExpense);

        // Then
        verify(expenseRepository).save(any());
        assertThat(expense.getAmount()).isEqualTo(newExpense.getAmount());
        assertThat(reflectionCompare(expense, oldExpenseCopy, "amount")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyExpenseTypeExpenseUpdated() {
        // Given
        Expense newExpense = new Expense(10L , "amount", new Date(2020,12,01) , null);
        Expense expense = new Expense(10L , "expense", new Date(2020,12,01) , null);

        expense.setId(1L);
        Expense oldExpenseCopy= new Expense(10L , "expense", new Date(2020,12,01) , null);

        oldExpenseCopy.setId(1L);
        given(expenseRepository.getReferenceById(1L)).willReturn(expense);

        // When
        serviceUnderTest.updateExpense(1L, newExpense);

        // Then
        verify(expenseRepository).save(any());
        assertThat(expense.getTypeExpense()).isEqualTo(newExpense.getTypeExpense());
        assertThat(reflectionCompare(expense, oldExpenseCopy, "typeExpense")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyExpenseDateUpdated() {
        // Given
        Date date =new Date(2020,12,02);
        Expense newExpense = new Expense(20L , "amount", new Date(2020,12,01) , null);
        Expense expense = new Expense(20L , "amount", date , null);

        expense.setId(1L);
        Expense oldExpenseCopy= new Expense(20L , "amount", date, null);

        oldExpenseCopy.setId(1L);
        given(expenseRepository.getReferenceById(1L)).willReturn(expense);

        // When
        serviceUnderTest.updateExpense(1L, newExpense);

        // Then
        verify(expenseRepository).save(any());
        assertThat(expense.getDate()).isEqualTo(newExpense.getDate());
        assertThat(reflectionCompare(expense, oldExpenseCopy, "date")).isEqualTo(0);
    }




    @Test
    void itShouldVerifyExpenseCategoryUpdated() {
        // Given
        ExpenseCategory category=new ExpenseCategory("design");
        Expense newExpense = new Expense(10L , "amount", new Date(2020,12,01) , null);
        Expense expense = new Expense(10L , "amount", new Date(2020,12,01) ,category);

        expense.setId(1L);
        Expense oldExpenseCopy= new Expense(10L , "amount", new Date(2020,12,01) , category);

        oldExpenseCopy.setId(1L);
        given(expenseRepository.getReferenceById(1L)).willReturn(expense);

        // When
        serviceUnderTest.updateExpense(1L, newExpense);

        // Then
        verify(expenseRepository).save(any());
        assertThat(expense.getExpenseCategory()).isEqualTo(newExpense.getExpenseCategory());
        assertThat(reflectionCompare(expense, oldExpenseCopy, "expenseCategory")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyExpenseWasNotUpdated_expenseNotFoundException() {
        // given
        Expense newExpense = new Expense(20L , "amount", new Date(2020,12,02) , null);


        given(expenseRepository.getReferenceById(1L))
                .willReturn(null);

        // when and then
        AssertionsForClassTypes.assertThatThrownBy(() -> serviceUnderTest.updateExpense(1L, newExpense))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Expense not found");

        verify(expenseRepository, never()).save(any());
    }

    @Test
    void canDeleteExpense() {
        // given
        Expense expense = new Expense(20L , "amount", new Date(2020,12,02) , null);

        expense.setId(1L);

        // when
        serviceUnderTest.deleteEntity(1L);

        // then
        verify(expenseRepository).deleteById(1L);
    }


}