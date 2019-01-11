<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> >
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/site.css">
<!-- Bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body class="text-center">
<b>${notification}</b><br>
<c:remove var="notification" scope="session"/>
<c:if test="${sessionScope.user == null}">
<form class="form-signin" method="POST" action="./login">
<h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
<input type="text" name="username" placeholder="Username" required><br>
<input type="password" name="password" placeholder="Password" required><br>
<input type="submit" value="Login" class="btn btn-primary mt-3">
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