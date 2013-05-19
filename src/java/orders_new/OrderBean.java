/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders_new;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ponury
 */
public class OrderBean {
    private Date startdate;
    private Date enddate;
    private List<OrderedItem> ordereditems = new ArrayList<OrderedItem>();

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public List<OrderedItem> getOrdereditems() {
        return ordereditems;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setOrdereditems(List<OrderedItem> ordereditems) {
        this.ordereditems = ordereditems;
    }
    
    public void addItem(OrderedItem o){
        this.ordereditems.add(o);
    }
    
}
