package softclick.server.webtier.services.team;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Team;
import softclick.server.data.repositories.TeamRepository;
import softclick.server.webtier.services.BaseService;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@Qualifier("rmiTeamService")
public class TeamService extends BaseService<Team, Long> implements ITeamService{
    protected TeamService(TeamRepository repository) {
        super(repository);
    }
}
