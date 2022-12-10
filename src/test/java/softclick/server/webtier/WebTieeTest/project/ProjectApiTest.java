package softclick.server.webtier.WebTieeTest.project;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import softclick.server.data.entities.*;
import softclick.server.webtier.services.project.ProjectService;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProjectService projectService;


    ObjectMapper objectWraper = new ObjectMapper();
    ObjectWriter objectWriter = objectWraper.writer();


    Date debut = new Date();
    Date fin = new Date();
    Domain domain = new Domain("info");
    Employee chef = new Employee("0", "youssef", "zahi", "chefProjet", "youssef@gmail.com", "0637231628");
    Status status = new Status("testing");
    Priority priority = new Priority("medium", 5.0);
    Project p1 = new Project("new", "neeeew neeeeew", 5000d, domain, debut, fin, chef, status, priority);
    Project p2 = new Project("new", "neeeew neeeeew", 5000d, domain, debut, fin, chef, status, priority);
    Project p3 = new Project("new", "neeeew neeeeew", 5000d, domain, debut, fin, chef, status, priority);
    List<Project> projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));


    @SneakyThrows
    @Test
    public void shouldReturnAllProjects() {

        Mockito.when(projectService.getAllEntities()).thenReturn(projectList);

        this.mockMvc.perform(get("/api/v1/projects/")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectById() {
        p1.setIdProject(1L);
        Long idProject = 1L;

        Mockito.when(projectService.findEntityByKey(idProject)).thenReturn(p1);

        this.mockMvc.perform(get("/api/v1/projects/" + idProject)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectAdded() {
        projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Project neew = new Project("new", "neeeew neeeeew", 5000d, domain, debut, fin, chef, status, priority);
        Mockito.doCallRealMethod().when(projectService).saveEntity(neew);
        saveEntity(neew);
        String body_content = objectWriter.writeValueAsString(neew);
        this.mockMvc.perform(post("/api/v1/projects/", body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().is(201));
        assertThat(projectList.size()).isEqualTo(4);
        assertThat(projectList.get(3).getNameProject()).isEqualTo("new");
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectUpdated() {
        projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Project neew = new Project("updated project", "really updated", 5000d, domain, debut, fin, chef, status, priority);
        Long id_project = 1L;
        neew.setIdProject(id_project);

        Map<Object, Object> fields = new HashMap<>();
        fields.put("nameProject", "name updated");
        fields.put("descriptionProject", "description updated");

        Mockito.when(projectService.findEntityByKey(id_project)).thenReturn(p1);
        Mockito.doCallRealMethod().when(projectService).patch(id_project, fields);
        updateEntity(neew, 0);

        String body_content = objectWriter.writeValueAsString(fields);

        this.mockMvc.perform(patch("/api/v1/projects/" + id_project, body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().isOk());
        assertThat(projectList.get(2).getNameProject()).isEqualTo("updated project");
        assertThat(projectList.get(2).getDescriptionProject()).isEqualTo("really updated");
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectDeleted() {
        projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Long idProject = 1L;
        p1.setIdProject(idProject);

        deleteProject(p1);

        this.mockMvc.perform(delete("/api/v1/projects/" + idProject)).andDo(print()).andExpect(status().isOk());
        assertThat(projectList.size()).isEqualTo(2);
        //waiting for test database to regenrate the deleted projects for further delete tests
    }

    public void saveEntity(Project projectNew) {
        Long newId = projectList.get(projectList.size() - 1).getIdProject();
        projectNew.setIdProject(newId);
        projectList.add(projectNew);
    }

    public void updateEntity(Project newProject, int index) {
        projectList.remove(index);
        projectList.add(newProject);
    }

    public void deleteProject(Project project) {
        projectList.remove(project);
    }
}
