/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author Ponury
 */
public class Item {

    private int id;
    private String name;
    private String description;
    private int quantity;
    private int aviable;
    private float base_price;

    public Item(int id, String name, String desc, int quant, int avi, float bp) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.quantity = quant;
        this.aviable = avi;
        this.base_price = bp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAviable() {
        return aviable;
    }

    public float getBase_price() {
        return base_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAviable(int aviable) {
        this.aviable = aviable;
    }

    public void setBase_price(float base_price) {
        this.base_price = base_price;
    }
}
