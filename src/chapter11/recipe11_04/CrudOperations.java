package chapter11.recipe11_04;

import java.sql.*;

public class CrudOperations {
    public static Connection conn = null;

    public static void main(String[] args) {
        String connectionURL = "jdbc:derby://localhost:1527/java7recipes;";
        try {
            conn = DriverManager.getConnection(connectionURL);
            performCreate();
            performRead();
            performUpdate();
            performDelete();
            System.out.println("---Final State---");
            performRead();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void performCreate() {
        String sql = "INSERT INTO RECIPES VALUES(" +
                "NEXT VALUE FOR RECIPES_SEQ, " +
                "'11-4', " +
                "'Performing CRUD Operations', " +
                "'How to perform create, read, update, delete functions', " +
                "'Recipe text')";
        Statement statement = null;

        try {
            statement = conn.createStatement();
            // returns row count or 0 if not successful
            int result = statement.executeUpdate(sql);
            if (result > 0) {
                System.out.println("---Record Created---");
            } else {
                System.out.println("!! Record NOT Created !!");
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
        }
    }

    private static void performRead() {
        String query = "SELECT RECIPE_NUM, NAME, DESCRIPTION FROM RECIPES";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String recipe = resultSet.getString("RECIPE_NUM");
                String name = resultSet.getString("NAME");
                String descriptions = resultSet.getString("DESCRIPTION");

                System.out.println(recipe + "\t" + name + "\t" + descriptions);
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
        }
    }

    private static void performUpdate() {
        String query = "UPDATE RECIPES " +
                "SET RECIPE_NUM = '11-5' " +
                "WHERE RECIPE_NUM = '11-4'";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            int result = statement.executeUpdate(query);
            if (result > 0) {
                System.out.println("---Record Updated---");
            } else {
                System.out.println("!! Record NOT Updated !!");
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
        }
    }

    private static void performDelete() {
        String query = "DELETE FROM RECIPES WHERE RECIPE_NUM = '11-5'";
        Statement statement = null;

        try {
            statement = conn.createStatement();
            int result = statement.executeUpdate(query);
            if (result > 0) {
                System.out.println("---Record Deleted---");
            } else {
                System.out.println("!! Record NOT Deleted !!");
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
        }
    }

}
