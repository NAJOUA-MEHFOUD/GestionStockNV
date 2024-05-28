package springmvctp.service.Iservice;

import springmvctp.dao.entities.Commande;

import java.util.List;

public interface IServiceCommande {
    List<Commande> listerCommandes();
    Commande rechercherCommande(Integer id);
    void ajouterCommande(Commande commande);
    void modifierCommande(Commande commande);
    void confirmerCommande(Integer id);
    void annulerCommande(Integer id);
	void supprimerCommande(Integer id);
}
