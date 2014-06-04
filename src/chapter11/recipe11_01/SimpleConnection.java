package chapter11.recipe11_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alessandro on 04/05/2014.
 */
public class SimpleConnection {
    public static void main(String[] args) {
        String connectionURL = "jdbc:derby://localhost:1527/java7recipes;";
        try {
            Connection conn = DriverManager.getConnection(connectionURL);
            System.out.println("Successfully connected to " + connectionURL);
        } catch (Throwable e) {
            System.out.println(". . . exception thrown:");
            errorPrint(e);
        }

    }

    static void errorPrint(Throwable e) {
        if (e instanceof SQLException)
            SQLPrintException((SQLException)e);
        else {
            System.out.println("A non SQL error occurred.");
            e.printStackTrace();
        }
    }

    static void SQLPrintException(SQLException sqle) {
        while (sqle != null) {
            System.out.println("\n---SQLException caught---\n");
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("Severity: " + sqle.getErrorCode());
            System.out.println("Message: " + sqle.getMessage());
            sqle.printStackTrace();
            sqle = sqle.getNextException();
        }
    }

}
