/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders_new;

import java.sql.Date;

/**
 *
 * @author Macimi
 */
public class TOrderForUser {
    private String login;
    private int userid;
    private Date startDate;
    private Date endDate;
    private int quality; //quantity, quality, co za różnica
    private String productName;
    private int productid;
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public TOrderForUser(String login, int id, Date startDate, Date endDate, int quality, int productId, String productName, int orderId) {
        this.login = login;
        this.userid = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quality = quality;
        this.productName = productName;
        this.productid = productId;
        this.orderId = orderId;
    }
    
    public String getLogin() {
        return login;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getQuality() { 
        return quality;
    }

    public String getProductName() {
        return productName;
    }
    public int getProductId(){
        return productid;
    }
    
    public int getUserId(){
        return userid;
    }
    
}
