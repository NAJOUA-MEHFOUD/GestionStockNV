/*package springmvctp.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password; // Champ pour stocker le mot de passe
    private String address; // Champ pour stocker l'adresse
    private String phoneNumber; // Champ pour stocker le numéro de téléphone

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;
}*/
