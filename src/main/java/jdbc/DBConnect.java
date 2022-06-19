package jdbc;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    static Connection conn;

    public static Connection getDBConnection(String dbName) throws SQLException {
        String host = "localhost";
        String port = "5432";
        String user = "postgres";
        String password = "123456";

        String dbUrl = "jdbc:postgresql://"+ host +":" + port + "/"+ dbName + "?loggerLevel=OFF";

        try {
            conn = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
