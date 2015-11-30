<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="SaveOrEditAppointmentServlet" method="post">

Appointment message: <input type="text" id="patient_name" name="patient_name" value="${sessionScope.message}" size=50 maxlength=500 />
<% String username = (String)request.getSession().getAttribute("name"); 
out.println("Welcome "+username);
String message = (String)request.getSession().getAttribute("message");
out.println(message);	
%>
to save message <input type="submit" name="act" value="ok" />
to edit message <input type="submit" name="act" value="edit" />
</form>

</body>
</html>