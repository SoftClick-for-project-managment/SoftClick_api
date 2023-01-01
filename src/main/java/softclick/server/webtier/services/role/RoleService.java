package softclick.server.webtier.services.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Role;
import softclick.server.data.repositories.RoleRepository;
import softclick.server.webtier.services.BaseService;

import javax.transaction.Transactional;

@Service @Transactional @Slf4j
public class RoleService extends BaseService<Role, Long> implements IRoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }
}
