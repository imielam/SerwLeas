/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Macimi
 */
public class TUserData {
    private int userId;
    private String login;
    private String password;
    private String email;
    private int userType;
    private String pesel;

    public TUserData(int userId, String login, String password, String email, int userType, String pesel) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.pesel = pesel;
    }

    public TUserData() {
        this(0, "", "", "", 0, "");
    }
    
    public TUserData(int userId, String login, String email, int userType, String pesel) {
        this(userId, login, "", email, userType, pesel);
    }

    public int getUserId() {
        return userId;
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

    public int getUserType() {
        return userType;
    }

    public String getPesel() {
        return pesel;
    }
    
}
