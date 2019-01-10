<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> >
<meta charset="ISO-8859-1">
<title>Add New Vehicle</title>
<link rel="stylesheet" type="text/css" href="css/site.css">
</head>
<body>
<b>${message}</b><br>
<c:choose>
	<c:when test="${sessionScope.user == 'admin'}">
	<h1>Please insert new vehicle's details.</h1>
	<form method="POST" action="./addVehicle">
	ID:<input type="number" name="id" required><br>
	Make:<input type="text" name="make" pattern="[a-zA-Z]+" required><br>
	Model:<input type="text" name="model" pattern="[a-zA-Z0-9]+" required><br>
	Year:<input type="number" name="year" min="1" max="9999" required><br>
	Price:<input type="number" name="price" required><br>
	License Number:<input type="text" name="license number" pattern="(^[A-Z]{2}[0-9]{2}[A-Z]{3}$)" placeholder="ex. AB12ABC" required><br>
	Colour:<input type="text" name="colour" required><br>
	Number of Doors:<input type="text" name="doors" pattern="[0-9]+" required><br>
	Transmission type (manual/automatic):<input type="text" name="transmission" required><br>
	Mileage:<input type="number" name="mileage" required><br>
	Fuel type (petrol.diesel,hybrid,electric):<input type="text" name="fuel type" required><br>
	Engine size (cc):<input type="number" name="engine size" required><br>
	Body style (hatchback, estate, SUV, MVP coupe):<input type="text" name="body style" required><br>
	Condition (e.g. like new, good, fair):<input type="text" name="condition" required><br>
	Notes (special features such as sat nav):<input type="text" name="notes" required><br>
	<input type="submit" value="Create">
	<a href="./home">Cancel</a>
	</form>
	</c:when>
	<c:when test="${sessionScope.user == null}">
	<h1>Please <a href = "./login">Log in</a> first.</h1>
	</c:when>
	<c:otherwise>	
	<h1>Only admin able to manipulate table.</h1>
	</c:otherwise>
</c:choose>
</body>
</html>