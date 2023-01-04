package softclick.server.data.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project , Long> , JpaSpecificationExecutor<Project> {
    private Specification<Project> nameProjectlike(String name){
        if (name.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("nameProject"), "%"+name+"%");
    }
    private Specification<Project> domainEqualTo(Long id_domain){
        if (id_domain == null) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("domainProjet"), id_domain);
    }
    private Specification<Project> chefEqualTo(Long id_chef){
        if (id_chef == null) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("chefProject"), id_chef);
    }
    private Specification<Project> statusEqualTo(Long id_status){
        if (id_status == null) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("projectStatus"), id_status);
    }
    private Specification<Project> priorityEqualTo(Long id_priority){
        if (id_priority == null) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("projectPriority"), id_priority);
    }
    public default List<Project> serachProject(Long id_domain,Long id_chef,Long id_status,Long id_priority, String name_project){
        return findAll(nameProjectlike(name_project).and(domainEqualTo(id_domain).and(chefEqualTo(id_chef).and(statusEqualTo(id_status).and(priorityEqualTo(id_priority))))));

    }
}
