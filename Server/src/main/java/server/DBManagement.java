package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManagement {
    private static Connection connection;



    public static Statement connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("JDBC:sqlite:users.db");
        return connection.createStatement();
    }

}
