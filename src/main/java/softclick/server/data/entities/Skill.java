package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
//@Data
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String skillName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "Employee_Skill",
            joinColumns = @JoinColumn( name = "idSkill" ),
            inverseJoinColumns = @JoinColumn( name = "idEmployee" ) )
    private Set<Employee> employees;

    public Long getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Skill(Long id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }
}
