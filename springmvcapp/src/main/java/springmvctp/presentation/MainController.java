package springmvctp.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("home")
    public String homePage() {
        return "home"; // Nom du fichier HTML contenant le navbar rempli
    }
}
