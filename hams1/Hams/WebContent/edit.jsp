<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page language="java" session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<style type="">
sup {
    vertical-align: super;
    font-size: smaller;
    color : red;
}
tr.row2 td {
    padding-top: 20px;
}

tr.row3 td {
    padding-top: 20px;
}
</style>
</head>
<body bgcolor='#E6E6FA'>
<center>

<h1>HAMS</h1>

<form name="edit_detail" action="PatientAppointmentServlet" method="post">
	<p><b><font size=5>Clinic Appointment</font></b></p>
	<p><font size=1><sup>*</sup> Required Fields</font></p>
<table>
<tr class="row1">
	<td><b>Patient Name: <sup>*</sup></b></td>
	<td><input type="text" id="patient_name" name="patient_name" value="${sessionScope.patient_name}" size=50 maxlength=500 /> </td>
</tr>

<tr class="row2">
	<td><b>Clinic Detail: <sup>*</sup></b></td>
	<td><input type="text" id="clinic_detail" name="clinic_detail" value="${sessionScope.clinic_detail}" size=50 maxlength=500 /> </td>
</tr>

<tr class="row3">
	<td><b>Approx Appointment Time: <sup>*</sup></b></td>
	<td><input type="text" id="time" name="time" value="${sessionScope.time}" size=50 maxlength=500 /> </td>
</tr>


</table>

<input type="submit" name="submit" value="submit"/>

</form>

</center>

</body>

</html>
