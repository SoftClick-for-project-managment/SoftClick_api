package softclick.server.webtier.services.team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import softclick.server.data.entities.Client;
import softclick.server.data.entities.Team;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.data.repositories.TeamRepository;

import static org.apache.commons.lang3.builder.CompareToBuilder.reflectionCompare;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class TeamServiceTest {

    @Mock private TeamRepository teamRepository;
    @Mock private EmployeeRepository employeeRepository;
    @Mock private ITeamService serviceUnderTest;
    @BeforeEach
    void setUp() {
        serviceUnderTest=new TeamService(teamRepository,employeeRepository);
    }
    @Test
    void itShouldVerifyTeamAdded() {
        // given
        Team team=new Team("developpement team","hello! c'est une équipe de developpement");
        // when
        serviceUnderTest.saveEntity(team);
        // then
        ArgumentCaptor<Team> teamArgumentCaptor = ArgumentCaptor.forClass(Team.class);
        verify(teamRepository).save(teamArgumentCaptor.capture());
        Team capturedTeam = teamArgumentCaptor.getValue();
        assertThat(capturedTeam).isEqualTo(team);
    }
    @Test
    void canGetAllTeams() {
        // When
        serviceUnderTest.getAllEntities();
        // Then
        verify(teamRepository).findAll();
    }
    @Test
    void canDeleteTeam() {
        // given
        Team team = new Team(
               "team management","this is a team management");
        team.setIdTeam(1L);
        given(teamRepository.getReferenceById(1L)).willReturn(team);
        // when
        serviceUnderTest.deleteEntity(1L);

        // then
        verify(teamRepository).delete(teamRepository.getReferenceById(1L));
    }
    @Test
    void itShouldVerifyTeamUpdated() {
        // given
        Team newTeam = new Team("dev team","it is dev team");
        Team team = new Team("devops team","it's a devops team");
        team.setIdTeam(1L);
        Team OldTeamCopy = new Team("devops team","it's a devops team");
        OldTeamCopy.setIdTeam(1L);
        given(teamRepository.getReferenceById(1L))
                .willReturn(team);
        // when
        serviceUnderTest.UpdateTeam(1L, newTeam);
        // then
        verify(teamRepository).save(any());
        assertThat(team.getTeam_Name()).isEqualTo(newTeam.getTeam_Name());
        assertThat(team.getDescription()).isEqualTo(newTeam.getDescription());
        System.out.println(team);
        assertThat(reflectionCompare(team,OldTeamCopy)).isNotEqualTo(0);
        System.out.println(OldTeamCopy);
    }
}
