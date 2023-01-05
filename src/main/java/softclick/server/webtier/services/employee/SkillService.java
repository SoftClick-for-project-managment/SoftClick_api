package softclick.server.webtier.services.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Skill;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.data.repositories.SkillRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Set;

@Service @Transactional @Slf4j @Qualifier("rmiSkillService")
public class SkillService extends BaseService<Skill, Long> implements ISkillService {

    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    @Autowired
    protected SkillService(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        super(skillRepository);
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

}
