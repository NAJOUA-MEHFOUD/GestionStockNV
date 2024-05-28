package springmvctp.dao.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import springmvctp.dao.entities.CommandeFournisseur;

public interface ICommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
