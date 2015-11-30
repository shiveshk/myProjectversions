<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="validation.js"></script>

<link rel="stylesheet" type="text/css" href="hams.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>Insert title here</title>
</head>
<body>
<% String username = (String)request.getSession().getAttribute("name"); 
out.println("Welcome "+username);
%>

<% StringBuilder message = (StringBuilder)request.getSession().getAttribute("message"); 
out.println(message);
%>

<form action="SaveOrEditAppointmentServlet" method="post">
to save message <input type="submit" name="act" value="ok" /><b></b>
to edit message <input type="submit" name="act" value="edit" />
</form>

</body>
</html>