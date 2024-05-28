package springmvctp.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import springmvctp.dao.entities.CommandeFournisseur;
import springmvctp.dao.repositories.ICommandeFournisseurRepository;
import springmvctp.service.Iservice.IServiceCommandeFournisseur;
import springmvctp.service.Iservice.IServiceItem; // Importez votre service IServiceItem

@Service
@Transactional
@AllArgsConstructor
public class ServiceCommandeFournisseurImpl implements IServiceCommandeFournisseur {

    private final ICommandeFournisseurRepository commandeFournisseurRepos;
    private final IServiceItem itemService; // Ajoutez le service IServiceItem

    @Override
    public void ajouterCommandeFournisseur(CommandeFournisseur commandeFournisseur) {
        // Logique pour ajouter la commande fournisseur
        commandeFournisseurRepos.save(commandeFournisseur);
        
        // Mettre à jour la quantité de l'item correspondant
        itemService.augmenterQuantiteItem(commandeFournisseur.getItem().getId(), commandeFournisseur.getQuantity());
    }

   
    @Override
    public void supprimerCommandeFournisseur(Integer id) {
        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepos.findById(id);
        if (commandeFournisseur.isEmpty()) {
            throw new RuntimeException("Commande fournisseur not found");
        } else {
            commandeFournisseurRepos.deleteById(id);
        }
    }

  /*  @Override
    public CommandeFournisseur rechercherCommandeFournisseur(Integer id) {
        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepos.findById(id);
        if (commandeFournisseur.isEmpty()) {
            throw new RuntimeException("Commande fournisseur not found");
        } else {
            return commandeFournisseur.get();
        }
    }*/

    @Override
    public CommandeFournisseur rechercherCommandeFournisseur(Integer id) {
        return commandeFournisseurRepos.findById(id).orElse(null);
    }

    @Override
    public List<CommandeFournisseur> listerCommandesFournisseur() {
        return commandeFournisseurRepos.findAll();
    }

    @Override
    public void modifierCommandeFournisseur(CommandeFournisseur commandeFournisseur) {
        // Assurez-vous de vérifier si l'entité existe avant de la sauvegarder
        Optional<CommandeFournisseur> existingCommande = commandeFournisseurRepos.findById(commandeFournisseur.getId());
        if (existingCommande.isPresent()) {
            commandeFournisseurRepos.save(commandeFournisseur);
        } else {
            throw new RuntimeException("Commande fournisseur not found");
        }
    }

}
