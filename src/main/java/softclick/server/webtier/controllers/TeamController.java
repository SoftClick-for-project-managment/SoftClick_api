package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import softclick.server.data.entities.Team;
import softclick.server.webtier.services.team.ITeamService;


import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TeamController {

    private final ITeamService teamService;

    @Autowired
    public TeamController(@Qualifier("rmiTeamService") ITeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/team")
    public ResponseEntity<Object> index(){
        try{
            List<Team> teams = teamService.getAllEntities();
            return new ResponseEntity<>(teams, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Team team = teamService.findEntityByKey(Long.valueOf(id));
            if (team == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(team, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/teams")
    public ResponseEntity<Object> create(@RequestBody Team team){
        try{
            teamService.saveEntity(team);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/teams")
    public ResponseEntity<Object> update(@RequestBody Team team){
        try{
            Team storedTeam= teamService.findEntityByKey(team.getId());
            if (storedTeam == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            teamService.saveEntity(team);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/teams/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            teamService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
