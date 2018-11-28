<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/site.css">
</head>
<body>
<b>${message}</b><br>
<b>${failLogin}</b><br>
<form method="POST" action="./login">
Username: <input type="text" name="username" required><br>
Password: <input type="password" name="password" required><br>
<input type="submit" value="Login">
</form>
</body>
</html>