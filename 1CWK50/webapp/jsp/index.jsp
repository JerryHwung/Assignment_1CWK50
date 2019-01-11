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
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- Connect to jQuery libs through Internet -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>
<body class="text-center pt-5">
<script>
// function to show all details that relate to search
function search() {
	  var x = document.getElementById("vehicle-search").value;
	  var keyword = x.toLowerCase();
	  $(".details").filter(function() {
		  $(this).toggle($(this).text().toLowerCase().indexOf(keyword) > -1)
	  });
}
</script>
	<c:choose>
	<c:when test="${sessionScope.user == 'admin'}">
	<h1>Hi ${user}, welcome to vehicle database!</h1>
	<!--Show notification -->
	${notification}<br>
	<!-- instantly remove the value of notification after showing -->
	<c:remove var="notification" scope="session"/>
	<form method="POST" action="./logout">
	<input type="submit" class="btn btn-secondary mb-3" value="Logout">
	</form>
	
	<label for="vehicle-search">Search the table:</label>
	<input type="search" id="vehicle-search" placeholder="Search...">
	<button onclick="search()" class="btn btn-info mb-2">Search</button>
	<table class="table table-hover w-auto">
	<thead class="thead-dark">
		<tr class="sm"> <th>ID</th> <th>Make</th> <th>Model</th> <th>Year</th> <th>Price</th> 
		<th>License Number</th> <th>Colour</th> <th>Number of Doors</th> <th>Transmission</th> 
		<th>Mileage</th> <th>Fuel Type</th> <th>Engine Size</th> <th>Body Style</th> <th>Condition</th> <th>Notes</th> </tr>	
	</thead>
	<c:forEach items="${AllVehicles}" var="v">
	<tbody>
	<tr class="details"><td>${v.getVehicle_id()}</td> <td>${v.getMake()}</td> <td>${v.getModel()}</td> <td>${v.getYear()}</td> <td>${v.getPrice()}</td> 
		<td>${v.getLicense_number()}</td> <td>${v.getColour()}</td> <td>${v.getNumber_doors()}</td> <td>${v.getTransmission()}</td> 
		<td>${v.getMileage()}</td> <td>${v.getFuel_type()}</td> <td>${v.getEngine_size()}</td> <td>${v.getBody_style()}</td> 
		<td>${v.getCondition()}</td> <td>${v.getNotes()}</td> 
		<td class="border-0"><a class="btn btn-primary btn-sm p-2" href="./update?id=${v.getVehicle_id()}">Edit</a>
		<form method="POST" action="./delete?id=${v.getVehicle_id()}">
		<input class="btn btn-danger btn-sm p-2" type="submit" value="Delete"></form> </td>
	</tr>
	</tbody>
	</c:forEach>
	</table>
	<b><a href = "./addVehicle" class="btn btn-success mb-3">Add New Vehicle</a></b><br>
	</c:when>
	<c:when test="${sessionScope.user == null}">
	<h1>Please <a href = "./login">Log in</a> to view tables.</h1>
	</c:when>
	<c:otherwise>	
	<h1>Hi ${user}, welcome to vehicle database!</h1><br>
	<!--Show notification -->
	${notification}<br>
	<!-- instantly remove the value of notification after showing -->
	<c:remove var="notification" scope="session"/>
	<form method="POST" action="./logout">
	<input type="submit" value="Logout">
	</form>
	<label for="vehicle-search">Search the table:</label>
	<input type="search" id="vehicle-search" placeholder="Search...">
	<button onclick="search()">Search</button>
	<table>
		<tr> <th>Vehicle ID</th> <th>Make</th> <th>Model</th> <th>Year</th> <th>Price</th> 
		<th>License Number</th> <th>Colour</th> <th>Number of Doors</th> <th>Transmission</th> 
		<th>Mileage</th> <th>Fuel Type</th> <th>Engine Size</th> <th>Body Style</th> <th>Condition</th> <th>Notes</th> </tr>	
	<c:forEach items="${AllVehicles}" var="v">
	<tr class="details"><td>${v.getVehicle_id()}</td> <td>${v.getMake()}</td> <td>${v.getModel()}</td> <td>${v.getYear()}</td> <td>${v.getPrice()}</td> 
		<td>${v.getLicense_number()}</td> <td>${v.getColour()}</td> <td>${v.getNumber_doors()}</td> <td>${v.getTransmission()}</td> 
		<td>${v.getMileage()}</td> <td>${v.getFuel_type()}</td> <td>${v.getEngine_size()}</td> <td>${v.getBody_style()}</td> 
		<td>${v.getCondition()}</td> <td>${v.getNotes()}</td> 
		</td>
	</tr>
	</c:forEach>
	</table>
	</c:otherwise>
	</c:choose>
<br>
</body>
</html>