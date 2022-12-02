package softclick.server.webtier.services.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.Role;
import softclick.server.data.entities.Task;
import softclick.server.data.entities.User;
import softclick.server.data.repositories.ProjectRepository;
import softclick.server.data.repositories.RoleRepository;
import softclick.server.data.repositories.TaskRepository;
import softclick.server.data.repositories.UserRepository;
import softclick.server.webtier.services.BaseService;

@Service @Slf4j @Qualifier("rmiProjectService")
public class ProjectService extends BaseService<Project, Long> implements IProjectService {

    private final ProjectRepository projectRepository;
    @Autowired
    protected ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;

    }






}
