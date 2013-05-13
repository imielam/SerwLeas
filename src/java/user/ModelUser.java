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
