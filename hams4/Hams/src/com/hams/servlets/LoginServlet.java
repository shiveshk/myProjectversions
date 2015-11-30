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

        String name = request.getParameter("name");  
        String password1 = request.getParameter("password");  
        byte[] encodedBytes = Base64.encodeBase64(password1.getBytes());
        String password = new String(encodedBytes);
        System.out.println(password);
        
        PrintWriter out = null ;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e);
		} 

        //check login if value of login_check will be true then login should be successful otherwise don't give permission
        boolean login_check = loginCheck(name ,password);
        
        if( login_check == true ){  
       
        HttpSession session = request.getSession();  
        session.setAttribute("name" , name); 
        
        System.out.println("login successful" + name);
        LOGGER.info("login successful");
		String nextJSP = "/appointment.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		
		
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			
			e.printStackTrace();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
        }  
        
        else{  
        	/* login unsuccessful because of failed validation */
            
        	out.print("Sorry, username or password error!");  
            
        	/* redirect user again to login page */
        	
            String nextJSP = "/index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			
			try {
				dispatcher.forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch(IOException e ){
				e.printStackTrace();
			}
        	
        }  
        /* Closes the stream and releases any system resources associated with it. Closing a previously closed stream has no effect.
 */
        out.close();  
	}
	 
	/* function to validate credential of user from database
 */
	 
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
