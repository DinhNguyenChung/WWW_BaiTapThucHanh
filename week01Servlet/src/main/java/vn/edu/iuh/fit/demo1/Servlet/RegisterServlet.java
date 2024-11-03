package vn.edu.iuh.fit.demo1.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.demo1.Dao.dao;
import vn.edu.iuh.fit.demo1.entity.Person;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        Person person = new Person(firstName, lastName, user, password);
        dao daoPerson = new dao();
        boolean addUser = daoPerson.registerUser(person);
        if (addUser) {
            resp.sendRedirect("/login.html");

        }
        else {
            resp.sendRedirect("/Register.html?error=1");
        }
    }
}
