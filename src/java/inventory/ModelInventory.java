/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import database.Connector;
import database.DBCredentials;
import extras.DbException;
import extras.UserType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Macimi
 */
public class ModelInventory {

    private static final String DEFAULT_TABLE_NAME = "Inventory";
    private Connector con;
    private List<Item> list = new LinkedList<Item>();
    
    public List<Item> getAllItems(UserType type) throws DbException {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT * FROM \"" + DEFAULT_TABLE_NAME + "\" ORDER BY item_id";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            execute(st);
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbException();
        }
        return list;
    }
    
    public List<Item> getAllAbailable(UserType type) throws DbException {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT * FROM \"" + DEFAULT_TABLE_NAME + "\" WHERE available > 0 ORDER BY item_id";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            execute(st);
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbException();
        }
        return list;
    }

    private void execute(PreparedStatement st) throws SQLException {
        list = new LinkedList<Item>();
        ResultSet result = st.executeQuery();
        while (result.next()) {
            int i = 1;
            Item item = new Item(
                    result.getInt(i++),
                    result.getString(i++),
                    result.getString(i++),
                    result.getInt(i++),
                    result.getInt(i++),
                    result.getDouble(i++));
            list.add(item);
        }
    }

}
