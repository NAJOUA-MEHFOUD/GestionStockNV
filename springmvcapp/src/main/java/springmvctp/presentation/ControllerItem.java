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
import springmvctp.dao.entities.Item;
import springmvctp.service.Iservice.IServiceItem;

import jakarta.validation.Valid;

@AllArgsConstructor
@Controller
public class ControllerItem {
    
    private final IServiceItem itemService;

    @GetMapping("/listerItem")
    public String getListeItem(Model model) {
        List<Item> listeItems = itemService.listerItems();
        model.addAttribute("itemList", listeItems);
        return "index";
    }

    @GetMapping("/addform")
    public String getAddForm(Model model) {
        model.addAttribute("item", new Item());
        return "formajouter";
    }

    @PostMapping("/ajouterItem")
    public String insererItem(Model model, @Valid Item item, BindingResult br) {
        if (br.hasErrors()) {
            return "formajouter";
        } else {
            itemService.ajouterItem(item);
            return "redirect:/listerItem";
        }
    }

    @GetMapping("/modifierItem/{id}")
    public String getModifierForm(@PathVariable("id") Integer id, Model model) {
        Item item = itemService.rechercherItem(id);
        model.addAttribute("item", item);
        return "formmodifier";
    }

    @PostMapping("/modifierItem/{id}")
    public String modifierItem(@PathVariable("id") Integer id, @Valid Item item, BindingResult br) {
        if (br.hasErrors()) {
            return "formmodifier";
        } else {
            itemService.modifierItem(item);
            return "redirect:/listerItem";
        }
    }

    @PostMapping("/supprimerItem/{id}")
    public String supprimerItem(@PathVariable("id") Integer id) {
        itemService.supprimerItem(id);
        return "redirect:/listerItem";
    }


    @GetMapping("/rechercherItem")
    public String rechercherItem(@RequestParam("id") Integer id, Model model) {
        Item item = itemService.rechercherItem(id);
        model.addAttribute("item", item);
        return "detail";
    }
}
