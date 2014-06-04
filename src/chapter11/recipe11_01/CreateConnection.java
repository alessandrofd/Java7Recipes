package chapter11.recipe11_01;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Alessandro on 04/05/2014.
 */
public class CreateConnection {
    static Properties props = null;

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
            props.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        loadProperties();
    }

    public void loadProperties() {
        hostname = props.getProperty("host_name");
        port = props.getProperty("port_number");
        database = props.getProperty("db_name");
        username = props.getProperty("username");
        password = props.getProperty("password");
        jndi = props.getProperty("jndi");
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup(this.jndi);
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        CreateConnection createConnection = new CreateConnection();
        createConnection.getConnection();
    }
}
