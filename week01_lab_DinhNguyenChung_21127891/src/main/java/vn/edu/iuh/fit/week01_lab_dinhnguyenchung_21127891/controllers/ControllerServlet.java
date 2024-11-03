package vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.entities.Account;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.repositories.LogRepository;
import vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.services.AccountServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controller", value = "/controller")
public class ControllerServlet extends HttpServlet {

    private AccountServices accountServices;
    private LogRepository logRepository;
    @Override
    public void init() throws ServletException {
        // Initialize the AccountServices instance manually
        accountServices = new AccountServices();
        logRepository = new LogRepository();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "login":
//                resp.sendRedirect("dashboard.jsp");
                handleLogin(req, resp);
                break;
            case "logout":
                handleLogout(req, resp);
                break;
            case "Xemdanhsach":
                    String role = req.getParameter("role");
                    System.out.println(role);
                List<Account> list = new ArrayList<>();
                try {
                    list = accountServices.getAccountByRolename(role);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                HttpSession session = req.getSession();
                session.setAttribute("list", list);
                // Chuyển hướng sang trang JSP để hiển thị danh sách
                RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(req, resp);
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "logout":
                resp.sendRedirect("index.jsp");
                break;

        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        Account account = accountServices.login(email, password);

        if(account!=null) {
            HttpSession session = req.getSession();
            session.setAttribute("Account", account);
            String role = accountServices.RoleId(account.getAccountId());
            session.setAttribute("Role", role);
            try {
                Long logId = logRepository.logLogin(account.getAccountId());
                session.setAttribute("LogId", logId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            resp.sendRedirect("dashboard.jsp");
            System.out.println("Dang Nhap thanh cong "+account);
        }else {
            req.setAttribute("error", "Invalid email or password");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            System.out.println("Dang Nhap that bai ");
        }
    }
    private void handleLogout(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Account account = (Account) session.getAttribute("Account");
            if (account != null) {
                try {
                    logRepository.logLogout(req);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            session.invalidate();
        }
        resp.sendRedirect("index.jsp");
    }

    private  void ShowRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

    }

}
