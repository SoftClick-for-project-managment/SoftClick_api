package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.Skill;

public interface SkillRepository  extends JpaRepository<Skill, Long> {
    //Skill findBySkillName(String skillName);
}
