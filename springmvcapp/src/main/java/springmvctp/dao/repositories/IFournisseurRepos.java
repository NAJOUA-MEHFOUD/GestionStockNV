package springmvctp.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import springmvctp.dao.entities.Fournisseur;

public interface IFournisseurRepos extends JpaRepository<Fournisseur, Integer> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici si nécessaire
}
