package springmvctp.service.Iservice;

import java.util.List;
import springmvctp.dao.entities.Item;

public interface IServiceItem {
    void ajouterItem(Item item);
    void supprimerItem(Integer id);
    Item rechercherItem(Integer id);
    void modifierItem(Item item);
    List<Item> listerItems();
   
}
