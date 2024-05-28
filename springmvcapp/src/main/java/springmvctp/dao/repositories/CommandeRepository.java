package springmvctp.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springmvctp.dao.entities.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
