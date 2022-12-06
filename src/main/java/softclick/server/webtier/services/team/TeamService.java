package softclick.server.webtier.services.team;


import softclick.server.data.entities.Team;
import softclick.server.data.repositories.TeamRepository;
import softclick.server.webtier.services.BaseService;
@Service
@Transactional
@Slf4j
@Qualifier("rmiTeamService")
public class TeamService extends BaseService<Team, Long> implements ITeamService{
    protected TeamService(TeamRepository repository) {
        super(repository);
    }
}
