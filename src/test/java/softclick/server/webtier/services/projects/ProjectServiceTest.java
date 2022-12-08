package softclick.server.webtier.services.projects;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import softclick.server.data.entities.*;
import softclick.server.webtier.services.domain.DomainService;
import softclick.server.webtier.services.employee.EmployeeService;
import softclick.server.webtier.services.priority.PriorityService;
import softclick.server.webtier.services.status.StatusService;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private PriorityService priorityService;
    @Autowired
    private DomainService domainService;

    ObjectMapper objectWraper = new ObjectMapper();
    ObjectWriter objectWriter = objectWraper.writer();


    @SneakyThrows
    @Test
    public void shouldReturnAllProjects(){
        this.mockMvc.perform(get("/api/v1/projects/")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }
    @SneakyThrows
    @Test
    public void shouldReturnProjectById(){
        Long idProject=4L;
        this.mockMvc.perform(get("/api/v1/projects/"+idProject)).andDo(print()).andExpect(status().isOk());
    }
    @SneakyThrows
    @Test
    public void shouldReturnProjectAdded(){

        Date debut = new Date();
        Date fin = new Date();
        Employee chef =employeeService.getAllEntities().get(0);
        Status status = statusService.getAllEntities().get(0);
        Priority priority = priorityService.getAllEntities().get(0);
        Domain domain = domainService.getAllEntities().get(0);

        Project neew = new Project( "new", "neeeew neeeeew", 5000d,domain, debut, fin, chef, status, priority);

        String body_content = objectWriter.writeValueAsString(neew);

        this.mockMvc.perform(post("/api/v1/projects/",body_content).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body_content)).
                andDo(print()).andExpect(status().is(201));
    }
    @SneakyThrows
    @Test
    public void shouldReturnProjectUpdated(){
        Date debut = new Date();
        Date fin = new Date();
        Employee chef =employeeService.getAllEntities().get(0);
        Status status = statusService.getAllEntities().get(0);
        Priority priority = priorityService.getAllEntities().get(0);
        Domain domain = domainService.getAllEntities().get(0);

        Project neew = new Project( "updated project", "really updated", 5000d,domain, debut, fin, chef, status, priority);
        neew.setIdProject(7L);

        String body_content = objectWriter.writeValueAsString(neew);

        this.mockMvc.perform(put("/api/v1/projects/",body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectDeleted(){
        Long idProject=4L;
        this.mockMvc.perform(delete("/api/v1/projects/"+idProject)).andDo(print()).andExpect(status().isOk());
        //waiting for test database to regenrate the deleted projects for further delete tests
    }
}
