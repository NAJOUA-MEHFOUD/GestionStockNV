package springmvctp.presentation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import springmvctp.dao.entities.Fournisseur;
import springmvctp.service.Iservice.IServiceFournisseur;

import jakarta.validation.Valid;

@AllArgsConstructor
@Controller
public class ControllerFournisseur {

    private final IServiceFournisseur fournisseurService;

    @GetMapping("/listerFournisseurs")
    public String getListeFournisseurs(Model model) {
        List<Fournisseur> listeFournisseurs = fournisseurService.listerFournisseurs();
        model.addAttribute("fournisseurList", listeFournisseurs);
        return "fournisseur/index";
    }

    @GetMapping("/ajouterFournisseur")
    public String getAjouterFournisseurForm(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "fournisseur/formajouter";
    }

    @PostMapping("/ajouterFournisseur")
    public String ajouterFournisseur(@Valid Fournisseur fournisseur, BindingResult br) {
        if (br.hasErrors()) {
            return "fournisseur/formajouter";
        } else {
            fournisseurService.ajouterFournisseur(fournisseur);
            return "redirect:/listerFournisseurs";
        }
    }
    @GetMapping("/modifierFournisseur/{id}")
    public String getModifierFournisseurForm(@PathVariable("id") Integer id, Model model) {
        Fournisseur fournisseur = fournisseurService.rechercherFournisseur(id);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/formmodifier";
    }

    @PostMapping("/modifierFournisseur/{id}")
    public String modifierFournisseur(@PathVariable("id") Integer id, @Valid Fournisseur fournisseur, BindingResult br) {
        if (br.hasErrors()) {
            return "fournisseur/formmodifier";
        } else {
            fournisseurService.modifierFournisseur(fournisseur);
            return "redirect:/listerFournisseurs";
        }
    }


    @PostMapping("/supprimerFournisseur/{id}")
    public String supprimerFournisseur(@PathVariable("id") Integer id) {
        fournisseurService.supprimerFournisseur(id);
        return "redirect:/listerFournisseurs";
    }

    @GetMapping("/rechercherFournisseur")
    public String rechercherFournisseur(@RequestParam("id") Integer id, Model model) {
        Fournisseur fournisseur = fournisseurService.rechercherFournisseur(id);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/detail";
    }
}
