/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Macimi
 */
public class Connector {
    private static final String DEFAULT_HOST = "//192.168.0.23:5432/";
    private static final String DEFAULT_DATABASE = "bezpieczenstwooporogramowania";
    private static final String DEFAULT_ADDRESS = DEFAULT_HOST + DEFAULT_DATABASE;
    private static final String DEFAULT_USER = "dbguest";
    private static final String DEFAULT_PASSWORD = "password123";
    private Connection connection = null;
    
    public Connector() {
        this(DEFAULT_ADDRESS, DEFAULT_USER, DEFAULT_PASSWORD);
    }
    
    public Connector(String user, String password) {
        this(DEFAULT_ADDRESS, DEFAULT_USER, DEFAULT_PASSWORD);
    }
    
    public Connector(String adres, String user, String password) {
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
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e.getMessage());
        }
    }
    
    public PreparedStatement prepareStatement(String sql) throws SQLException{
        return connection.prepareStatement(sql);
    }
    
    public void closeConnection() throws SQLException{
        connection.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }
    
    
}
