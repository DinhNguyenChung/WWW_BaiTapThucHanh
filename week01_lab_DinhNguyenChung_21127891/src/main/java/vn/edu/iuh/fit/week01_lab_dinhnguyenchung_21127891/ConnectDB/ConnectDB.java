package vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "sapassword";

    static {
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MariaDB JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println(URL);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
