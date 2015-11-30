package com.hams.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


@WebServlet("/PatientAppointmentServlet")
public class PatientAppointmentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(PatientAppointmentServlet.class);
	//private static final Throwable Throwable = null; 
			
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException ,NullPointerException {
			
		
		//get data from index.jsp and save in variables
		String patient_name = null;
		String clinic_detail = null;
		String time = null;
		
		patient_name = request.getParameter("patient_name");
		clinic_detail = request.getParameter("clinic_detail");
		time = request.getParameter("time");
		
		
		
		//use string builder to build message
		StringBuilder message = null;
		try {
			message = new StringBuilder(" Dear " + patient_name + " Confirmed Appt :  " + clinic_detail + ". Appointment time  " + time +  " To cancel your appointment SMS CANCEL to 9108053229, .Call 18002700669 ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e);
		}
		
		//if message needed to be updated then store it in session to get further it in jsp to edit
		HttpSession session = request.getSession();
        session.setAttribute("patient_name", patient_name);
        session.setAttribute("clinic_detail", clinic_detail);
        session.setAttribute("time", time);
        
		/*
		 * message output on console response.getWriter().println(html);*/
		try {
			String html = "<html><h2>"+message+"</h2></html>";
            response.getWriter().println(html);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e);
        }
		
		
		//form to save message if message is to be sent or edit message if message to be edited
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e);
		}
		out.println("<body bgcolor='#E6E6FA'>");
		out.print("<form action=\"");
        out.print(response.encodeURL("SaveOrEditAppointmentServlet"));
        out.print("\" ");
        out.print("method=POST>");
        out.print("<input type='hidden' name='patient_data' value='"+message+"'>"); 
        out.print("to save message");
        out.print("<input type=submit name=\"act\" value=\"ok\" style=\"margin-right: 30px\"> ");
        out.print("to edit message");
        out.print("<input type=submit name=\"act\" value=\"cancel\">");
        out.print("</form>");	
        
        

	}


}