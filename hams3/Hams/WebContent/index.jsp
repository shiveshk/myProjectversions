<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="hams.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="style.css"/>
<title>Login</title>
<style>

img {
	
	margin: auto;
	bottom: 100px; 
    height: 150px; 
    width: 150px;
}

</style>

</head>



<body>
<img src = "images/image1.png" alt="doctor2" id="img3"/> 

 <%
        if(session.isNew())
            {
            session = request.getSession();
            String s = request.getParameter("login");
            session.setAttribute("first", s);
            }
        %>


    <div id="login-overlay" class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel"><b>Login to Hams Patient Appointment </b></h4> 
          </div>
          <div class="modal-body">
              <div class="row">
                  <div class="col-xs-6">
                      <div class="well">
                          <form id="loginForm" method="POST" action="LoginServlet">
                              <div class="form-group">
                                  <label for="username" class="control-label">Username</label>
                                  <input type="text" class="form-control" name="name" value=""  title="Please enter your username" placeholder="username">
                                  <span class="help-block"></span>
                              </div>
                              
                              <div class="form-group">
                                  <label for="password" class="control-label">Password</label>
                                  <input type="password" class="form-control" name="password" placeholder="password" value="" title="Please enter your password">
                                  <span class="help-block"></span>
                              </div>
                              
                              <button type="submit" value="login" name="submit" class="btn btn-success btn-block">Login</button>
                          </form>
                      </div>
                  </div>
                  
                  <div class="col-xs-6">
                      <p class="lead">Login now for <span class="text-success">Patient Appointment</span></p>
                      
                      <img src = "images/HAMSLogo.jpg" alt="hamslogo" /> 
                      
                  </div>
                  </div>
                          
      </div>
</div>
  </div>





</body>
</html>

