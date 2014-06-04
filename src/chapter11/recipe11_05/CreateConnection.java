package chapter11.recipe11_05;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CreateConnection {
    static Properties properties = new Properties();
    String hostname = null;
    String port = null;
    String database = null;
    String username = null;
    String password = null;
    String jndi = null;

    public CreateConnection() {
        InputStream in = null;
        try {
            in = Files.newInputStream(FileSystems.getDefault().getPath(System.getProperty("user.dir") + File.separator + "db_props.properties"));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadProperties();
    }

    public void loadProperties() {
        hostname = properties.getProperty("host_name");
        port = properties.getProperty("port_number");
        database = properties.getProperty("db_name");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        jndi = properties.getProperty("jndi");
    }

    /** Demonstrates obtaining a connection via DriverManager */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        String connectionURL = "jdbc:derby:" + this.hostname + ":" + this.port + "/" + this.database;
        conn = DriverManager.getConnection(connectionURL);
        System.out.println("Successfully connected to " + connectionURL);
        return conn;
    }

    /** Demonstrates obtaining a connection via a DataSource object */
    public Connection getDSConnection() {
        Connection conn = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource)context.lookup(this.jndi);
            conn = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        CreateConnection createConnection = new CreateConnection();
        try {
            createConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
