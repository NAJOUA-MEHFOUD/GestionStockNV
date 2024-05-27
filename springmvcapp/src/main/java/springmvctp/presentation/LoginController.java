/*package springmvctp.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import springmvctp.dao.entities.Client;
import springmvctp.service.Iservice.clientntService;

import java.util.List;

@AllArgsConstructor
@Controller
public class LoginController {

    private final clientntService clientService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("client", new Client());
        return "loginForm";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("client") Client client) {
        // Récupérer le client à partir de l'email
        Client existingClient = clientService.getClientByEmail(client.getEmail());
        if (existingClient != null && existingClient.getPassword().equals(client.getPassword())) {
            // Le client est authentifié avec succès, rediriger vers la page d'accueil
            return "redirect:/home";
        } else {
            // L'authentification a échoué, afficher un message d'erreur ou rediriger vers une page de connexion avec un message d'erreur
            return "redirect:/login?error";
        }
    }
}*/
