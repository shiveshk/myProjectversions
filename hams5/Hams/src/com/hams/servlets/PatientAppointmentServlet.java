package com.hams.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hams.data.Appointment;


@WebServlet("/PatientAppointmentServlet")
public class PatientAppointmentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(PatientAppointmentServlet.class);
			
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException ,NullPointerException {
			
		
		//get data from index.jsp and save in variables
		String patient_mobile_number = null;
		String patient_name = null;
		String clinic_detail = null;
		String time = null;
		
		patient_mobile_number = request.getParameter("patient_mobile_number");
		patient_name = request.getParameter("patient_name");
		clinic_detail = request.getParameter("clinic_detail");
		time = request.getParameter("time");
		
		
		
		//use string builder to build message
		StringBuilder message = null;
		try {
			message = new StringBuilder(" Dear " + patient_name + " Confirmed Appt :  " + clinic_detail + 
					".Approx Appointment time  " + time +  " . To cancel your appointment SMS CANCEL to 9108053229 .Call 18002700673 ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e);
		}
		
		//if message needed to be updated then store it in session to get further it in jsp to edit
		HttpSession session = request.getSession();
		session.setAttribute("patient_mobile_number", patient_mobile_number);
		session.setAttribute("patient_name", patient_name);
        session.setAttribute("clinic_detail", clinic_detail);
        session.setAttribute("time", time);
        session.setAttribute("message", message);
        
        
        
        String nextJSP = "/message.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
        
		
	}


}