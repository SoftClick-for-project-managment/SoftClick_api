package softclick.server.webtier.services.projects;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softclick.server.data.entities.*;
import softclick.server.data.repositories.*;
import softclick.server.webtier.services.project.IProjectService;
import softclick.server.webtier.services.project.ProjectService;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private StatusRepository statusRepository;
    @Mock
    private PriorityRepository priorityRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    private IProjectService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new ProjectService(projectRepository);
    }

    @Test
    void itShouldVerifyProjectAdded() {
        // given
        Date debut = new Date();
        Date fin = new Date();
        Employee chef =new Employee();
        chef.setId(2L);
        Status status = new Status();
        status.setIdStatus(1L);
        Priority priority = new Priority();
        priority.setIdPriority(1L);
        Domain domain = new Domain();
        domain.setIdDomain(2L);

        Project project = new Project( "new", "neeeew neeeeew", 5000d,domain, debut, fin, chef, status, priority);

        // when
        serviceUnderTest.saveEntity(project);
        // then
        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository).save(projectArgumentCaptor.capture());
        Project capturedProject = projectArgumentCaptor.getValue();
        assertThat(capturedProject).isEqualTo(project);
    }

    @Test
    void itShouldVerifyTaskNotAdd_endDateLteStartDate() {
        // given
        Date debut = new Date();
        Date fin = new Date();
        debut.setTime(fin.getTime()+10000);

        Employee chef =new Employee();
        chef.setId(2L);
        Status status = new Status();
        status.setIdStatus(1L);
        Priority priority = new Priority();
        priority.setIdPriority(1L);
        Domain domain = new Domain();
        domain.setIdDomain(2L);

        Project project = new Project( "new", "neeeew neeeeew", 5000d,domain, debut, fin, chef, status, priority);

        // when
        // then
        assertThatThrownBy(() -> serviceUnderTest.saveEntity(project))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Start date can't be greater than end date");

        verify(projectRepository, never()).save(any());
    }

    @Test
    void itShouldVerifyTpatch() {
        // given
        Date debut = new Date();
        Date fin = new Date();
        fin.setTime(fin.getTime()+10000);

        Employee chef =new Employee();
        chef.setId(2L);
        Status status = new Status();
        status.setIdStatus(1L);
        Priority priority = new Priority();
        priority.setIdPriority(1L);
        Domain domain = new Domain();
        domain.setIdDomain(2L);

        Project project = new Project( "new", "neeeew neeeeew", 5000d,domain, debut, fin, chef, status, priority);
        Project newProject = new Project("name updated", "description updated", 5000d,domain, debut, fin, chef, status, priority);
        project.setIdProject(1L);
        newProject.setIdProject(1L);
        Map<Object,Object> fields = new HashMap<>();
        fields.put("nameProject","name updated");
        fields.put("descriptionProject","description updated");

        given(projectRepository.getReferenceById(1L))
                .willReturn(project);

        // when
        serviceUnderTest.patch(project.getIdProject(),fields);
        // then
        String[] excludeFields ={"nameProject","descriptionProject"};
        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository).save(projectArgumentCaptor.capture());
        Project capturedProject = projectArgumentCaptor.getValue();
        System.out.println(capturedProject.toString());
        assertThat(capturedProject.getNameProject()).isEqualTo(newProject.getNameProject());
        assertThat(capturedProject.getDescriptionProject()).isEqualTo(newProject.getDescriptionProject());
        assertThat(capturedProject.getIdProject()).isEqualTo(newProject.getIdProject());
    }
}