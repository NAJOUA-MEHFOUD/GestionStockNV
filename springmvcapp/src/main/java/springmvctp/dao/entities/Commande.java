package springmvctp.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Commande {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private Client client;

    // Liste des produits avec leur quantité
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitQuantite> produitsQuantite = new ArrayList<>();

    private boolean confirmee; // Indique si la commande est confirmée ou non

    private boolean annulee; // Indique si la commande est annulée ou non
    public void addProduitQuantite(ProduitQuantite produitQuantite) {
        this.produitsQuantite.add(produitQuantite);
        produitQuantite.setCommande(this);
    }
    public int getQuantiteProduit(int produitId) {
        for (ProduitQuantite pq : this.getProduitsQuantite()) {
            if (pq.getProduit().getId() == produitId) {
                return pq.getQuantite();
            }
        }
        return 0; // Si le produit n'est pas trouvé dans la commande
    }
    
}
