package vn.edu.iuh.fit.demo1.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.demo1.Dao.dao;
import vn.edu.iuh.fit.demo1.entity.Person;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        dao daoP = new dao();
        Person p = daoP.loginUser(username, password);
        if (p != null) {
//            req.getSession().setAttribute("user", p);
            HttpSession session = req.getSession();
            session.setAttribute("user", p);
            resp.sendRedirect("index.jsp");
        }
        else {
            resp.sendRedirect("login.html?errol=1");
        }


    }
}
