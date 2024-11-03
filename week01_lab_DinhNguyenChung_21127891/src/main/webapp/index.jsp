<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<form action="controller" method="post">
    <input type="email" name="email" placeholder="Email" required /> <br> <br>
    <input type="password" name="password" placeholder="Password" required /> <br> <br>
    <input type="submit" name="action" value="login">
<%--    <button type="submit">Login</button>--%>
</form>
<p>${error}</p>
</body>
</html>