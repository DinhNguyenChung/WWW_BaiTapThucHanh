package vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.ConnectDB.ConnectDB;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.entities.Log;

import java.sql.*;
import java.util.Optional;

@Transactional
public class LogRepository {
    @PersistenceContext(unitName = "mydb")
    private EntityManager em;
    public void save (Log log) {
        em.persist(log);

    }
    public Log findById(int id) {
        return em.find(Log.class, id);
    }
    public void update (Log log) {
        em.merge(log);
    }
    public Optional<Log> findAll( Integer accountId) {
        try {
            Log log = em.createQuery("SELECT l FROM Log l WHERE l.accountId = :accountId ORDER BY l.loginTime DESC", Log.class)
                    .setParameter("accountId", accountId)
                    .setMaxResults(1)
                    .getSingleResult();
            return Optional.of(log);
        }catch (Exception e) {
            return Optional.empty();
        }
    }
    public void logLogout(HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("LogId");
        try (Connection con = ConnectDB.getConnection()) {
//            String query = "UPDATE log SET logout_time = CURRENT_TIMESTAMP WHERE account_id = ? AND logout_time IS NULL";
            String query = "UPDATE log SET logout_time = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, id);
//            stmt.executeUpdate();
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);
        }

    }
//    public void logLogin(String accountId) throws SQLException {
//        try (Connection con = ConnectDB.getConnection()) {
////            String query = "INSERT INTO log (account_id) VALUES (?)";
////            String query = "INSERT INTO log (account_id, login_time, logout_time, notes) VALUES (?, CURRENT_TIMESTAMP, NULL, ?)";
//            String query = "INSERT INTO log (account_id, login_time, logout_time) VALUES (?, CURRENT_TIMESTAMP, NULL)";
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setString(1, accountId);
////            stmt.setString(2,"User logged in");
//            stmt.executeUpdate();
//        }
//    }
    public long logLogin(String accountId) throws SQLException {
        try (Connection con = ConnectDB.getConnection()) {
            String query = "INSERT INTO log (account_id, login_time, logout_time) VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, accountId);
//            stmt.setString(2, "User logged in");
            stmt.executeUpdate();

            // Lấy log_id vừa tạo ra
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getLong(1));
                return rs.getLong(1);  // Trả về log_id
            }

        }
        return -1;
    }

}
