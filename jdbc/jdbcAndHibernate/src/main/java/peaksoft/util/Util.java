package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String UserName = "postgres";
    private static String Password = "postgres";

    public static Connection getConnection() {
        Connection connection = null;
        try {
    connection = DriverManager.getConnection(
            URL,
            UserName,
            Password
    );
            System.out.println("Successfully connected to database...");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
