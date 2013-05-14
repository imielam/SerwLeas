/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import user.User;

/**
 *
 * @author Ponury
 */
public class DataSource {

    private Statement s;
    private String tablica;
    private HashMap<String, String> userSource = new HashMap<String, String>();
    private Connector con;

    public DataSource() {
        con = new Connector();
    }

    public DataSource(String user, String password) {
        con = new Connector(user, password);
    }

    public int userExists(User user) {
            if(!precheckLoginData(user)){
                return 0;
            }
        
            ResultSet rs;
            try {
                PreparedStatement st = con.prepareStatement("SELECT * FROM \"Users\" WHERE login = ? AND password = ?");

                st.setString(1, user.getName().trim());
                st.setString(2, user.getPassword().trim());
                rs = st.executeQuery();
                con.closeConnection();
                if (rs.next()) {
                    return rs.getInt("user_type");
                }
                return 0;
            } catch (SQLException ex) {
                System.err.println("nie powiodło się: " + ex.getMessage());
                return 0;
            }
        
    }

    public boolean precheckLoginData(User user) {
        Pattern lettersOnly = Pattern.compile("[a-zA-Z]+");
        Matcher m = lettersOnly.matcher(user.getName());
        if (!m.matches() || user.getName().length() < 4 || user.getName().length() > 16) {
            return false;
        }
        //haslo jest uprzednio hashowane wiec nie trzeba sprawdzac czy ktos wrzucil cos szkodliwego

        return true;
    }
}
