<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="models.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/site.css">
</head>
<body>
	<b>${message}</b><br>
	<c:if test="${sessionScope.user == 'admin'}">
	
	<h1>Hi ${user}, welcome to vehicle database!</h1><br>
	<form method="POST" action="./logout">
	<input type="submit" value="Logout">
	</form>
	<br>
	<b><a href = "./addVehicle">Create</a></b><br>
	<table>
		<tr> <th>Vehicle ID</th> <th>Make</th> <th>Model</th> <th>Year</th> <th>Price</th> 
		<th>License Number</th> <th>Colour</th> <th>Number of Doors</th> <th>Transmission</th> 
		<th>Mileage</th> <th>Fuel Type</th> <th>Engine Size</th> <th>Body Style</th> <th>Condition</th> <th>Notes</th> </tr>	
	<c:forEach items="${AllVehicles}" var="v">
	<tr><td>${v.getVehicle_id()}</td> <td>${v.getMake()}</td> <td>${v.getModel()}</td> <td>${v.getYear()}</td> <td>${v.getPrice()}</td> 
		<td>${v.getLicense_number()}</td> <td>${v.getColour()}</td> <td>${v.getNumber_doors()}</td> <td>${v.getTransmission()}</td> 
		<td>${v.getMileage()}</td> <td>${v.getFuel_type()}</td> <td>${v.getEngine_size()}</td> <td>${v.getBody_style()}</td> 
		<td>${v.getCondition()}</td> <td>${v.getNotes()}</td></tr>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${sessionScope.user == null}">
	<h1>Please <a href = "./login">Log in</a> to view tables.</h1>
	</c:if>
<br>
</body>
</html>