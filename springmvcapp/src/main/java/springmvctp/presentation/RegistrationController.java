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
public class RegistrationController {

    private final clientntService clientService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "registrationForm";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("client") Client client) {
        clientService.addClient(client);
        return "redirect:/login"; // Rediriger vers la page de connexion après l'inscription
    }
}*/
