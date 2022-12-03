package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.User;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findBynom(String nom);

    @Query("SELECT c FROM Client c WHERE c.nom LIKE :kw")
    List<Client> findByKeyword(@Param("kw") String kw);


}

