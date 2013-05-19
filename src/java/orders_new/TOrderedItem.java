/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders_new;

/**
 *
 * @author Macimi
 */
public class TOrderedItem {
    private int itemId;
    private int quantity;

    public TOrderedItem(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
