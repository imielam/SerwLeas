/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ponury
 */
public class Orders {
    public List<Order> orders = new ArrayList<Order>();
    
    public Orders(){
        List<OrderedItem> ordereditems = new ArrayList<OrderedItem>();
        ordereditems.add(new OrderedItem(1, 3));
        ordereditems.add(new OrderedItem(3,15));
        Order tmp = new Order(1,3,"2013-01-12","2013-06-12", ordereditems);
        orders.add(tmp);
        tmp = new Order(2,4,"2013-02-26","2014-02-26",ordereditems);
        orders.add(tmp);
        tmp = new Order(3,2,"2012-08-01","2014-08-01",ordereditems);
        orders.add(tmp);
    }
}
