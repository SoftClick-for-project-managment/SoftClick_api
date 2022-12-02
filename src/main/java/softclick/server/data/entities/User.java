package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
@Proxy(lazy=false)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles")
    private Collection<Role> roles;
    @OneToOne(mappedBy = "user")
    private Employee employee;

    public User(String username, String password, boolean isActive) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }
}
