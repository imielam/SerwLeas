/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import extras.UserType;

/**
 *
 * @author Macimi
 */
public class TUserData {
    private String login;
    private String password = "";
    private String email;
    private int companuId;
    private int userType;
    private String pesel;

    public TUserData(String login, String email, int companuId, int userType, String pesel) {
        this.login = login;
        this.email = email;
        this.companuId = companuId;
        this.userType = userType;
        this.pesel = pesel;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getCompanuId() {
        return companuId;
    }

    public int getUserType() {
        return userType;
    }

    public String getPesel() {
        return pesel;
    }
    
}
