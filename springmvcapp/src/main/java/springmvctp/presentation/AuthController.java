package springmvctp.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springmvctp.service.Iservice.IAuthService;


@Controller
public class AuthController {

    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginForm"; // Page de connexion HTML
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        // Utilisez le service d'authentification pour vérifier les informations de connexion
        if (authService.isValidCredentials(username, password)) {
            // Si les informations sont correctes, rediriger vers la page principale
            return "redirect:/home";
        } else {
            // Si les informations sont incorrectes, ajouter un message d'erreur et rediriger vers la page de connexion
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }
}
