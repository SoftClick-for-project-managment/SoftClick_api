package softclick.server.webtier.WebTieeTest.priority;


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
import softclick.server.webtier.services.priority.PriorityService;
import softclick.server.webtier.services.project.ProjectService;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriorityApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PriorityService priorityService;


    ObjectMapper objectWraper = new ObjectMapper();
    ObjectWriter objectWriter = objectWraper.writer();



    Priority p1 = new Priority("medium", 5.0);
    Priority p2 = new Priority("important", 8.0);
    Priority p3 = new Priority("can wait", 1.0);
    List<Priority> projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));


    @SneakyThrows
    @Test
    public void shouldReturnAllProjects() {

        Mockito.when(priorityService.getAllEntities()).thenReturn(projectList);

        this.mockMvc.perform(get("/api/v1/priorities/")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectById() {
        p1.setIdPriority(1L);
        Long idPriority = 1L;

        Mockito.when(priorityService.findEntityByKey(idPriority)).thenReturn(p1);

        this.mockMvc.perform(get("/api/v1/priorities/" + idPriority)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectAdded() {
        projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Priority neew = new Priority("medium", 5.0);
        Mockito.doCallRealMethod().when(priorityService).saveEntity(neew);
        saveEntity(neew);
        String body_content = objectWriter.writeValueAsString(neew);
        this.mockMvc.perform(post("/api/v1/priorities/", body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().is(201));
        assertThat(projectList.size()).isEqualTo(4);
        assertThat(projectList.get(3).getNamePriority()).isEqualTo("medium");
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectUpdated() {
        projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Priority neew = new Priority("waaaaw", 10.0);
        Long id_priority = 1L;
        neew.setIdPriority(id_priority);

        Map<Object, Object> fields = new HashMap<>();
        fields.put("namePriority", "name updated");


        Mockito.when(priorityService.findEntityByKey(id_priority)).thenReturn(p1);
        Mockito.doCallRealMethod().when(priorityService).patch(id_priority,fields);
        updateEntity(neew, 0);

        String body_content = objectWriter.writeValueAsString(fields);

        this.mockMvc.perform(patch("/api/v1/priorities/" + id_priority, body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().isOk());
        assertThat(projectList.get(3).getNamePriority()).isEqualTo("namePriority");

    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectDeleted() {
        projectList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Long idPriority = 1L;
        p1.setIdPriority(idPriority);

        deleteProject(p1);

        this.mockMvc.perform(delete("/api/v1/priorities/" + idPriority)).andDo(print()).andExpect(status().isOk());
        assertThat(projectList.size()).isEqualTo(2);
        //waiting for test database to regenrate the deleted projects for further delete tests
    }

    public void saveEntity(Priority prioritynew) {
        Long newId = projectList.get(projectList.size() - 1).getIdPriority();
        prioritynew.setIdPriority(newId);
        projectList.add(prioritynew);
    }

    public void updateEntity(Priority newPriority, int index) {
        projectList.remove(index);
        projectList.add(newPriority);
    }

    public void deleteProject(Priority priority) {
        projectList.remove(priority);
    }
}