/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

/**
 *
 * @author Macimi
 */
public class TDeleteData {
    private String productName;
    private int quantity;
    private String login;

    public TDeleteData(String productName, int quantity, String login) {
        this.productName = productName;
        this.quantity = quantity;
        this.login = login;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLogin() {
        return login;
    }
    
}
