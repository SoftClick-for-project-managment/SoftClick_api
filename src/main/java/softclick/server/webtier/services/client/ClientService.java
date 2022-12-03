package softclick.server.webtier.services.client;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.ClientRepository;
import softclick.server.data.repositories.RoleRepository;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.services.client.IClientService;
import softclick.server.webtier.services.user.UserService;

import java.util.List;
@Service @Transactional @Slf4j @Qualifier("rmiClientService")
public class ClientService extends BaseService<Client, Long> implements IClientService {



    protected ClientService(ClientRepository repository) {
        super(repository);
    }


}
