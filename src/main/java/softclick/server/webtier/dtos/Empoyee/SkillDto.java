package softclick.server.webtier.dtos.Empoyee;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class SkillDto implements Serializable {
    //private Long id;
    private String skillName;
}
