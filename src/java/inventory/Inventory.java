/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ponury
 */
public class Inventory {
    public List<Item> inventory = new ArrayList<Item>();
    
    
    public Inventory(){
        Item tmp = new Item(1,"krzesło","Krzesło jest idealne do siadania, można nim także kogoś uderzyć",10000000,2,3.50f);
        inventory.add(tmp);
        tmp = new Item(2,"słoń","Słoń to nawet nie przedmiot ale wynajmujemy je bo są szare",15,11,3000.99f);
        inventory.add(tmp);
        tmp = new Item(3,"jogurt bakoma","Truskawkowe jogurty bakoma na promocji teraz już od 99.99zł",600,345,99.99f);
        inventory.add(tmp);
    }
}
