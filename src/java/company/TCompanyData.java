/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

/**
 *
 * @author Macimi
 */
public class TCompanyData {
    private String name;
    private String NIP;

    public TCompanyData(String name, String NIP) {
        this.name = name;
        this.NIP = NIP;
    }

    public String getName() {
        return name;
    }

    public String getNIP() {
        return NIP;
    }
    
}
