package softclick.server.webtier.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Skill;
import softclick.server.webtier.dtos.Empoyee.EmployeeListAndSingleDto;
import softclick.server.webtier.dtos.Empoyee.SkillDto;
import softclick.server.webtier.services.employee.ISkillService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SkillController {

    private final ISkillService skillService;
    private final ModelMapper modelMapper;

    @Autowired
    public SkillController(@Qualifier("rmiSkillService") ISkillService skillService, ModelMapper modelMapper) {
        this.skillService = skillService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/skills")
    public ResponseEntity<Object> index(){
        try{
            List<Skill> skills = skillService.getAllEntities();
            return new ResponseEntity<>(skills, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/skills/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Skill skill= skillService.findEntityByKey(Long.valueOf(id));
            if (skill == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(skill, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/skills")
    public ResponseEntity<Object> create(@RequestBody SkillDto skillDto){
        try{
            Skill skill = modelMapper.map(skillDto, Skill.class);
            skillService.saveEntity(skill);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/skills/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            skillService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
