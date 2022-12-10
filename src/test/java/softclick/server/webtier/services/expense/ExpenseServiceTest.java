package softclick.server.webtier.services.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softclick.server.data.entities.Expense;
import softclick.server.data.entities.ExpenseCategory;
import softclick.server.data.entities.Status;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.ExpenseCategoryRepository;
import softclick.server.data.repositories.ExpenseRepository;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {
    @Mock private TaskRepository taskRepository;

    @Mock private ExpenseRepository expenseRepository;
    @Mock private ExpenseCategoryRepository expenseCategoryRepository;
    private IExpenseService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new ExpenseService(expenseRepository,expenseCategoryRepository,taskRepository);
    }

    @Test
    void itShouldVerifyExpenseAdded() {
        // given
        Task task= new Task(); task.setId(1L);
        ExpenseCategory expenseCategory= new ExpenseCategory(); expenseCategory.setId(1L);

        Expense expense= new Expense(10L,"income", new Date(1000),expenseCategory,task);

        // when
        serviceUnderTest.saveEntity(expense);
        // then
        ArgumentCaptor<Expense> expenseArgumentCaptor = ArgumentCaptor.forClass(Expense.class);
        verify(expenseRepository).save(expenseArgumentCaptor.capture());
        Expense capturedExpense = expenseArgumentCaptor.getValue();
        assertThat(capturedExpense).isEqualTo(expense);
    }




    @Test
    void itShouldVerifyExpenseWasNotUpdated_ExpenseNotFoundException() {
        // given
        Status newStatus = new Status("OPEN"); newStatus.setIdStatus(2L);
        Task newTask = new Task(null, null, null, null, newStatus, null, null, null,null);
        ExpenseCategory newExpenseCategory=new ExpenseCategory("transport");
        Expense newExpense=new Expense(11L,"amount",null,newExpenseCategory,newTask);
        given(expenseRepository.getReferenceById(1L))
                .willReturn(null);
        // when and then
        assertThatThrownBy(() -> serviceUnderTest.updateExpense(1L, newExpense))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("not found");

        verify(expenseRepository, never()).save(any());
    }


}