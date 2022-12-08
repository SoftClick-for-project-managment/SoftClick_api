package softclick.server.webtier.services.projects;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void shouldReturnAllProjects(){
        this.mockMvc.perform(get("/api/v1/projects/")).andDo(print()).andExpect(status().isOk());
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

        this.mockMvc.perform(get("/api/v1/projects/")).andDo(print()).andExpect(status().isOk());
    }
}
