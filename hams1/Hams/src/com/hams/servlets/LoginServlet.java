package com.hams.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;


import org.apache.commons.codec.binary.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(SaveOrEditAppointmentServlet.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("name");  
        String password1 = request.getParameter("password");  
        //String bytesEncoded = Base64.encodeBytes(password1.getBytes);
        byte[] encodedBytes = Base64.encodeBase64(password1.getBytes());
        //String password = encodedBytes.toString();
        String password = new String(encodedBytes);
        System.out.println(password);
        
        PrintWriter out = response.getWriter(); 

        //check login
        boolean login_check = loginCheck(name ,password);
        
        if(login_check==true){  
        //out.print("Welcome, "+name);  
        HttpSession session=request.getSession();  
        session.setAttribute("name",name); 
        
        System.out.println("login successful"+name);
        LOGGER.info("login successful");
		String nextJSP = "/appointment.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
        }  
        else{  
        	//System.out.println("incorrect username or password");
            out.print("Sorry, username or password error!");  
            
            String nextJSP = "/index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
        }  
        out.close();  
	}
	 
	 
	 public boolean loginCheck(String username, String password){
		    String query;
		    boolean login = false;

		    try {
		        Class.forName("com.mysql.jdbc.Driver").newInstance();
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hams", "root", "1234");
		        Statement stmt = (Statement) con.createStatement();
		        query = "SELECT user_name, TO_BASE64(password) FROM user WHERE user_name='" + username + "' AND password='" + password + "';";
		        stmt.executeQuery(query);
		        ResultSet rs = stmt.getResultSet();
		        login = rs.first(); 
		    } catch (InstantiationException e) {
		        e.printStackTrace();
		    } catch (IllegalAccessException e) {
		        e.printStackTrace();
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return login;
		}
}
