package springmvctp.presentation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import springmvctp.dao.entities.CommandeFournisseur;
import springmvctp.dao.entities.Fournisseur;
import springmvctp.dao.entities.Item;

import springmvctp.service.Iservice.IServiceCommandeFournisseur;
import springmvctp.service.Iservice.IServiceFournisseur;
import springmvctp.service.Iservice.IServiceItem;

@AllArgsConstructor
@Controller
public class CommandeFournisseurController {

    private final IServiceCommandeFournisseur commandeFournisseurService;
    private final IServiceItem itemService;
    private final IServiceFournisseur fournisseurService;

    @ModelAttribute("items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @ModelAttribute("fournisseurs")
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/listerCommandesFournisseur")
    public String listerCommandesFournisseur(Model model) {
        List<CommandeFournisseur> commandes = commandeFournisseurService.listerCommandesFournisseur();
        model.addAttribute("commandes", commandes);
        return "commandeFournisseur/index";
    }

    @GetMapping("/ajouterCommandeFournisseur")
    public String getAjouterCommandeFournisseurForm(Model model) {
        model.addAttribute("commandeFournisseur", new CommandeFournisseur());
        return "commandeFournisseur/formajouter";
    }

    @PostMapping("/ajouterCommandeFournisseur")
    public String ajouterCommandeFournisseur(@Valid CommandeFournisseur commandeFournisseur, BindingResult br) {
        if (br.hasErrors()) {
            return "commandeFournisseur/formajouter";
        } else {
            commandeFournisseurService.ajouterCommandeFournisseur(commandeFournisseur);
            return "redirect:/listerCommandesFournisseur";
        }
    }

    @GetMapping("/modifierCommandeFournisseur/{id}")
    public String getModifierCommandeFournisseurForm(@PathVariable("id") Integer id, Model model) {
        CommandeFournisseur commandeFournisseur = commandeFournisseurService.rechercherCommandeFournisseur(id);
        if (commandeFournisseur == null) {
            return "redirect:/listerCommandesFournisseur";
        }
        model.addAttribute("commandeFournisseur", commandeFournisseur);
        model.addAttribute("items", itemService.getAllItems());
        model.addAttribute("fournisseurs", fournisseurService.getAllFournisseurs());
        return "commandeFournisseur/formmodifier";
    }

    @PostMapping("/modifierCommandeFournisseur/{id}")
    public String modifierCommandeFournisseur(@PathVariable("id") Integer id, @Valid CommandeFournisseur commandeFournisseur, BindingResult br) {
        if (br.hasErrors()) {
            return "commandeFournisseur/formmodifier";
        } else {
            commandeFournisseurService.modifierCommandeFournisseur(commandeFournisseur);
            return "redirect:/listerCommandesFournisseur";
        }
    }

    @PostMapping("/supprimerCommandeFournisseur/{id}")
    public String supprimerCommandeFournisseurPost(@PathVariable("id") Integer id) {
        CommandeFournisseur commandeFournisseur = commandeFournisseurService.rechercherCommandeFournisseur(id);
        if (commandeFournisseur == null) {
            return "redirect:/listerCommandesFournisseur"; // Redirige si la commande n'existe pas
        }
        
        // Diminuer la quantité de produit associée à la commande fournisseur
        itemService.diminuerQuantiteItem(commandeFournisseur.getItem().getId(), commandeFournisseur.getQuantity());
        
        commandeFournisseurService.supprimerCommandeFournisseur(id);
        return "redirect:/listerCommandesFournisseur";
    }

    @GetMapping("/supprimerCommandeFournisseur/{id}")
    public String supprimerCommandeFournisseurGet(@PathVariable("id") Integer id) {
        return supprimerCommandeFournisseurPost(id);
    }

    @GetMapping("/rechercherCommandeFournisseur")
    public String rechercherCommandeFournisseur(@PathVariable("id") Integer id, Model model) {
        CommandeFournisseur commandeFournisseur = commandeFournisseurService.rechercherCommandeFournisseur(id);
        model.addAttribute("commandeFournisseur", commandeFournisseur);
        return "commandeFournisseur/detail";
    }
}
