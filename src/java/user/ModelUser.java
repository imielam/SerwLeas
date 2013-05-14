/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import address.TAddressData;
import company.TCompanyData;
import database.Connector;
import database.DBCredentials;
import extras.UserType;
import inventory.ModelInventory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import orders_new.ModelOrders;

/**
 *
 * @author Macimi
 */
public class ModelUser {

    private Connector con;

    private int addAddress(TAddressData address) throws SQLException {
        String sql = "INSERT INTO \"Address\" (postal_code, street, street_hn, street_an, city) VALUES (?, ?, ?, ?, ?);";
//        String sql = "INSERT INTO \"Address\" (postal_code, street, street_hn, street_an, city) VALUES ('00-000', 'maciek', '1', '1', 'maciek');";
        PreparedStatement st = con.prepareStatement(sql);
        int i = 1;
        st.setString(i++, address.getPostalCode());
        st.setString(i++, address.getStreet());
        st.setString(i++, address.getStreetHn());
        st.setInt(i++, address.getStreetAn());
        st.setString(i++, address.getCity());
        System.out.println(st);
        st.executeUpdate();

        sql = "SELECT \"Address\".address_id FROM \"Address\" WHERE postal_code = ? AND street = ? AND street_hn = ? AND street_an = ? AND city = ?;";
        st = con.prepareStatement(sql);
        i = 1;
        st.setString(i++, address.getPostalCode());
        st.setString(i++, address.getStreet());
        st.setString(i++, address.getStreetHn());
        st.setInt(i++, address.getStreetAn());
        st.setString(i++, address.getCity());
        ResultSet result = st.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        } else {
            throw new SQLException("Wystąpił błąd. Cofam zmiany.");
        }
    }

    private boolean precheckLoginData(User user) {
        Pattern lettersOnly = Pattern.compile("[a-zA-Z]+");
        Matcher m = lettersOnly.matcher(user.getName());
        if (!m.matches() || user.getName().length() < 4 || user.getName().length() > 16) {
            return false;
        }
        //haslo jest uprzednio hashowane wiec nie trzeba sprawdzac czy ktos wrzucil cos szkodliwego

        return true;
    }

    public TUserData userExists(User user) {
        return userExists(UserType.GUEST, user);
    }

    public TUserData userExists(UserType type, User user) {
        if (!precheckLoginData(user)) {
            return new TUserData();
        }
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        try {
        ResultSet rs;
            PreparedStatement st = con.prepareStatement("SELECT * FROM \"Users\" WHERE login = ? AND password = ?");
            st.setString(1, user.getName().trim());
            st.setString(2, user.getPassword().trim());
            rs = st.executeQuery();
            con.closeConnection();
            if (rs.next()) {
                int i = 1;
                TUserData tud = new TUserData(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6));
                return tud;
            }
            return new TUserData();
        } catch (SQLException ex) {
            System.err.println("nie powiodło się: " + ex.getMessage());
            return new TUserData();
        }

    }

    private int addCompany(TCompanyData company, int addressId) throws SQLException {
        String sql = "INSERT INTO \"Company\" (address_id, name, \"NIP\") VALUES (?, ?, ?);";
        PreparedStatement st = con.prepareStatement(sql);
        int i = 1;
        st.setInt(i++, addressId);
        st.setString(i++, company.getName());
        st.setString(i++, company.getNIP());
        st.executeUpdate();

        sql = "SELECT \"Company\".company_id FROM \"Company\" WHERE address_id = ? AND name = ? AND \"NIP\" = ? ;";
        st = con.prepareStatement(sql);
        i = 1;
        st.setInt(i++, addressId);
        st.setString(i++, company.getName());
        st.setString(i++, company.getNIP());
        ResultSet result = st.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        } else {
            throw new SQLException("Wystąpił błąd. Cofam zmiany.");
        }
    }

    private void addUser(TUserData user, int companyId) throws SQLException {
        String sql = "INSERT INTO \"Users\" (login, password, email, company_id, user_type, pesel) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement st = con.prepareStatement(sql);
        int i = 1;
        st.setString(i++, user.getLogin());
        st.setString(i++, user.getPassword());
        st.setString(i++, user.getEmail());
        st.setInt(i++, companyId);
        st.setInt(i++, user.getUserType());
        st.setString(i++, user.getPesel());
        st.executeUpdate();
    }

    public void addNewUser(UserType type, TUserData user, TCompanyData company, TAddressData address) {
        con = new Connector(DBCredentials.getInstance().getDBUserByType(type));
        try {
            con.startTransaction();
            addUser(user, addCompany(company, addAddress(address)));
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
