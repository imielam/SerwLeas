/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Orders;

import java.sql.Date;

/**
 *
 * @author Macimi
 */
public class OrderForUser {
    private String login;
    private Date startDate;
    private Date endDate;
    private int quality;
    private String productName;
    
    public OrderForUser(String login, Date startDate, Date endDate, int quality, String productName) {
        this.login = login;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quality = quality;
        this.productName = productName;
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
    
}