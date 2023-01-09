package softclick.server.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Proxy(lazy = false)
public class Team implements Serializable {
    private static final long serialVersionUID = -1837929862304475755L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeam;
    @Column(nullable = true )
    private String team_Name;
    @Column(nullable = true)
    private String description;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Team_Employee",
            joinColumns = @JoinColumn( name = "idTeam" ),
            inverseJoinColumns = @JoinColumn( name = "idEmployee" ))
    private Set<Employee> members;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "team_project",
            joinColumns = @JoinColumn( name = "idTeam" ),
            inverseJoinColumns = @JoinColumn( name = "idProject" ))
    private Set<Project> projects;
    public Team(String team_Name, String description, Set<Employee> members) {
        this.team_Name = team_Name;
        this.description = description;
        this.members = members;
    }

    public Team(String team_Name, String description) {
        this.team_Name = team_Name;
        this.description = description;
    }

    @JsonIgnore
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
