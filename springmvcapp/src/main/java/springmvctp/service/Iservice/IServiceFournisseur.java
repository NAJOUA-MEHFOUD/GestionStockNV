package springmvctp.service.Iservice;

import java.util.List;
import springmvctp.dao.entities.Fournisseur;

public interface IServiceFournisseur {
    void ajouterFournisseur(Fournisseur fournisseur);
    void supprimerFournisseur(Integer id);
    Fournisseur rechercherFournisseur(Integer id);
    void modifierFournisseur(Fournisseur fournisseur);
    List<Fournisseur> listerFournisseurs();
}
