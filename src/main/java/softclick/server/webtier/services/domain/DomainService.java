package softclick.server.webtier.services.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Domain;
import softclick.server.data.repositories.DomainRepository;
import softclick.server.webtier.services.BaseService;

@Service
@Slf4j
@Qualifier("rmiDomainService")
public class DomainService extends BaseService<Domain, Long> implements IDomainService {
    private final DomainRepository domainRepository;
    @Autowired
    protected DomainService(DomainRepository domainRepository) {
        super(domainRepository);
        this.domainRepository = domainRepository;

    }
}
