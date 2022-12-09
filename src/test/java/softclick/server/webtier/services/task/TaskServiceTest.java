package softclick.server.webtier.services.task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softclick.server.data.entities.*;
import softclick.server.data.repositories.*;
import softclick.server.webtier.utils.exceptions.BusinessException;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import static org.apache.commons.lang3.builder.CompareToBuilder.reflectionCompare;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock private TaskRepository taskRepository;
    @Mock private ProjectRepository projectRepository;
    @Mock private StatusRepository statusRepository;
    @Mock private PriorityRepository priorityRepository;
    @Mock private EmployeeRepository employeeRepository;
    private ITaskService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new TaskService(taskRepository, projectRepository, statusRepository, priorityRepository, employeeRepository);
    }

    @Test
    void itShouldVerifyTaskAdded() {
        // given
        Project project = new Project(); project.setIdProject(1L);
        Status status = new Status(); status.setIdStatus(1L);
        Priority priority = new Priority(); priority.setIdPriority(1L);
        Employee employee = new Employee(); employee.setId(1L);
        Task task = new Task("testTask", LocalDateTime.now(), LocalDateTime.now().plusHours(8),
                null, status, project, employee, priority, null);

        // when
        serviceUnderTest.saveEntity(task);
        // then
        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(taskArgumentCaptor.capture());
        Task capturedTask = taskArgumentCaptor.getValue();
        assertThat(capturedTask).isEqualTo(task);
    }

    @Test
    void itShouldVerifyTaskNotAdd_endDateLteStartDate() {
        // given
        Project project = new Project(); project.setIdProject(1L);
        Status status = new Status(); status.setIdStatus(1L);
        Priority priority = new Priority(); priority.setIdPriority(1L);
        Employee employee = new Employee(); employee.setId(1L);
        Task task = new Task("testTask",
                LocalDateTime.now(), LocalDateTime.now().minusDays(1), null, status, project, employee, priority, null);

        // when
        // then
        assertThatThrownBy(() -> serviceUnderTest.saveEntity(task))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Start date can't be greater than end date");

        verify(taskRepository, never()).save(any());
    }

    @Test
    void itShouldVerifyTaskStatusUpdated() {
        // given
        Status newStatus = new Status("OPEN"); newStatus.setIdStatus(2L);
        Task newTask = new Task(null, null, null, null, newStatus, null, null, null, null);

        Project project = new Project(); project.setIdProject(1L);
        Status status = new Status("IN PROGRESS"); status.setIdStatus(1L);
        Priority priority = new Priority(); priority.setIdPriority(1L);
        Employee employee = new Employee(); employee.setId(1L);
        LocalDateTime currentTime = LocalDateTime.now();
        Task task = new Task("testTask", currentTime, currentTime.plusHours(8), null, status, project, employee, priority, null);
        task.setId(1L);

        Task oldTaskCopy = new Task("testTask", currentTime, currentTime.plusHours(8), null, status, project, employee, priority, null);
        oldTaskCopy.setId(1L);

        given(taskRepository.getReferenceById(1L))
                .willReturn(task);
        given(statusRepository.getReferenceById(2L))
                .willReturn(newStatus);
        // when
        serviceUnderTest.updateTask(1L, newTask);
        // then
        verify(taskRepository).save(any());
        assertThat(task.getStatus()).isEqualTo(newTask.getStatus());
        assertThat(reflectionCompare(task, oldTaskCopy, "status")).isEqualTo(0);
    }

    @Test
    void itShouldVerifyTaskWasNotUpdated_taskNotFoundException() {
        // given
        Status newStatus = new Status("OPEN"); newStatus.setIdStatus(2L);
        Task newTask = new Task(null, null, null, null, newStatus, null, null, null, null);

        given(taskRepository.getReferenceById(1L))
                .willReturn(null);
        // when and then
        assertThatThrownBy(() -> serviceUnderTest.updateTask(1L, newTask))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("not found");

        verify(taskRepository, never()).save(any());
    }

    @Test
    void itShouldVerifyTaskWasNotUpdated_taskEndDateLteStartDate() {
        // given
        LocalDateTime currentTime = LocalDateTime.now();
        Task newTask = new Task(null, currentTime, currentTime.minusDays(1).plusHours(7).minusMinutes(30), null, null, null, null, null, null);

        Project project = new Project(); project.setIdProject(1L);
        Status status = new Status("IN PROGRESS"); status.setIdStatus(1L);
        Priority priority = new Priority(); priority.setIdPriority(1L);
        Employee employee = new Employee(); employee.setId(1L);
        LocalDateTime timeBeforeTwoDaysFromNow = LocalDateTime.now().minusDays(2);
        Task task = new Task("testTask", timeBeforeTwoDaysFromNow, timeBeforeTwoDaysFromNow.plusHours(8), null, status, project, employee, priority, null);
        task.setId(1L);

        Task oldTaskCopy = new Task("testTask", timeBeforeTwoDaysFromNow, timeBeforeTwoDaysFromNow.plusHours(8), null, status, project, employee, priority, null);
        oldTaskCopy.setId(1L);

        given(taskRepository.getReferenceById(1L))
                .willReturn(task);
        // when and then
        assertThatThrownBy(() -> serviceUnderTest.updateTask(1L, newTask))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Start date can't be greater than end date");

        verify(taskRepository, never()).save(any());
//        assertThat(reflectionCompare(task, oldTaskCopy)).isEqualTo(0);
        assertThat(task).isEqualTo(oldTaskCopy);
    }
}