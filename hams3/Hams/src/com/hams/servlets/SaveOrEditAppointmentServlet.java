package com.hams.servlets;

 
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;



@WebServlet("/SaveOrEditAppointmentServlet")
public class SaveOrEditAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(SaveOrEditAppointmentServlet.class);
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOrEditAppointmentServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  
		String act = request.getParameter("act");
		if (act.equals("cancel")) {
		   
			//cancel button was pressed so we don't need to save data there may be need to edit of data
			
			String nextJSP = "/edit.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		} 
		
	     
        
	}

}
