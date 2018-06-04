package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private final static String PROPERTIES_PATH = "application.properties";
    private final static String DB_URL_PARAM = "database.url";
    private final static String DB_USER_PARAM = "database.user";
    private final static String DB_PASS_PARAM = "database.pass";
    private final static String DB_DRIVER_PARAM = "database.driver";

    private Properties props;
    private Connection connection;

    public ConnectionUtil() {
        props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(PROPERTIES_PATH);
        try {
            props.load(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Connection getConnection() {
        String user = props.getProperty(DB_USER_PARAM);
        String pass = props.getProperty(DB_PASS_PARAM);
        String addr = props.getProperty(DB_URL_PARAM);
        String driver = props.getProperty(DB_DRIVER_PARAM);
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(addr,user,pass);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return connection;
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
