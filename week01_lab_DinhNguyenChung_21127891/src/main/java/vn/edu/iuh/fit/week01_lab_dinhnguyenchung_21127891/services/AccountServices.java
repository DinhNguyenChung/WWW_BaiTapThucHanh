package vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.services;

import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.dtos.AccountDto;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.dtos.LogDto;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.entities.Account;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.entities.Log;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.repositories.AccountRepository;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.repositories.LogRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public  class AccountServices  {
  private AccountRepository accountRepository;
  private LogRepository logRepository;
    public AccountServices() {
        // Initialize accountRepository manually
        this.accountRepository = new AccountRepository();
        this.logRepository = new LogRepository();
    }
  public Account login(String email, String password) {
      Account account = accountRepository.findAccountByEmail(email);
      if (account != null && account.getPassword().equals(password)) {
          return account;
      }
      return null;
  }
    public String RoleId(String accountId) {
        return  accountRepository.findRoleName(accountId);
        }
    public List<Account> getAccountByRolename(String roleName) throws SQLException {
        return accountRepository.getAccountByRole(roleName);
    }

}
