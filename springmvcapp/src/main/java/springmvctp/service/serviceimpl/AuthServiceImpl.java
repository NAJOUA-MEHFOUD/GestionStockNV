package springmvctp.service.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvctp.dao.repositories.UserRepository;
import springmvctp.service.Iservice.IAuthService;
import springmvctp.dao.entities.User;

@Service
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValidCredentials(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password); // Assurez-vous de stocker les mots de passe hachés
    }
}
