package ua.external.servlets.pool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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

            /*URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

            return DriverManager.getConnection(dbUrl, username, password);*/

        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Driver is not found" + e.getMessage(), e);
        } /*catch (URISyntaxException e) {
            throw new IllegalArgumentException("Url is not found" + e.getMessage(), e);
        }*/
    }
}
