package springmvctp.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvctp.dao.entities.Client;
import springmvctp.service.Iservice.clientntService;

import java.util.List;

@Controller
public class ControllerClient {

    @Autowired
    private clientntService clientService;

    @GetMapping("/listerClients")
    public String listerClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "listerClients"; // renvoie à la vue listerClients.html
    }

    @GetMapping("/ajouterClient")
    public String afficherFormulaireAjoutClient(Model model) {
        model.addAttribute("client", new Client());
        return "ajouterClient"; // renvoie à la vue ajouterClient.html
    }

    @PostMapping("/ajouterClient")
    public String ajouterClient(@ModelAttribute("client") Client client) {
        clientService.addClient(client);
        return "redirect:/listerClients"; // redirige vers la liste des clients après l'ajout
    }

    @GetMapping("/modifierClient/{id}")
    public String afficherFormulaireModifierClient(@PathVariable("id") Integer id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "modifierClient"; // renvoie à la vue modifierClient.html
    }

    @PostMapping("/modifierClient/{id}")
    public String modifierClient(@PathVariable("id") Integer id, @ModelAttribute("client") Client client) {
        client.setId(id); // assure que l'ID reste le même lors de la modification
        clientService.updateClient(client);
        return "redirect:/listerClients"; // redirige vers la liste des clients après la modification
    }

    @PostMapping("/supprimerClient/{id}")
    public String supprimerClient(@PathVariable("id") Integer id) {
        clientService.deleteClient(id);
        return "redirect:/listerClients"; // redirige vers la liste des clients après la suppression
    }
}
