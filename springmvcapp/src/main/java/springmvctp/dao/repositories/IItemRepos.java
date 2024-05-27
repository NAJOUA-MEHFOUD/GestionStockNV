package springmvctp.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvctp.dao.entities.Item;

public interface IItemRepos extends JpaRepository<Item, Integer> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici si nécessaire
}
