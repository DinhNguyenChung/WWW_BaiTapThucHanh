package vn.edu.iuh.fit.demo1.ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con = null;
    private static ConnectDB instance = new ConnectDB();
    private  ConnectDB(){

    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws SQLException {

           String url = "jdbc:sqlserver://localhost:1433;databasename=user_db";
           String user = "sa";
           String password = "sapassword";
           con = DriverManager.getConnection(url, user, password);

    }

    public void disconnect() {
        if(con != null) {
            try {
                con.close();

            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
