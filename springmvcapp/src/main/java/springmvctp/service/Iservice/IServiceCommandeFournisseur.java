package springmvctp.service.Iservice;

import java.util.List;
import springmvctp.dao.entities.CommandeFournisseur;

public interface IServiceCommandeFournisseur {
    void ajouterCommandeFournisseur(CommandeFournisseur commandeFournisseur);
    void supprimerCommandeFournisseur(Integer id);
    CommandeFournisseur rechercherCommandeFournisseur(Integer id);
    void modifierCommandeFournisseur(CommandeFournisseur commandeFournisseur);
    List<CommandeFournisseur> listerCommandesFournisseur();
    
}
