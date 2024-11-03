<%@ page import="vn.edu.iuh.fit.week01_lab_dinhnguyenchung_21127891.entities.Account" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 07/09/2024
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        Account account =(Account) session.getAttribute("Account");
        String role =(String) session.getAttribute("Role");
        if (account!=null) {
            System.out.println("Nhaanj dduowjc, "+account);
    %>
    <h1>Welcome, <%= role%></h1>
        <h2>
            Hello, <%= account.getFullName()%>
            !
        </h2>
        <p>
            <%=
                account.getEmail()
            %>
        </p>
        <p>
            <%=
                account.getPhone()
            %>
        </p>
<%
    }else {
    System.out.println("Khong nhan duoc session");

%>
<p>
    Khong nhan duoc session
</p>
<%
    }
%>
<%--<a href="ControlServlet?action=logout" name="action" value="logout">Logout</a>--%>
<%--<a href="${pageContext.request.contextPath}/ControlServlet?action=logout" name="action" value="logout">Logout</a>--%>
<form action="controller" method="post">
    <input type="submit" name="action" value="logout" >

</form>
<hr>
<h2>Account Management</h2>
<!-- Add forms for adding, updating, deleting accounts and granting roles -->
    <form action="controller" method="post">
        <label><input type="radio" name="role" value="administrator" /> Admin</label>
        <label><input type="radio" name="role" value="user" /> User</label>

        <input type="submit" name="action" value="Xemdanhsach" />

    </form>
<hr>
    <h2>List Account By Role Name</h2>
    <table border="1">
        <thead>
        <tr>
            <th>Account ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Account> accounts = (List<Account>) session.getAttribute("list");
            if (accounts != null && !accounts.isEmpty()) {
                for (Account account1 : accounts) {
        %>
        <tr>
            <td><%= account1.getAccountId() %></td>
            <td><%= account1.getFullName() %></td>
            <td><%= account1.getEmail() %></td>
            <td><%= account1.getPhone() %></td>
            <td><%= account1.getStatus() == 1 ? "Active" : "Inactive" %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5">No accounts found</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</body>
</html>
