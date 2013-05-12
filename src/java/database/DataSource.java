/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
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
    private static final String DEFAULT_HOST = "//192.168.0.23:5432/";
    private static final String DEFAULT_DATABASE = "bezpieczenstwooporogramowania";
    private static final String DEFAULT_ADDRESS = DEFAULT_HOST + DEFAULT_DATABASE;
    private static final String DEFAULT_USER = "dbguest";
    private static final String DEFAULT_PASSWORD = "password123";
    private static final String DEFAULT_TABLE = "Users";
    private Connection connection = null;
    private Statement s;
    private String tablica;
    private HashMap<String, String> userSource = new HashMap<String, String>();

    public DataSource() {
        this(DEFAULT_ADDRESS, DEFAULT_USER, DEFAULT_PASSWORD, DEFAULT_TABLE);
    }

    public DataSource(String adres, String user, String password, String tab) {
        tablica = tab;
//        generateTestData();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!" + e.getMessage());
            return;
        }
        connection = null;
        try {
            System.out.println("jdbc:postgresql:" + adres);
            connection = DriverManager.getConnection("jdbc:postgresql:" + adres, user, password);
            s = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e.getMessage());
        }
    }

    public boolean userExists(User user) {
        ResultSet rs;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM \"Users\" WHERE login = ? AND password = ?");
            st.setString(1, user.getName());
            st.setString(2, user.getPassword());
            rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("nei powiodło się: "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }

//    private void generateTestData() {
//        userSource.put("admin", "test");
//        userSource.put("user", "passUser");
//    }
}
