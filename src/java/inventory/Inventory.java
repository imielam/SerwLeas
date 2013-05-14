/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import extras.DbException;
import extras.UserType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ponury
 */
public class Inventory {
    private String userType = null;
    public List<Item> inventory = new ArrayList<Item>();
    private ModelInventory mi = new ModelInventory();
    
    
    public Inventory(){
//        Item tmp = new Item(1,"krzesło","Krzesło jest idealne do siadania, można nim także kogoś uderzyć",10000000,2,3.50);
//        inventory.add(tmp);
//        tmp = new Item(2,"słoń","Słoń to nawet nie przedmiot ale wynajmujemy je bo są szare",15,11,3000.99);
//        inventory.add(tmp);
//        tmp = new Item(2,"jogurt bakoma","Truskawkowe jogurty bakoma na promocji teraz już od 99.99zł",600,345,99.99);
//        inventory.add(tmp);
    }
    
    public void getItems() throws DbException{
//        inventory = mi.getAllItems(UserType.GUEST);
        inventory = mi.getAllAbailable(UserType.GUEST);
    }
}
