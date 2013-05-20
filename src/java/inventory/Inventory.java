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
        
    }
    
    public void getItems() throws DbException{
//        inventory = mi.getAllItems(UserType.GUEST);
        inventory = mi.getAllItems(UserType.GUEST);
    }
    
}
