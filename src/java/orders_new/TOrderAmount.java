/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders_new;

/**
 *
 * @author Macimi
 */
public class TOrderAmount {
    private String userName;
    private int amount;

    public TOrderAmount(String userName, int amount) {
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
