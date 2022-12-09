package softclick.server.webtier.WebTieeTest.status;


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
import softclick.server.webtier.services.status.StatusService;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatusApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StatusService statusService;


    ObjectMapper objectWraper = new ObjectMapper();
    ObjectWriter objectWriter = objectWraper.writer();



    Status p1 = new Status("developing");
    Status p2 = new Status("testing");
    Status p3 = new Status("finalisation");
    List<Status> statusList = new ArrayList<>(Arrays.asList(p1, p2, p3));


    @SneakyThrows
    @Test
    public void shouldReturnAllProjects() {

        Mockito.when(statusService.getAllEntities()).thenReturn(statusList);

        this.mockMvc.perform(get("/api/v1/status/")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectById() {
        p1.setIdStatus(1L);
        Long idStatus = 1L;

        Mockito.when(statusService.findEntityByKey(idStatus)).thenReturn(p1);

        this.mockMvc.perform(get("/api/v1/status/" + idStatus)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void shouldReturnStatusAdded() {
        statusList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Status neew = new Status("new status");
        Mockito.doCallRealMethod().when(statusService).saveEntity(neew);
        saveEntity(neew);
        String body_content = objectWriter.writeValueAsString(neew);
        this.mockMvc.perform(post("/api/v1/status/", body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().is(201));
        assertThat(statusList.size()).isEqualTo(4);
        assertThat(statusList.get(3).getNameStatus()).isEqualTo("new status");
    }

    @SneakyThrows
    @Test
    public void shouldReturnStatusUpdated() {
        statusList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Status neew = new Status("new status");
        Long id_status = 1L;
        neew.setIdStatus(id_status);

        Map<Object, Object> fields = new HashMap<>();
        fields.put("nameStatus", "new status");


        Mockito.when(statusService.findEntityByKey(id_status)).thenReturn(p1);
        Mockito.doCallRealMethod().when(statusService).patch(id_status, fields,Status.class);
        updateEntity(neew, 0);

        String body_content = objectWriter.writeValueAsString(fields);

        this.mockMvc.perform(patch("/api/v1/status/" + id_status, body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().isOk());
        assertThat(statusList.get(2).getNameStatus()).isEqualTo("new status");

    }

    @SneakyThrows
    @Test
    public void shouldReturnStatusDeleted() {
        statusList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Long idStatus = 1L;
        p1.setIdStatus(idStatus);

        deleteStatus(p1);

        this.mockMvc.perform(delete("/api/v1/status/" + idStatus)).andDo(print()).andExpect(status().isOk());
        assertThat(statusList.size()).isEqualTo(2);

    }

    public void saveEntity(Status newPStatus) {
        Long newId = statusList.get(statusList.size() - 1).getIdStatus();
        newPStatus.setIdStatus(newId);
        statusList.add(newPStatus);
    }

    public void updateEntity(Status newPStatus, int index) {
        statusList.remove(index);
        statusList.add(newPStatus);
    }

    public void deleteStatus(Status status) {
        statusList.remove(status);
    }
}
