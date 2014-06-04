package chapter11.recipe11_03;

import java.sql.*;

public class QueryDatabase {

    public static Connection conn = null;

    public static void main(String[] args) {
        String connectionURL = "jdbc:derby://localhost:1527/java7recipes";
        try {
            conn = DriverManager.getConnection(connectionURL);
            System.out.println("Successfully connected to " + connectionURL);
            queryDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryDatabase() {
        String query = "SELECT recipe_num, name, description FROM recipes";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String recipe = resultSet.getString("recipe_num");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                System.out.println(recipe +"\t" + name + "\t" + description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
