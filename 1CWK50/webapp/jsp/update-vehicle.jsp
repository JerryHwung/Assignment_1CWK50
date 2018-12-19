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
</head>
<body>
<b>${message}</b><br>
<c:if test="${sessionScope.user == 'admin'}">
<h1>Please update the vehicle's details.</h1>
<form method="POST" action="./update">

ID:<input type="number" name="id" value="${v.getVehicle_id()}" required><br>
Make:<input type="text" name="make" value="${v.getMake()}" required><br>
Model:<input type="text" name="model" value="${v.getModel()}" required><br>
Year:<input type="number" name="year" value="${v.getYear()}" required><br>
Price:<input type="number" name="price" value="${v.getPrice()}" required><br>
License Number:<input type="text" name="license number" value="${v.getLicense_number()}" required><br>
Colour:<input type="text" name="colour" value="${v.getColour()}" required><br>
Number of Doors:<input type="text" name="doors" value="${v.getNumber_doors()}" required><br>
Transmission type (manual/automatic):<input type="text" name="transmission" value="${v.getTransmission()}" required><br>
Mileage:<input type="number" name="mileage" value="${v.getMileage()}" required><br>
Fuel type (petrol.diesel,hybrid,electric):<input type="text" name="fuel type" value="${v.getFuel_type()}" required><br>
Engine size (cc):<input type="number" name="engine size" value="${v.getEngine_size()}" required><br>
Body style (hatchback, estate, SUV, MVP coupe):<input type="text" name="body style" value="${v.getBody_style()}" required><br>
Condition (e.g. like new, good, fair):<input type="text" name="condition" value="${v.getCondition()}" required><br>
Notes (special features such as sat nav):<input type="text" name="notes" value="${v.getNotes()}" required><br>

<input type="submit" value="Update">
<a href="./home">Cancel</a>
</form>
</c:if>
</body>
</html>