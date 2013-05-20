/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders_new;

import database.Connector;
import database.DBCredentials;
import extras.DbException;
import extras.TDeleteData;
import extras.TNewOrder;
import extras.UserType;
import inventory.ModelInventory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<TOrderAmount> getAllUsersWithOrders(UserType type) throws DbException {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        //con = new Connector();
        String sql = "SELECT " + "  \"Users\".login, \n"
                + "  \"Users\".user_id, \n"
                + "  COUNT(\"Orders\".order_id) AS Amount \n "
                + "FROM " + "  public.\"Orders\", \n"
                + "  public.\"Users\"\n"
                + "WHERE \n"
                + "  \"Orders\".user_id = \"Users\".user_id \n"
                + "GROUP BY \n"
                + " \"Users\".user_id;";

        LinkedList<TOrderAmount> list = new LinkedList<TOrderAmount>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                int i = 1;
                TOrderAmount item = new TOrderAmount(
                        result.getString(i++),
                        result.getInt(i++),
                        result.getInt(i++));
                list.add(item);
            }
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            //throw new DbException();
        }
        return list;
    }

    public List<TOrderForUser> getOrdersForUser(UserType type, String login) throws DbException {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT \n"
                + "  \"Users\".login, \n"
                + "  \"Users\".user_id, \n"
                + "  \"Orders\".start_date, \n"
                + "  \"Orders\".end_date, \n"
                + "  \"OrderedItems\".quantity, \n"
                + "  \"Inventory\".item_id, \n"
                + "  \"Inventory\".name, \n"
                + "  \"Orders\".order_id\n"
                + "FROM \n"
                + "  public.\"Users\", \n"
                + "  public.\"Orders\", \n"
                + "  public.\"OrderedItems\", \n"
                + "  public.\"Inventory\"\n"
                + "WHERE \n"
                + "  \"Orders\".user_id = \"Users\".user_id AND\n"
                + "  \"Orders\".order_id = \"OrderedItems\".order_id AND \n"
                + "  \"OrderedItems\".item_id = \"Inventory\".item_id AND" + "  \"Users\".login = ?;";
        LinkedList<TOrderForUser> list = new LinkedList<TOrderForUser>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, login);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                int i = 1;
                TOrderForUser item = new TOrderForUser(
                        result.getString(i++),
                        result.getInt(i++),
                        result.getDate(i++),
                        result.getDate(i++),
                        result.getInt(i++),
                        result.getInt(i++),
                        result.getString(i++),
                        result.getInt(i++));
                list.add(item);
            }
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbException();
        }
        return list;
    }

    public void deleteOrder(UserType type, int orderId) throws DbException {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        String sql = "SELECT \n"
                + "  \"OrderedItems\".item_id, \n"
                + "  \"OrderedItems\".quantity\n"
                + "FROM \n"
                + "  public.\"Orders\", \n"
                + "  public.\"OrderedItems\", \n"
                + "  public.\"Inventory\"\n"
                + "WHERE \n"
                + "  \"Orders\".order_id = \"OrderedItems\".order_id AND\n"
                + "  \"OrderedItems\".item_id = \"Inventory\".item_id AND\n"
                + "  \"OrderedItems\".order_id = ?;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet result = st.executeQuery();
            List<TOrderedItem> list = new ArrayList<TOrderedItem>();
            while (result.next()) {
                int i = 1;
                TOrderedItem oi = new TOrderedItem(result.getInt(i++), result.getInt(i++));
                list.add(oi);
            }
            if (list.size() < 0) {
                throw new SQLException("Nie znalazłem danych do usunięcia. Cofam zmiany.");
            }

            con.startTransaction();
            sql = "DELETE FROM \"Orders\" WHERE \"Orders\".order_id = ?;";
            st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            st.executeUpdate();
            for (int j = 0; j < list.size(); j++) {
                TOrderedItem oi = list.get(j);
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
                st.executeUpdate();
            }
            con.commit();
            con.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
                con.closeConnection();
                throw new DbException();
            } catch (SQLException ex1) {
                Logger.getLogger(ModelOrders.class.getName()).log(Level.SEVERE, null, ex1);
                throw new DbException();
            }
        }
    }

    public void addNewOrder(UserType type, Order newOrder) throws DbException {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        try {
            con.startTransaction();

            String sql = "INSERT INTO \"Orders\" (user_id, start_date, end_date) VALUES (?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, newOrder.getUserid());
            st.setDate(2, newOrder.getStartdate());
            st.setDate(3, newOrder.getEnddate());
            st.executeUpdate();

            sql = "SELECT \"order_id\" FROM \"Orders\" ORDER BY \"order_id\" DESC LIMIT 1;";
            st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            int orderID = 0;
            if (result.next()) {
                orderID = result.getInt(1);
            }

            for (int i = 0; i < newOrder.getOrdereditems().size(); i++) {
                OrderedItem oi = newOrder.getOrdereditems().get(i);
                 sql = "SELECT \n"
                        + "  \"Inventory\".available\n"
                        + "FROM \n"
                        + "  public.\"Inventory\"\n"
                        + "WHERE\n"
                        + "  \"Inventory\".item_id = ?;";
                st = con.prepareStatement(sql);
                st.setInt(1, oi.itemid);
                result = st.executeQuery();
                int available = 0;
                if (result.next()) {
                    available = result.getInt(1);
                }
                if (available == 0) {
                    throw new DbException("Twoje zamówienie zawiera niedostępne produkty!");
                }
                sql = "UPDATE \"Inventory\" SET available = ? WHERE item_id = ?;";
                st = con.prepareStatement(sql);
                st.setInt(1, available - oi.quantity);
                st.setInt(2, oi.itemid);
                st.executeUpdate();

                sql = "INSERT INTO \"OrderedItems\" (item_id, quantity, order_id) VALUES (?, ?, ?);";
                st = con.prepareStatement(sql);
                st.setInt(1, oi.itemid);
                st.setInt(2, oi.quantity);
                st.setInt(3, orderID);
                st.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
                con.closeConnection();
                throw new DbException();
            } catch (SQLException ex1) {
                Logger.getLogger(ModelOrders.class.getName()).log(Level.SEVERE, null, ex1);
                throw new DbException();
            }
        } catch (DbException e) {
            try {
                con.rollback();
                con.closeConnection();
                throw e;
            } catch (SQLException ex1) {
                Logger.getLogger(ModelOrders.class.getName()).log(Level.SEVERE, null, ex1);
                throw new DbException();
            }
        }
    }
}
