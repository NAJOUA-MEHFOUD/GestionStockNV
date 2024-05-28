package springmvctp.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
public class ProduitQuantite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Commande commande;
    @ManyToOne
    private Item produit;

    private int quantite;

    // Autres propriétés et méthodes...

    public void setProduit(Item produit) {
        this.produit = produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Autres propriétés...
}


