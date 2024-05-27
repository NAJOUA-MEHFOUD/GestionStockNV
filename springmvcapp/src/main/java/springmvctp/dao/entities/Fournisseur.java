package springmvctp.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String contactName;

    @NotEmpty
    private String contactTitle;

    @NotEmpty
    private String address;

    @NotEmpty
    private String city;

    @NotEmpty
    private String region;

    @NotEmpty
    private String postalCode;

    @NotEmpty
    private String country;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String fax;

    // Vous pouvez ajouter d'autres champs selon vos besoins
}
