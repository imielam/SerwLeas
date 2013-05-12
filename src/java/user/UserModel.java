/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;


/**
 *
 * @author Macimi
 */
public class UserModel {
//    private static final String DEFAULT_TABLE_NAME = "Users";
//    private Connector con;
//    private List<Item> list = new LinkedList<Item>();
//    
//    public List<Item> getAllItems(UserType type) {
//        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
//        String sql = "SELECT * FROM \"" + DEFAULT_TABLE_NAME + "\" ORDER BY item_id";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            execute(st);
//            con.closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    
//    public List<Item> getAllAbailable(UserType type) {
//        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
//        String sql = "SELECT * FROM \"" + DEFAULT_TABLE_NAME + "\" WHERE available > 0 ORDER BY item_id";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            execute(st);
//            con.closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(ModelInventory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//
//    private void execute(PreparedStatement st) throws SQLException {
//        list = new LinkedList<Item>();
//        ResultSet result = st.executeQuery();
//        while (result.next()) {
//            int i = 2;
//            Item item = new Item(
//                    result.getString(i++),
//                    result.getString(i++),
//                    result.getInt(i++),
//                    result.getInt(i++),
//                    result.getDouble(i++));
//            list.add(item);
//        }
//    }

}

