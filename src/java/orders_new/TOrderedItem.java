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
    private int orderedItemId;

    public TOrderedItem(int itemId, int quantity, int orderedItemId) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.orderedItemId = orderedItemId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderedItemId() {
        return orderedItemId;
    }
    
}
