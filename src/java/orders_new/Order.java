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
public class Order {
    private int orderid;
    private int userid;
    private Date startdate;
    private Date enddate;
    private List<OrderedItem> ordereditems = new ArrayList<OrderedItem>();
    
    public Order (int orderid, int userid, Date startdate, Date enddate, List<OrderedItem> ordereditems){
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

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
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

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setOrdereditems(List<OrderedItem> ordereditems) {
        this.ordereditems = ordereditems;
    }
    
}
