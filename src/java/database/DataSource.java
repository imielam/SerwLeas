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

    public boolean userExists(User user) {
        ResultSet rs;
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM \"Users\" WHERE login = ? AND password = ?");
            st.setString(1, user.getName().trim());
            st.setString(2, user.getPassword().trim());
            rs = st.executeQuery();
            con.closeConnection();
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("nie powiodło się: "+ ex.getMessage());
            return false;
        }
    }
}
