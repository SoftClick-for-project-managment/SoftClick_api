package softclick.server.data.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.User;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Client findByNom(String nom);

    @Query(""+
            "SELECT CASE WHEN COUNT(c) > 0 THEN "+
            "TRUE ELSE FALSE END "+
            "FROM Client c "+
            "WHERE c.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    private Specification<Client> nameClientLike(String name){
        if (name == null ||name.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("nom"), "%"+name+"%");
    }

    private Specification<Client> prenomClientLike(String prenom){
        if (prenom == null || prenom.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("prenom"), "%"+prenom+"%");
    }

    private Specification<Client> nomEntrepriseClientLike(String nomEntreprise){
        if (nomEntreprise == null || nomEntreprise.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("nomEntreprise"), "%"+nomEntreprise+"%");
    }
    private Specification<Client> nomVilleClientLike(String ville){
        if (ville == null || ville.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("ville"), "%"+ville+"%");
    }
    private Specification<Client> nomPayClientLike(String pays){
        if (pays == null || pays.equals("")) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("pays"), "%"+pays+"%");
    }

    public default List<Client> serachClient( String name,String prenom,String nomEntreprise,String ville,String pay){
        return findAll(nameClientLike(name).and(prenomClientLike(prenom).and(nomEntrepriseClientLike(nomEntreprise)
                .and(nomVilleClientLike(ville)
                        .and(nomPayClientLike(pay)))
        )));

    }

}
