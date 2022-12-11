package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.User;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findBynom(String nom);

    @Query(""+
            "SELECT CASE WHEN COUNT(c) > 0 THEN "+
            "TRUE ELSE FALSE END "+
            "FROM Client c "+
            "WHERE c.email = ?1"
    )
    Boolean selectExistsEmail(String email);


}

