/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders_new;

import database.Connector;
import database.DBCredentials;
import extras.TDeleteData;
import extras.TNewOrder;
import extras.UserType;
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
public class ModelOrders {

//    private static final String DEFAULT_TABLE_NAME = "Users";
    private Connector con;

    public List<TOrderAmount> getAllUsersWithOrders(UserType type) {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT " + "  \"Users\".login,"
                + "  COUNT(\"Orders\".order_id) AS Amount  "
                + "FROM " + "  public.\"Orders\", "
                + "  public.\"Users\""
                + "WHERE "
                + "  \"Orders\".user_id = \"Users\".user_id "
                + "GROUP BY "
                + "  \"Users\".login;";

        LinkedList<TOrderAmount> list = new LinkedList<TOrderAmount>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                int i = 1;
                TOrderAmount item = new TOrderAmount(
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

    public List<TOrderForUser> getOrdersForUser(UserType type, String login) {
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
        LinkedList<TOrderForUser> list = new LinkedList<TOrderForUser>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                int i = 1;
                TOrderForUser item = new TOrderForUser(
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

    public void deleteOrder(UserType type, TDeleteData deleteData) {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT " + "OrderedItems\".item_id, "
                + "  \"OrderedItems\".quantity, "
                + "  \"OrderedItems\".ordered_item_id" + "FROM "
                + "  public.\"Users\", " + "  public.\"OrderedItems\", "
                + "  public.\"Orders\", " + "  public.\"Inventory\" " + "WHERE "
                + "  \"OrderedItems\".item_id = \"Inventory\".item_id AND "
                + "  \"Orders\".user_id = \"Users\".user_id AND"
                + "  \"Orders\".ordered_items_id = \"OrderedItems\".ordered_item_id AND"
                + "  \"OrderedItems\".quantity = ? AND" + "  \"Inventory\".name = ? AND"
                + "  \"Users\".login = ?;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, deleteData.getQuantity());
            st.setString(2, deleteData.getProductName());
            st.setString(3, deleteData.getLogin());
            ResultSet result = st.executeQuery();
            TOrderedItem oi = null;
            if (result.next()) {
                int i = 1;
                oi = new TOrderedItem(result.getInt(i++), result.getInt(i++), result.getInt(i++));
            } else {
                throw new SQLException("Nie znalazłem danych do usunięcia. Cofam zmiany.");
            }
            con.startTransaction();
            sql = "DELETE FROM \"OrderedItems\" WHERE \"OrderedItems\".ordered_item_id = ?;";
            st = con.prepareStatement(sql);
            st.setInt(1, oi.getOrderedItemId());
            st.executeQuery();
            sql = "SELECT "
                    + "  \"Inventory\".available"
                    + "FROM "
                    + "  public.\"Inventory\""
                    + "WHERE "
                    + "  \"Inventory\".item_id = ?;";
            st = con.prepareStatement(sql);
            st.setInt(1, oi.getItemId());
            result = st.executeQuery();
            int available = 0;
            if (result.next()) {
                available = result.getInt(1);
            }
            sql = "UPDATE \"Inventory\" SET available = ? WHERE item_id = ?;";
            st = con.prepareStatement(sql);
            st.setInt(1, available + oi.getQuantity());
            st.setInt(2, oi.getItemId());
            st.executeQuery();
            con.commit();
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
                con.closeConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(ModelOrders.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void addNewOrder(UserType type, TNewOrder newOrder) {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));

        try {
            con.startTransaction();
            String sql = "SELECT "
                    + "  \"Inventory\".available"
                    + "FROM "
                    + "  public.\"Inventory\""
                    + "WHERE "
                    + "  \"Inventory\".item_id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, newOrder.getItemId());
            ResultSet result = st.executeQuery();
            int available = 0;
            if (result.next()) {
                available = result.getInt(1);
            }
            sql = "UPDATE \"Inventory\" SET available = ? WHERE item_id = ?;";
            st = con.prepareStatement(sql);
            st.setInt(1, available - newOrder.getQuantity());
            st.setInt(2, newOrder.getItemId());
            st.executeQuery();

            sql = "INSERT INTO \"OrderedItems\" (item_id, quantity) VALUES (?, ?);";
            st = con.prepareStatement(sql);
            st.setInt(1, newOrder.getItemId());
            st.setInt(2, newOrder.getQuantity());
            st.executeQuery();

            sql = "SELECT \n"
                    + "  \"OrderedItems\".ordered_item_id"
                    + "FROM "
                    + "  public.\"OrderedItems\""
                    + "WHERE"
                    + "  \"OrderedItems\".item_id = ? AND "
                    + "  \"OrderedItems\".quantity = ?;";
            st = con.prepareStatement(sql);
            st.setInt(1, newOrder.getItemId());
            st.setInt(2, newOrder.getQuantity());
            result = st.executeQuery();
            int addedOrderedId = 0;
            if (result.next()){
                addedOrderedId = result.getInt(1);
            } else {
                throw new SQLException("Wystąpił błąd. Cofam zmiany.");
            }
            
            sql = "INSERT INTO \"Orderes\" (user_id, start_date, end_date, ordered_items_id) VALUES (?, ?, ?, ?);";
            st = con.prepareStatement(sql);
            st.setInt(1, newOrder.getUserId());
            st.setDate(2, newOrder.getStartDate());
            st.setDate(3, newOrder.getEndDate());
            st.setInt(4, addedOrderedId);
            st.executeQuery();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
                con.closeConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(ModelOrders.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
