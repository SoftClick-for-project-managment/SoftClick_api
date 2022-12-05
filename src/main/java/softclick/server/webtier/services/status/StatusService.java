package softclick.server.webtier.services.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Status;
import softclick.server.data.repositories.StatusRepository;
import softclick.server.webtier.services.BaseService;


@Service
@Slf4j
@Qualifier("rmiStatusService")
public class StatusService extends BaseService<Status, Long> implements IStatusService {
    private final StatusRepository statusRepository;
    @Autowired
    protected StatusService(StatusRepository statusRepository) {
        super(statusRepository);
        this.statusRepository = statusRepository;

    }
}
