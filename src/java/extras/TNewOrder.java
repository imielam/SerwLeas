/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import java.sql.Date;

/**
 *
 * @author Macimi
 */
public class TNewOrder {
    private int itemId;
    private int userId;
    private int quantity;
    private Date startDate;
    private Date endDate;

    public TNewOrder(int itemId, int userId, int quantity, Date startDate, Date endDate) {
        this.itemId = itemId;
        this.userId = userId;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getItemId() {
        return itemId;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
}
