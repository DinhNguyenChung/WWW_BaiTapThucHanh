package vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.ConnectDB.ConnectDB;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.dtos.AccountDto;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.entities.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccountRepository  {
        @PersistenceContext(unitName = "mydb")
        private EntityManager em;
        public void save(Account account) {
//            em.getTransaction().begin();
            em.persist(account);
//            em.getTransaction().commit();

        }

//    public Account findAccountByEmail(String email) {
//            try {
//                return em.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class)
//                        .setParameter("email", email)
//                        .getSingleResult();
//            }catch(NoResultException e) {
//                return null;
//            }
//    }
    public Account findAccountByEmail(String email) {

            try(Connection con= ConnectDB.getConnection())
            {Account account= new Account();
                String query = "Select * from account where email = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()){
                    String id = rs.getString("account_id");
                    String name = rs.getString("full_name");
                    String password = rs.getString("password");
//                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    Byte status = rs.getByte("status");

                    account.setAccountId(id);
                    account.setFullName(name);
                    account.setPassword(password);
                    account.setPhone(phone);
                    account.setEmail(email);
                    account.setStatus(status);
                    account.setStatus(status);


                }return account;


            }catch (SQLException e) {
                return null;
//                e.printStackTrace();
            }
    }

    public String findRoleName(String accountId) {
            try(Connection connection = ConnectDB.getConnection()) {
                String query = "Select role_name from account a join grant_access ga  on a.account_id = ga.account_id where a.account_id = ?";
                 PreparedStatement stmt = connection.prepareStatement(query);
                 stmt.setString(1, accountId);
                 ResultSet rs = stmt.executeQuery();
                 if (rs.next()) {
                     return rs.getString("role_id");
                 }
            }catch (SQLException e) {

                e.printStackTrace();
            }return "";
    }
    public List<Account> getAccountByRole(String roleName) throws SQLException {
            List<Account> accounts = new ArrayList<>();
            String query = "SELECT a.* FROM account a " +
                    "JOIN grant_access ga ON a.account_id = ga.account_id " +
                    "JOIN role r ON ga.role_id = r.role_id " +
                    "WHERE r.role_name = ? AND ga.is_grant = 1";
            try(Connection con = ConnectDB.getConnection()) {
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, roleName);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("account_id");
                    String name = rs.getString("full_name");
                    String password = rs.getString("password");
                    String phone = rs.getString("phone");
                    Byte status = rs.getByte("status");
                    Account account = new Account();
                    account.setAccountId(id);
                    account.setFullName(name);
                    account.setPassword(password);
                    account.setPhone(phone);
                    account.setStatus(status);
                    accounts.add(account);
                }
                return  accounts;
            }
    }
}
