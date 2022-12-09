package softclick.server.webtier.services.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softclick.server.data.entities.*;
import softclick.server.data.repositories.ClientRepository;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.webtier.services.task.ITaskService;
import softclick.server.webtier.services.task.TaskService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.builder.CompareToBuilder.reflectionCompare;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private IClientService serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new ClientService(clientRepository);
    }

    @Test
    void itShouldVerifyClientAdded() {
        // given
        Client client = new Client("abouzbiba","wafae","wafae.abouzbiba@gmail.com","06 37 94","soft","temara","maroc");
        // when
        serviceUnderTest.saveEntity(client);
        // then
        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientArgumentCaptor.capture());
        Client capturedClient = clientArgumentCaptor.getValue();
        assertThat(capturedClient).isEqualTo(client);
    }



}
