<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> >
<meta charset="ISO-8859-1">
<title>Add New Vehicle</title>
<link rel="stylesheet" type="text/css" href="css/site.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body class="text-center p-3">
<!--Show notification -->
${notification}<br>
<!-- instantly remove the value of notification after showing -->
<c:remove var="notification" scope="session"/>
<c:choose>
	<c:when test="${sessionScope.user == 'admin'}">
	<h1>Please insert new vehicle's details.</h1>
	<form method="POST" action="./addVehicle">
	<div class="p-2">
	ID: <input class="ml-3" type="number" name="id" required>
	</div>
	<div class="p-2">
	Make: <input class="ml-3" type="text" name="make" pattern="[a-zA-Z]+" required>
	</div>
	<div class="p-2">
	Model: <input class="ml-3" type="text" name="model" pattern="[a-zA-Z0-9]+" required>
	</div>
	<div class="p-2">
	Year: <input class="ml-3" type="number" name="year" min="1" max="9999" required>
	</div>
	<div class="p-2">
	Price: <input class="ml-3" type="number" name="price" required>
	</div>
	<div class="p-2">
	License Number: <input class="ml-3" type="text" name="license number" pattern="(^[A-Z]{2}[0-9]{2}[A-Z]{3}$)" placeholder="ex. AB12ABC" required>
	</div>
	<div class="p-2">
	Colour: <input class="ml-3" type="text" name="colour" required>
	</div>
	<div class="p-2">
	Number of Doors: <input class="ml-3" type="text" name="doors" pattern="[0-9]+" required>
	</div>
	<div class="p-2">
	Transmission type (manual/automatic): <input class="ml-3" type="text" name="transmission" required>
	</div>
	<div class="p-2">
	Mileage: <input class="ml-3" type="number" name="mileage" required>
	</div>
	<div class="p-2">
	Fuel type (petrol.diesel,hybrid,electric): <input class="ml-3" type="text" name="fuel type" required>
	</div>
	<div class="p-2">
	Engine size (cc): <input class="ml-3" type="number" name="engine size" required>
	</div>
	<div class="p-2">
	Body style (hatchback, estate, SUV, MVP coupe): <input class="ml-3" type="text" name="body style" required>
	</div>
	<div class="p-2">
	Condition (e.g. like new, good, fair): <input class="ml-3" type="text" name="condition" required>
	</div>
	<div class="p-2">
	Notes (special features such as sat nav): <input class="ml-3" type="text" name="notes" required>
	</div>
	<input class="btn btn-success" type="submit" value="Create">
	<a class="btn btn-secondary ml-2" href="./home">Cancel</a>
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