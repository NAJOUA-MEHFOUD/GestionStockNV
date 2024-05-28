package springmvctp.service.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import springmvctp.dao.entities.Item;
import springmvctp.dao.repositories.IItemRepos;
import springmvctp.service.Iservice.IServiceItem;

@Service
@Transactional
@AllArgsConstructor
public class ServiceItemImpl implements IServiceItem {

    private final IItemRepos itemRepos;
    
    @Override
    public void ajouterItem(Item item) {
        itemRepos.save(item);
    }

    @Override
    public void supprimerItem(Integer id) {
        Optional<Item> item = itemRepos.findById(id);
        if (item.isPresent()) {
            itemRepos.deleteById(id);
        } else {
            throw new RuntimeException("Item not found");
        }
    }

    @Override
    public Item rechercherItem(Integer id) {
        return itemRepos.findById(id)
                        .orElseThrow(() -> new RuntimeException("Item not found"));
    }
    @Override
    public Item getItemById(Integer id) {
        Optional<Item> itemOptional = itemRepos.findById(id);
        return itemOptional.orElse(null);
    }
    @Override
    public void modifierItem(Item item) {
        itemRepos.save(item);
    }

    @Override
    public List<Item> listerItems() {
        return itemRepos.findAll(); 
    }
    public void updateProductStock(Integer productId, int quantity) {
        Item product = itemRepos.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        int updatedStock = product.getQuantity() + quantity; // Si la quantité est négative, cela décrémentera le stock
        product.setQuantity(updatedStock);
        itemRepos.save(product);
    }









    @Override
    public List<Item> getAllItems() {
        return itemRepos.findAll();
    }


    @Override
    public void augmenterQuantiteItem(Integer itemId, Integer quantiteAjoutee) {
        Item item = itemRepos.findById(itemId)
                             .orElseThrow(() -> new RuntimeException("Item not found"));
        int nouvelleQuantite = item.getQuantity() + quantiteAjoutee;
        item.setQuantity(nouvelleQuantite);
        itemRepos.save(item);
    }

    @Override
    public void diminuerQuantiteItem(Integer itemId, Integer quantiteDiminuee) {
        Item item = itemRepos.findById(itemId)
                             .orElseThrow(() -> new RuntimeException("Item not found"));
        int nouvelleQuantite = item.getQuantity() - quantiteDiminuee;
        item.setQuantity(nouvelleQuantite);
        itemRepos.save(item);
    }
}

