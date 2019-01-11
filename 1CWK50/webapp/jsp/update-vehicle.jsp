<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="models.Vehicle"%>
<!DOCTYPE html>
<html>
<head>
<meta <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> >
<meta charset="ISO-8859-1">
<title>Update Vehicle</title>
<link rel="stylesheet" type="text/css" href="css/site.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body class="text-center ml-5">
<!--Show notification -->
${notification}<br>
<!-- instantly remove the value of notification after showing -->
<c:remove var="notification" scope="session"/>
<c:choose>
	<c:when test="${sessionScope.user == 'admin'}">
	<h2>Please update the vehicle's details.</h2>
	<form method="POST" action="./update">
	<div class="p-2">
	ID:<input class="ml-3" type="number" name="id" value="${v.getVehicle_id()}" required>
	</div>
	<div class="p-2">
	Make:<input class="ml-3" type="text" name="make" pattern="[a-zA-Z]+" value="${v.getMake()}" required>
	</div>
	<div class="p-2">
	Model:<input class="ml-3" type="text" name="model" pattern="[a-zA-Z0-9]+" value="${v.getModel()}" required>
	</div>
	<div class="p-2">
	Year:<input class="ml-3" type="number" name="year" min="1" max="9999" value="${v.getYear()}" required>
	</div>
	<div class="p-2">
	Price:<input class="ml-3" type="number" name="price" value="${v.getPrice()}" required>
	</div>
	<div class="p-2">
	License Number:<input class="ml-3" type="text" name="license number" pattern="(^[A-Z]{2}[0-9]{2}[A-Z]{3}$)" value="${v.getLicense_number()}" required>
	</div>
	<div class="p-2">
	Colour:<input class="ml-3" type="text" name="colour" value="${v.getColour()}" required>
	</div>
	<div class="p-2">
	Number of Doors:<input class="ml-3" type="text" name="doors"pattern="[0-9]+" value="${v.getNumber_doors()}" required>
	</div>
	<div class="p-2">
	Transmission type (manual/automatic):<input class="ml-3" type="text" name="transmission" value="${v.getTransmission()}" required>
	</div>
	<div class="p-2">
	Mileage:<input class="ml-3" type="number" name="mileage" value="${v.getMileage()}" required>
	</div>
	<div class="p-2">
	Fuel type (petrol.diesel,hybrid,electric):<input class="ml-3" type="text" name="fuel type" value="${v.getFuel_type()}" required>
	</div>
	<div class="p-2">
	Engine size (cc):<input class="ml-3" type="number" name="engine size" value="${v.getEngine_size()}" required>
	</div>
	<div class="p-2">
	Body style (hatchback, estate, SUV, MVP coupe):<input class="ml-3" type="text" name="body style" value="${v.getBody_style()}" required>
	</div>
	<div class="p-2">
	Condition (e.g. like new, good, fair):<input class="ml-3" type="text" name="condition" value="${v.getCondition()}" required>
	</div>
	<div class="p-2">
	Notes (special features such as sat nav):<input class="ml-3" type="text" name="notes" value="${v.getNotes()}" required>
	</div>
	<div class="p-2">
	<input class="btn btn-success" type="submit" value="Update">
	<a class="btn btn-secondary" href="./home">Cancel</a>
	</div>
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