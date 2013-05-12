/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import database.Connector;
import database.DBCredentials;
import extras.UserType;
import inventory.Item;
import inventory.ModelInventory;
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
/*
 * SELECT 
 "Users".login,
 COUNT("Orders".order_id) AS Amount  
 FROM 
 public."Orders", 
 public."Users"
 WHERE 
 "Orders".user_id = "Users".user_id
 GROUP BY
 "Users".login;

 */
public class ModelOrders {

//    private static final String DEFAULT_TABLE_NAME = "Users";
    private Connector con;

    public List<OrderAmount> getAllUsersWithOrders(UserType type) {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT " + "  \"Users\".login,"
                + "  COUNT(\"Orders\".order_id) AS Amount  "
                + "FROM " + "  public.\"Orders\", "
                + "  public.\"Users\""
                + "WHERE "
                + "  \"Orders\".user_id = \"Users\".user_id "
                + "GROUP BY "
                + "  \"Users\".login;";

        LinkedList<OrderAmount> list = new LinkedList<OrderAmount>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                int i = 0;
                OrderAmount item = new OrderAmount(
                        result.getString(i++),
                        result.getInt(i++));
                list.add(item);
            }
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<OrderForUser> getOrdersForUser(UserType type, String login) {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT "
                + "  \"Users\".login, "
                + "  \"Orders\".start_date, "
                + "  \"Orders\".end_date, "
                + "  \"OrderedItems\".quantity, "
                + "  \"Inventory\".name"
                + "FROM "
                + "  public.\"Users\", "
                + "  public.\"Orders\", "
                + "  public.\"OrderedItems\", "
                + "  public.\"Inventory\""
                + "WHERE \n"
                + "  \"Orders\".user_id = \"Users\".user_id AND"
                + "  \"Orders\".ordered_items_id = \"OrderedItems\".ordered_item_id AND"
                + "  \"OrderedItems\".item_id = \"Inventory\".item_id AND" + "  \"Users\".login = ?;";
        LinkedList<OrderForUser> list = new LinkedList<OrderForUser>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                int i = 0;
                OrderForUser item = new OrderForUser(
                        result.getString(i++),
                        result.getDate(i++),
                        result.getDate(i++),
                        result.getInt(i++),
                        result.getString(i++));
                list.add(item);
            }
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
