<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> >
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/site.css">
</head>
<body>
<b>${message}</b><br>
<b>${failLogin}</b><br>
<c:if test="${sessionScope.user == null}">
<form method="POST" action="./login">
Username: <input type="text" name="username" required><br>
Password: <input type="password" name="password" required><br>
<input type="submit" value="Login">
</form>
</c:if>
<c:if test="${sessionScope.user != null}">
<form method="POST" action="./logout" >
<h1>You are already logged in please log out before trying to log in.</h1>
<input type="submit" value="Logout">
</form>
</c:if>
</body>
</html>