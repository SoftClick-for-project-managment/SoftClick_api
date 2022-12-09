package softclick.server.webtier.services.client;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softclick.server.data.entities.*;
import softclick.server.data.repositories.ClientRepository;
import softclick.server.data.repositories.RoleRepository;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.services.client.IClientService;
import softclick.server.webtier.services.user.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@Transactional
@Slf4j
@Qualifier("rmiClientService")
public class ClientService extends BaseService<Client, Long> implements IClientService {

    private final ClientRepository clientRepository;


    @Autowired
    protected ClientService(ClientRepository clientrepository) {
        super(clientrepository);
        this.clientRepository = clientrepository;

    }


    public void updateClient(Long id, Client newClient){
        log.info("Updating client with id: {}", id.toString());
        Client client = clientRepository.getReferenceById(id);
        if (client == null)
            throw new EntityNotFoundException();

        client.setNom(newClient.getNom());
        client.setPrenom(newClient.getPrenom());
        client.setPays(newClient.getPays());
        client.setEmail(newClient.getEmail());
        client.setVille(newClient.getVille());
        client.setNomEntreprise(newClient.getNomEntreprise());
        client.setPhone(newClient.getPhone());
        client.setPays(newClient.getPays());

       clientRepository.save(client);
    }

}
