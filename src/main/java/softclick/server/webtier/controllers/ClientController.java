package softclick.server.webtier.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.User;
import softclick.server.webtier.services.client.ClientService;
import softclick.server.webtier.services.client.IClientService;
import softclick.server.webtier.services.user.IUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(@Qualifier("rmiClientService") IClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<Object> index(){
        try{
            List<Client> clients = clientService.getAllEntities();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Client client = clientService.findEntityByKey(Long.valueOf(id));
            if (client == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(client, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Client client){
        try{
            clientService.saveEntity(client);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Client client){
        try{
            Client storedClient = clientService.findEntityByKey(client.getId());
//            Optional<Client> storedClient = clientService.findEntityByKey(Long.valueOf(id));

            if (storedClient == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            clientService.saveEntity(client);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            clientService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
