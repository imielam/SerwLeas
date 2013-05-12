/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Ponury
 */
public class User {
 
    private String name;
    private String password;
    private int usertype;
    
    public User(){
        usertype = 0;
    }
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUsertype(int usertype){
        this.usertype = usertype;
    }
    
    public int getUsertype(){
        return usertype;
    }
}
