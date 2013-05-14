/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;
import database.MD5;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Ponury
 * 
 */
public class User {
 
    private String name;
    private String password;
    private String email;
    private String pesel;
    private int usertype;
    private int userid;
    MD5  m = new MD5();
    public User(String name, String password, String email, String pesel, int usertype, int userid) {
        this.name = name;
        try{
            this.password = m.md5(password);
        } catch (NoSuchAlgorithmException nsae){
            
        }
        this.email = email;
        this.pesel = pesel;
        this.usertype = usertype;
        this.userid = userid;
    }

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
        try{
            this.password = m.md5(password);
        } catch (NoSuchAlgorithmException nsae){
            System.err.println(nsae);
        }
    }
    
    public void setUsertype(int usertype){
        this.usertype = usertype;
    }
    
    public int getUsertype(){
        return usertype;
    }
    
    public int getUserid() {
        return userid;
    }
    
    public void setUserid(int userid){
        this.userid = userid;
    }
}
