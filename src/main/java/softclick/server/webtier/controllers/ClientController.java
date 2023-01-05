package softclick.server.webtier.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Project;
import softclick.server.webtier.dtos.Client.ClientCreateAndUpdateDto;
import softclick.server.webtier.dtos.Client.ClientListAndSingleDto;
import softclick.server.webtier.dtos.project.ProjectandSingleDto;
import softclick.server.webtier.services.client.IClientService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final IClientService clientService;

    private final ModelMapper modelMapper;

    @Autowired
    public ClientController(@Qualifier("rmiClientService") IClientService clientService,ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<Object> index(){
        try{
            List<ClientListAndSingleDto> clients = clientService.getAllEntities().stream().map(client -> modelMapper.map(client, ClientListAndSingleDto.class)).collect(Collectors.toList());
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Client client = clientService.findEntityByKey(Long.valueOf(id));
            ClientListAndSingleDto clientDto = modelMapper.map(client, ClientListAndSingleDto.class);
            if (client == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

                return new ResponseEntity<>(client, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<Object> create(@RequestBody ClientCreateAndUpdateDto clientDto){
        try{
            Client client = modelMapper.map(clientDto,Client.class);
            clientService.saveEntity(client);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody ClientCreateAndUpdateDto clientDto){
        try {
            Client client = modelMapper.map(clientDto, Client.class);
            clientService.updateClient(Long.valueOf(id),client);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(DataNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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

    @PostMapping(value = "/clients/search")
    public ResponseEntity<Object> search(@RequestBody Client client_searched){
        try{
            String nom,prenom,nomEntreprise,ville,pay;
            nom = (client_searched.getNom() == null) ? null: client_searched.getNom();
            prenom = (client_searched.getPrenom() == null) ? null:client_searched.getPrenom();
            nomEntreprise = (client_searched.getNomEntreprise() == null) ? null:client_searched.getNomEntreprise();
            ville = (client_searched.getVille() == null) ? null:client_searched.getVille() ;
            pay = (client_searched.getPays() == null) ? null:client_searched.getPays() ;
            List<Client> clients = clientService.serachClient(nom,prenom,nomEntreprise,ville,pay);
            List<ClientListAndSingleDto> clienttDtoList = clients.stream().map(t -> modelMapper.map(t, ClientListAndSingleDto.class)).collect(Collectors.toList());
            return new ResponseEntity<>(clienttDtoList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
