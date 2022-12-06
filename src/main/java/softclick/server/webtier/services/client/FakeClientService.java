package softclick.server.webtier.services.client;

import org.springframework.beans.factory.annotation.Qualifier;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.user.IUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Qualifier("rmiFakeClientService")
public class FakeClientService implements IClientService {

    private List<Client> clients = new ArrayList<>();


    public FakeClientService(){
        clients.add(new Client("abouzbiba","wafae","wafae.abouzbiba@gmail.com","06 37 94","soft","temara","maroc"));
        clients.add(new Client("cccc","ccc","ccc","06 37 94","soft","temara","maroc"));
        clients.add(new Client("Mable","Murphy","jayda.legros@rau.com      ","+1 (754) 958-2911", "Zulauf, Tillman and Beer","Spinkamouth","KY"));
        clients.add(new Client("Millie","Stracke","sbuckridge@runte.com      ","+1-530-492-6944", "Morissette Ltd","Lubowitzmouth","LU"));
        clients.add(new Client("Michale","Bayer","sydnee.kutch@gutmann.com  ","(843) 605-7918", "Keebler, Satterfield and Bernier","Carterfurt     ","BY"));
        clients.add(new Client("Griffin","Spencer  ","feeney.wendell@reichel.com","+1-940-539-7397", "Wisozk-Bayer","Boyerberg","CM"));
        clients.add(new Client("Odessa","Langworth","vkunze@mccullough.com     ","214-455-0227     ", "Brakus-Ruecker","East Elissafort","BI"));
        clients.add(new Client("Dariana","Hahn     ","uvandervort@keebler.info  ","1-915-624-0388   ", "Donnelly Group","Deshaunside    ","IL"));
        clients.add(new Client("Lilliana","Treutel  ","baby13@boehm.net          ","+1.754.208.4853  ", "Moen, Powlowski and Orn","Lake Herman    ","GW"));
        clients.add(new Client("Waylon","Abshire  ","savanna06@reilly.net      ","+1-828-433-3907  ", "Kutch, Torphy and Cremin","Casimirmouth   ","BQ"));
        clients.add(new Client("Joany","Gerhold  ","pamela.boehm@ward.com     ","240.523.7261     ", "Quitzon PLC","Shanahanview   ","CA"));
    }

    @Override
    public void saveEntity(Client entity) {

    }


    @Override
    public Client findEntityByKey(Long aLong) {
        return clients.get(aLong.intValue());
    }

    @Override
    public List<Client> getAllEntities() {
        return null;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }

    @Override
    public void updateClient(Long id, Client client) {

    }
}
