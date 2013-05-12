/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Orders;

/**
 *
 * @author Macimi
 */
public class OrderAmount {
    private String userName;
    private int amount;

    public OrderAmount(String userName, int amount) {
        this.userName = userName;
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public int getAmount() {
        return amount;
    }
    
    
}
