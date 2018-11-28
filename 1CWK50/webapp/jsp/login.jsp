<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<b>${message}</b><br>
<form method="POST" action="./home">
Name: <input type="text" name="name" required><br>
Password: <input type="password" name="password" required><br>
<input type="submit" value="Login">
</form>
</body>
</html>