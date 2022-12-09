package softclick.server.webtier.WebTieeTest.domain;


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
import softclick.server.webtier.services.domain.DomainService;
import softclick.server.webtier.services.project.ProjectService;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DomainApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DomainService domainService;


    ObjectMapper objectWraper = new ObjectMapper();
    ObjectWriter objectWriter = objectWraper.writer();


Domain p1 = new Domain("info");
    Domain p2 = new Domain("indus");
    Domain p3 = new Domain("meca");
    List<Domain> domainList = new ArrayList<>(Arrays.asList(p1, p2, p3));


    @SneakyThrows
    @Test
    public void shouldReturnAllProjects() {

        Mockito.when(domainService.getAllEntities()).thenReturn(domainList);

        this.mockMvc.perform(get("/api/v1/domains/")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectById() {
        p1.setIdDomain(1L);
        Long idProject = 1L;

        Mockito.when(domainService.findEntityByKey(idProject)).thenReturn(p1);

        this.mockMvc.perform(get("/api/v1/domains/" + idProject)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectAdded() {
        domainList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Domain neew = new Domain("new domain");
        Mockito.doCallRealMethod().when(domainService).saveEntity(neew);
        saveEntity(neew);
        String body_content = objectWriter.writeValueAsString(neew);
        this.mockMvc.perform(post("/api/v1/domains/", body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().is(201));
        assertThat(domainList.size()).isEqualTo(4);
        assertThat(domainList.get(3).getNameDomain()).isEqualTo("new domain");
    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectUpdated() {
        domainList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Domain neew = new Domain("new domain");
        Long id_domain = 1L;
        neew.setIdDomain(id_domain);

        Map<Object, Object> fields = new HashMap<>();
        fields.put("nameDomain", "new domain");


        Mockito.when(domainService.findEntityByKey(id_domain)).thenReturn(p1);
        Mockito.doCallRealMethod().when(domainService).patch(id_domain, fields,Domain.class);
        updateEntity(neew, 0);

        String body_content = objectWriter.writeValueAsString(fields);

        this.mockMvc.perform(patch("/api/v1/domains/" + id_domain, body_content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body_content)).
                andDo(print()).andExpect(status().isOk());
        assertThat(domainList.get(2).getNameDomain()).isEqualTo("new domain");

    }

    @SneakyThrows
    @Test
    public void shouldReturnProjectDeleted() {
        domainList = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Long idDomain = 1L;
        p1.setIdDomain(idDomain);

        deleteDomain(p1);

        this.mockMvc.perform(delete("/api/v1/domains/" + idDomain)).andDo(print()).andExpect(status().isOk());
        assertThat(domainList.size()).isEqualTo(2);
        //waiting for test database to regenrate the deleted projects for further delete tests
    }

    public void saveEntity(Domain newDomain) {
        Long newId = domainList.get(domainList.size() - 1).getIdDomain();
        newDomain.setIdDomain(newId);
        domainList.add(newDomain);
    }

    public void updateEntity(Domain newDomain, int index) {
        domainList.remove(index);
        domainList.add(newDomain);
    }

    public void deleteDomain(Domain domain) {
        domainList.remove(domain);
    }
}
