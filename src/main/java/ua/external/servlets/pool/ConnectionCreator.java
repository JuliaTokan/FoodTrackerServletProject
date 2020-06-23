package ua.external.servlets.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConnectionCreator {
    public Connection createConnection() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("db.database");
            String url = resource.getString("url");
            String user = resource.getString("user");
            String pass = resource.getString("password");
            String driver = resource.getString("driver");
            Class.forName(driver);
            return DriverManager.getConnection(url);

        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Driver is not found" + e.getMessage(), e);
        }
    }
}
