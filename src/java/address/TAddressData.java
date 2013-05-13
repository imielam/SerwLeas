/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package address;

/**
 *
 * @author Macimi
 */
public class TAddressData {
    private String postalCode;
    private String street;
    private String streetHn;
    private int streetAn;
    private String city;

    public TAddressData(String postalCode, String street, String streetHn, int streetAn, String city) {
        this.postalCode = postalCode;
        this.street = street;
        this.streetHn = streetHn;
        this.streetAn = streetAn;
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetHn() {
        return streetHn;
    }

    public int getStreetAn() {
        return streetAn;
    }

    public String getCity() {
        return city;
    }
    
}
