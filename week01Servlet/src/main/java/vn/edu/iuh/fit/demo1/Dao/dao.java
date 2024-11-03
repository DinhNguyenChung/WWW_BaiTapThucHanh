package vn.edu.iuh.fit.demo1.Dao;

import vn.edu.iuh.fit.demo1.ConnectDB.ConnectDB;
import vn.edu.iuh.fit.demo1.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dao {
    public dao(){

    }
    public boolean registerUser(Person person){

        try {
            ConnectDB.getInstance().connect();
            String sql = "insert into users(firstname,lastname,username,passwords) values(?,?,?,?)";
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getUserName());
            ps.setString(4, person.getPassword());
            int i = ps.executeUpdate();
            return i > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
//    public Person loginUser(String username, String password){
//        try {
//            ConnectDB.getInstance().connect();
//        } catch (SQLException ex) {
//            Logger.getLogger(dao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String query = "SELECT * FROM users WHERE username = ? AND passwords = ?";
//        try (
//
//                Connection conn = ConnectDB.getConnection();
//             PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            Person person = new Person();
//            person.setFirstName(rs.getString("firstname"));
//            person.setLastName(rs.getString("lastname"));
//            person.setUserName(rs.getString("username"));
//            person.setPassword(rs.getString("passwords"));
//            if (rs.next()) {
//                return person;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
public Person loginUser(String username, String password) {
    try {
        // Kết nối cơ sở dữ liệu
        ConnectDB.getInstance().connect();
    } catch (SQLException ex) {
        Logger.getLogger(dao.class.getName()).log(Level.SEVERE, null, ex);
    }

    String query = "SELECT * FROM users WHERE username = ? AND passwords = ?";
    try {
        Connection conn = ConnectDB.getConnection();

        if (conn == null) {
            throw new SQLException("Unable to establish database connection.");
        }

        // Chuẩn bị câu lệnh truy vấn
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        // Di chuyển con trỏ đến kết quả đầu tiên
        if (rs.next()) {
            Person person = new Person();
            person.setFirstName(rs.getString("firstname"));
            person.setLastName(rs.getString("lastname"));
            person.setUserName(rs.getString("username"));
            person.setPassword(rs.getString("passwords"));
            return person;  // Trả về thông tin người dùng nếu đăng nhập thành công
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;  // Trả về null nếu không tìm thấy kết quả
}

}
