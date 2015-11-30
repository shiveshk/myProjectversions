<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="validation.js"></script>

<link rel="stylesheet" type="text/css" href="hams.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit Appointment Data</title>
 
</head>
<img src = "images/HAMSLogo.jpg" alt="hamslogo" id="img1" />
<img src = "images/image1.png" alt="doctor2" id="img3"/>  

<body bgcolor='#E6E6FA'>

<div class="container" >
<% String username = (String)request.getSession().getAttribute("name"); 
out.println("Welcome "+username);
%>

<a href="LogoutServlet">Logout</a>

<center>
<div id="login-overlay" class="modal-dialog" >
     <div class="modal-content" >
          <div class="modal-header" >

<h1>HAMS</h1>
	<p><b><font size=5>Clinic Appointment</font></b></p>
	<p><font size=1><sup>*</sup> Required Fields</font></p>

<form name="myForm" action="PatientAppointmentServlet" method="post" onsubmit="return validate()">
	<div class="form-group">
<table >

	<tr class="row1">
	 	 <td><b>Patient mobile Number: <sup>*</sup></b> </td>
	 	 <td><input type="text" id="patient_mobile_number" name="patient_mobile_number" value="${sessionScope.patient_mobile_number}" size=50 maxlength=20 /></td>
	</tr>

	<tr class="row2">
		<td><b>Patient Name: <sup>*</sup></b></td>
		<td><input type="text" id="patient_name" name="patient_name" value="${sessionScope.patient_name}" size=50 maxlength=5000 /> </td>
	</tr>

	<tr class="row3"> 
	 	<td><b>Clinic Detail: <sup>*</sup></b> </td>
	 	<td><input type="text" id="clinic_detail" name="clinic_detail" value="${sessionScope.clinic_detail}" size=50 maxlength=5000  /></td>
	 </tr>  

	<tr class="row4">
		<td><b>Approx Appointment Time: <sup>*</sup></b></td>
		<td><input type="text" id="time" name="time" value="${sessionScope.time}" size=50 maxlength=5000 /> </td>
	</tr>

</table>

<input type="submit" name="submit" value="submit"/>
</div>
</form>
		</div>
	</div>
</div>
</center>
</div>

<img src = "images/hams1.jpg" alt="doctor" id="img2"/>


</body>

</html>
