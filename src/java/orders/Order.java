/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import orders.OrderedItem;
import inventory.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ponury
 */
public class Order {
    private int orderid;
    private int userid;
    private String startdate;
    private String enddate;
    private List<OrderedItem> ordereditems = new ArrayList<OrderedItem>();
    
    public Order (int orderid, int userid, String startdate, String enddate, List<OrderedItem> ordereditems){
        this.orderid = orderid;
        this.userid = userid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.ordereditems = ordereditems;
    }

    public int getOrderid() {
        return orderid;
    }

    public int getUserid() {
        return userid;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public List<OrderedItem> getOrdereditems() {
        return ordereditems;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setOrdereditems(List<OrderedItem> ordereditems) {
        this.ordereditems = ordereditems;
    }
    
}
