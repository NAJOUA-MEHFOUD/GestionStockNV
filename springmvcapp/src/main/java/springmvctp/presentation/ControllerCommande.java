package springmvctp.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import springmvctp.dao.entities.Commande;
import springmvctp.dao.entities.Client;
import springmvctp.dao.entities.Item;
import springmvctp.dao.entities.ProduitQuantite;
import springmvctp.service.Iservice.IServiceCommande;
import springmvctp.service.Iservice.IServiceItem;
import springmvctp.service.Iservice.clientntService;

@AllArgsConstructor
@Controller
public class ControllerCommande {

    private final IServiceCommande commandeService;
    private final clientntService clientService;
    private final IServiceItem itemService;

    @GetMapping("/listerCommandes")
    public String getListeCommandes(Model model) {
        List<Commande> listeCommandes = commandeService.listerCommandes();
        model.addAttribute("commandeList", listeCommandes);
        return "listeCommandes";
    }

    @GetMapping("/ajouterCommande")
    public String getAjouterCommandeForm(Model model) {
        model.addAttribute("commande", new Commande());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("produits", itemService.listerItems());
        return "ajouterCommande";
    }

    @PostMapping("/ajouterCommande")
    public String ajouterCommande(@RequestParam("date") String date,
                                  @RequestParam("client") Integer clientId,
                                  @RequestParam("produitsCount") int produitsCount,
                                  @RequestParam Map<String, String> allParams,
                                  Model model) {
        Commande commande = new Commande();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            commande.setDate(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "error";
        }

        Client client = clientService.getClientById(clientId);
        commande.setClient(client);

        for (int i = 0; i < produitsCount; i++) {
            String produitIdStr = allParams.get("produits[" + i + "].id");
            String quantiteStr = allParams.get("produits[" + i + "].quantity");

            if (produitIdStr != null && !produitIdStr.isEmpty() && quantiteStr != null && !quantiteStr.isEmpty()) {
                try {
                    Integer produitId = Integer.parseInt(produitIdStr);
                    int quantite = Integer.parseInt(quantiteStr);

                    Item produit = itemService.getItemById(produitId);

                    ProduitQuantite produitQuantite = new ProduitQuantite();
                    produitQuantite.setProduit(produit);
                    produitQuantite.setQuantite(quantite);
                    produitQuantite.setCommande(commande);

                    commande.addProduitQuantite(produitQuantite);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return "error";
                }
            }
        }

        commandeService.ajouterCommande(commande);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("produits", itemService.listerItems());

        return "redirect:/listerCommandes";
    }

    @GetMapping("/ModifierCommande/{id}")
    public String getModifierCommandeForm(@PathVariable("id") Integer id, Model model) {
        Commande commande = commandeService.rechercherCommande(id);
        model.addAttribute("commande", commande);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("produits", itemService.listerItems());

        return "ModifierCommande";
    }

    @PostMapping("/ModifierCommande/{id}")
    public String modifierCommande(@PathVariable("id") Integer id,
                                   @RequestParam("date") String date,
                                   @RequestParam("client.id") Integer clientId,
                                   @RequestParam("produitsCount") int produitsCount,
                                   @RequestParam Map<String, String> allParams) {
        Commande commande = commandeService.rechercherCommande(id);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            commande.setDate(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "error";
        }

        Client client = clientService.getClientById(clientId);
        commande.setClient(client);
        for (int i = 0; i < produitsCount; i++) {
            String produitIdStr = allParams.get("produits[" + i + "].id");
            String quantiteStr = allParams.get("produits[" + i + "].quantity");

            if (produitIdStr != null && !produitIdStr.isEmpty() && quantiteStr != null && !quantiteStr.isEmpty()) {
                try {
                    Integer produitId = Integer.parseInt(produitIdStr);
                    int nouvelleQuantite = Integer.parseInt(quantiteStr);

                    Item produit = itemService.getItemById(produitId);
                    ProduitQuantite produitQuantite = new ProduitQuantite();
                    produitQuantite.setProduit(produit);
                    produitQuantite.setQuantite(nouvelleQuantite);
                    produitQuantite.setCommande(commande);
                    commande.addProduitQuantite(produitQuantite);

                    int ancienneQuantite = commande.getQuantiteProduit(produitId); // Ancienne quantité dans la commande
                    int differenceQuantite = nouvelleQuantite - ancienneQuantite; // Calcul de la différence

                    // Mettre à jour le stock du produit en conséquence de la différence
                    itemService.updateProductStock(produitId, -differenceQuantite);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return "error";
                }
            }
        }

            
        

        commandeService.modifierCommande(commande);
        return "redirect:/listerCommandes";
    }




    @PostMapping("/confirmerCommande/{id}")
    public String confirmerCommande(@PathVariable("id") Integer id) {
        commandeService.confirmerCommande(id);
        return "redirect:/listerCommandes";
    }

    @PostMapping("/annulerCommande/{id}")
    public String annulerCommande(@PathVariable("id") Integer id) {
        commandeService.annulerCommande(id);
        return "redirect:/listerCommandes";
    }

    @PostMapping("/supprimerCommande/{id}")
    public String supprimerCommande(@PathVariable("id") Integer id) {
        commandeService.supprimerCommande(id);
        return "redirect:/listerCommandes";
    }
}
