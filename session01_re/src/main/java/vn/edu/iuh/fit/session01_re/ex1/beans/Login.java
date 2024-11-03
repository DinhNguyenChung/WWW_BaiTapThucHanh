package vn.edu.iuh.fit.session01_re.ex1.beans;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("us");
        String password = req.getParameter("pwd");
        Loginbean lgb = new Loginbean();
        boolean rs = lgb.login(username, password);
        PrintWriter out = resp.getWriter();
        if (rs) {
            out.println("Chào mừng bạn đến vơi WWW");
        }
        else {
            out.println("Đăng nhập thất bại ");
        }
    }
}

