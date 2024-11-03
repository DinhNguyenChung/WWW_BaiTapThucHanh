<%@ page import="vn.edu.iuh.fit.demo1.entity.Person" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Wellcom you!" %>
</h1>
<%
    jakarta.servlet.http.HttpSession sessions = request.getSession(false); // Lấy session hiện tại
    vn.edu.iuh.fit.demo1.entity.Person user = null;
    if (sessions != null && sessions.getAttribute("user") != null) {
        user = (Person) sessions.getAttribute("user"); // Lấy thông tin người dùng từ session
    } else {
        response.sendRedirect("login.html"); // Chuyển hướng nếu chưa đăng nhập
    }
%>
<h1>
    <%= "Welcome, " + (user != null ? user.getUserName() : "") + "!" %> <!-- Hiển thị tên người dùng -->
</h1>
<br/>
<br/>
<a href="login.html">login</a> <br><br>
<a href="Register.html">Register</a>
</body>
</html>
<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ page import="jakarta.servlet.http.HttpSession" %>--%>
<%--<%@ page import="vn.edu.iuh.fit.demo1.entity.Person" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Welcome</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%--%>
<%--    jakarta.servlet.http.HttpSession sessions = request.getSession(false); // Lấy session hiện tại--%>
<%--    vn.edu.iuh.fit.demo1.entity.Person user = null;--%>
<%--    if (sessions != null && sessions.getAttribute("user") != null) {--%>
<%--        user = (Person) sessions.getAttribute("user"); // Lấy thông tin người dùng từ session--%>
<%--    } else {--%>
<%--        response.sendRedirect("login.html"); // Chuyển hướng nếu chưa đăng nhập--%>
<%--    }--%>
<%--%>--%>
<%--<h1>--%>
<%--    <%= "Welcome, " + (user != null ? user.getUserName() : "") + "!" %> <!-- Hiển thị tên người dùng -->--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="login.html">Login</a> <br><br>--%>
<%--<a href="register.html">Register</a>--%>
<%--</body>--%>
<%--</html>--%>
