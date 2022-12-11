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

    @Test
    void canGetAllClients() {
        // When
        serviceUnderTest.getAllEntities();
        // Then
        verify(clientRepository).findAll();
    }

    @Test
    void itShouldVerifyClientEmailUpdated() {
        // given
        Client newClient = new Client(null,null,"jayda.legros@rau.com",null, null,null,null);
        Client client = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        client.setId(1L);
        Client oldClientCopy = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        oldClientCopy.setId(1L);
        given(clientRepository.getReferenceById(1L))
                .willReturn(client);
        // when
        serviceUnderTest.updateClient(1L, newClient);
        // then
        verify(clientRepository).save(any());
        assertThat(client.getEmail()).isEqualTo(newClient.getEmail());
        System.out.println(client);
        assertThat(reflectionCompare(client,oldClientCopy, "email")).isEqualTo(0);
        System.out.println(oldClientCopy);
    }

    @Test
    void itShouldVerifyClientPhoneUpdated() {
        // given
        Client newClient = new Client(null,null,null,"0637943308", null,null,null);
        Client client = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        client.setId(1L);
        Client oldClientCopy = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        oldClientCopy.setId(1L);
        given(clientRepository.getReferenceById(1L))
                .willReturn(client);
        // when
        serviceUnderTest.updateClient(1L, newClient);
        // then
        verify(clientRepository).save(any());
        assertThat(client.getPhone()).isEqualTo(newClient.getPhone());
        System.out.println(client);
        assertThat(reflectionCompare(client,oldClientCopy, "phone")).isEqualTo(0);
        System.out.println(oldClientCopy);
    }


    @Test
    void itShouldVerifyClientNomUpdated() {
        // given
        Client newClient = new Client("wafae",null,null,null, null,null,null);
        Client client = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        client.setId(1L);
        Client oldClientCopy = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        oldClientCopy.setId(1L);
        given(clientRepository.getReferenceById(1L))
                .willReturn(client);
        // when
        serviceUnderTest.updateClient(1L, newClient);
        // then
        verify(clientRepository).save(any());
        assertThat(client.getNom()).isEqualTo(newClient.getNom());
        System.out.println(client);
        assertThat(reflectionCompare(client,oldClientCopy, "nom")).isEqualTo(0);
        System.out.println(oldClientCopy);
    }


    @Test
    void itShouldVerifyClientPrenomUpdated() {
        // given
        Client newClient = new Client(null,"abo",null,null, null,null,null);
        Client client = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        client.setId(1L);
        Client oldClientCopy = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        oldClientCopy.setId(1L);
        given(clientRepository.getReferenceById(1L))
                .willReturn(client);
        // when
        serviceUnderTest.updateClient(1L, newClient);
        // then
        verify(clientRepository).save(any());
        assertThat(client.getPrenom()).isEqualTo(newClient.getPrenom());
        System.out.println(client);
        assertThat(reflectionCompare(client,oldClientCopy, "prenom")).isEqualTo(0);
        System.out.println(oldClientCopy);
    }


    @Test
    void itShouldVerifyClientNomEntrepriseUpdated() {
        // given
        Client newClient = new Client(null,null,null,null, "sofclick",null,null);
        Client client = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        client.setId(1L);
        Client oldClientCopy = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        oldClientCopy.setId(1L);
        given(clientRepository.getReferenceById(1L))
                .willReturn(client);
        // when
        serviceUnderTest.updateClient(1L, newClient);
        // then
        verify(clientRepository).save(any());
        assertThat(client.getNomEntreprise()).isEqualTo(newClient.getNomEntreprise());
        System.out.println(client);
        assertThat(reflectionCompare(client,oldClientCopy, "nomEntreprise")).isEqualTo(0);
        System.out.println(oldClientCopy);
    }


    @Test
    void itShouldVerifyClientVilleUpdated() {
        // given
        Client newClient = new Client(null,null,null,null, null,"temara",null);
        Client client = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        client.setId(1L);
        Client oldClientCopy = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        oldClientCopy.setId(1L);
        given(clientRepository.getReferenceById(1L))
                .willReturn(client);
        // when
        serviceUnderTest.updateClient(1L, newClient);
        // then
        verify(clientRepository).save(any());
        assertThat(client.getVille()).isEqualTo(newClient.getVille());
        System.out.println(client);
        assertThat(reflectionCompare(client,oldClientCopy, "ville")).isEqualTo(0);
        System.out.println(oldClientCopy);
    }

    @Test
    void itShouldVerifyClientPaysUpdated() {
        // given
        Client newClient = new Client(null,null,null,null, null,null,"morocco");
        Client client = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        client.setId(1L);
        Client oldClientCopy = new Client("Michale","Bayer","sydnee.kutch@gutmann.com","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt","BY");
        oldClientCopy.setId(1L);
        given(clientRepository.getReferenceById(1L))
                .willReturn(client);
        // when
        serviceUnderTest.updateClient(1L, newClient);
        // then
        verify(clientRepository).save(any());
        assertThat(client.getPays()).isEqualTo(newClient.getPays());
        System.out.println(client);
        assertThat(reflectionCompare(client,oldClientCopy, "pays")).isEqualTo(0);
        System.out.println(oldClientCopy);
    }



    @Test
    void canDeleteClient() {
        // given
        Client client = new Client(
                "Michale",
                "Bayer",
                "sydnee.kutch@gutmann.com",
                "(843) 605-7918",
                "Keebler, Satterfield and Bernier",
                "Carterfurt",
                "BY");
        client.setId(1L);
       // given(clientRepository.getReferenceById(1L)).willReturn(client);
        // when
        serviceUnderTest.deleteEntity(1L);

        // then
        verify(clientRepository).deleteById(1L);

    }




}
