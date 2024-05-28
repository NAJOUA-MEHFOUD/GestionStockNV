package springmvctp.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springmvctp.dao.entities.Commande;
import springmvctp.dao.entities.Item;
import springmvctp.dao.entities.ProduitQuantite;
import springmvctp.dao.repositories.CommandeRepository;
import springmvctp.service.Iservice.IServiceCommande;
import springmvctp.service.Iservice.IServiceItem;

import java.util.List;

@Service
@Transactional
public class ServiceCommande implements IServiceCommande {
	  private final IServiceItem serviceItem;
    private final CommandeRepository commandeRepository;

    @Autowired // Ajoutez cette annotation
    public ServiceCommande(CommandeRepository commandeRepository, IServiceItem serviceItem) {
        this.commandeRepository = commandeRepository;
        this.serviceItem = serviceItem;
    }

    @Override
    public List<Commande> listerCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande rechercherCommande(Integer id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public void ajouterCommande(Commande commande) {
        commandeRepository.save(commande);
        mettreAJourStockProduits(commande, true);
    }

    @Override
    public void modifierCommande(Commande commande) {
        commandeRepository.save(commande);
        mettreAJourStockProduits(commande, false);
    }


    @Override
    public void confirmerCommande(Integer id) {
        Commande commande = commandeRepository.findById(id).orElseThrow();
        commande.setConfirmee(true);
        commandeRepository.save(commande);
    }

    @Override
    public void annulerCommande(Integer id) {
        Commande commande = commandeRepository.findById(id).orElseThrow();
        commande.setAnnulee(true);
        commandeRepository.save(commande);
        mettreAJourStockProduits(commande, false);
    }
    @Override
    public void supprimerCommande(Integer id) {
        commandeRepository.deleteById(id);
    }
    private void mettreAJourStockProduits(Commande commande, boolean ajout) {
        for (ProduitQuantite produitQuantite : commande.getProduitsQuantite()) {
            Item produit = produitQuantite.getProduit();
            int quantite = produitQuantite.getQuantite();
            if (ajout) {
                serviceItem.updateProductStock(produit.getId(), -quantite); // Appel à la méthode updateProductStock
            } else {
                serviceItem.updateProductStock(produit.getId(), quantite); // Appel à la méthode updateProductStock
            }
        }
    }
}


