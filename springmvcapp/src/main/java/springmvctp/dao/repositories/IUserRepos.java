package springmvctp.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvctp.dao.entities.User;

public interface IUserRepos extends JpaRepository<User, Integer> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici si nécessaire
}
