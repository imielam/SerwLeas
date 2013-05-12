/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import extras.UserType;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Macimi
 */
public class DBCredentials {

    private static DBCredentials itself = null;
    private Map<UserType, DBUser> credentials = new EnumMap<UserType, DBUser>(UserType.class);

    private DBCredentials() {
        credentials.put(UserType.GUEST, new DBUser("dbguest", "password123"));
        credentials.put(UserType.CLIENT, new DBUser("dbclient", "password123"));
        credentials.put(UserType.SALESMAN, new DBUser("dbsalesman", "password123"));
        credentials.put(UserType.ADMIN, new DBUser("dbadmin", "password123"));
    }

    public static DBCredentials getInstance() {
        if (itself == null) {
            itself = new DBCredentials();
        }
        return itself;
    }
    
    public DBUser getDBUserByType(UserType type){
        if (credentials.containsKey(type)){
            return credentials.get(type);
        } else {
            return credentials.get(UserType.GUEST);
        }
    }
}
