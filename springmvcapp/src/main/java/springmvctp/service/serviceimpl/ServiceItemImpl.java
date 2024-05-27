package springmvctp.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
        if (item.isEmpty()) {
            throw new RuntimeException("Item not found");
        } else {
            itemRepos.deleteById(id);
        }
    }

    @Override
    public Item rechercherItem(Integer id) {
        Optional<Item> item = itemRepos.findById(id);
        if (item.isEmpty()) {
            throw new RuntimeException("Item not found");
        } else {
            return item.get();
        }
    }

    @Override
    public void modifierItem(Item item) {
        itemRepos.save(item);
    }

    
    @Override
    public List<Item> listerItems() {
        return itemRepos.findAll(); 
    }


   
}
