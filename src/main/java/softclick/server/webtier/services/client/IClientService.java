package softclick.server.webtier.services.client;

import softclick.server.data.entities.Client;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.Task;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.IBaseService;

import java.util.List;

public interface IClientService extends IBaseService<Client, Long> {

    void updateClient(Long id, Client client);

    public List<Client> serachClient(String name, String prenom, String nomEntreprise,String ville,String pay);

}