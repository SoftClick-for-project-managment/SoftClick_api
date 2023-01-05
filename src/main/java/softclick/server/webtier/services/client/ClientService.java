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
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

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


    public void updateClient(Long id, Client newClient) {

        Client client = clientRepository.getReferenceById(id);
        if (client == null) {
            throw new DataNotFoundException("client not found");
        }
        if (newClient.getEmail() != null)
            client.setEmail(newClient.getEmail());

        if (newClient.getNom() != null)
            client.setNom(newClient.getNom());

        if (newClient.getPrenom() != null)
            client.setPrenom(newClient.getPrenom());

        if (newClient.getPays() != null)
            client.setPays(newClient.getPays());

        if (newClient.getPhone() != null)
            client.setPhone(newClient.getPhone());

        if (newClient.getVille() != null)
            client.setVille(newClient.getVille());

        if (newClient.getNomEntreprise() != null)
            client.setNomEntreprise(newClient.getNomEntreprise());

        clientRepository.save(client);
    }

    @Override
    public List<Client> serachClient(String name, String prenom, String nomEntreprise,String ville,String pay) {
        List<Client> filteredClients = clientRepository.serachClient(name, prenom, nomEntreprise,ville,pay);
        return  filteredClients;
    }


}

