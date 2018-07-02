/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tam
 */
public class ConnectDB {

    private static final String PROTOCOL = "jdbc:mysql://";
    private static final String SERVER = "localhost";
    private static final int PORT = 3306;
    private static final String DBNAME = "DemoJetty";
    private static final String USER = "root";
    private static final String PASSWORD = "Tam12345`";
    private static final String SSL = "?autoReconnect=true&useSSL=false";

    private String GetURL() {
        String URL = PROTOCOL + SERVER+":"+PORT +"/" + DBNAME +SSL;
        return URL;
    }
    
    public Connection getConnection()
    {
        Connection cnn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection(this.GetURL(),USER,PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cnn;
    }
}
