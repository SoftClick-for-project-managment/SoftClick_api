package softclick.server.webtier.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Team;
import softclick.server.webtier.dtos.Empoyee.EmployeeCreateAndUpdateDto;
import softclick.server.webtier.dtos.Empoyee.EmployeeListAndSingleDto;
import softclick.server.webtier.dtos.Team.TeamCreateAndUpdateDto;
import softclick.server.webtier.dtos.Team.TeamListAndSingleDto;
import softclick.server.webtier.services.employee.IEmployeeService;
import softclick.server.webtier.services.team.ITeamService;


import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TeamController {

    private final ITeamService teamService;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamController(@Qualifier("rmiTeamService") ITeamService teamService, ModelMapper modelMapper) {
        this.teamService = teamService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/teams")
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
            TeamListAndSingleDto teamDto = modelMapper.map(team, TeamListAndSingleDto.class);
            if (team == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(team, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/teams")
    public ResponseEntity<Object> create(@RequestBody TeamCreateAndUpdateDto teamDto){
        try{
            Team team = modelMapper.map(teamDto, Team.class);
            teamService.saveEntity(team);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/teams/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody TeamCreateAndUpdateDto teamDto){
        try{
            Team team = modelMapper.map(teamDto, Team.class);
            teamService.UpdateTeam(Long.valueOf(id), team);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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