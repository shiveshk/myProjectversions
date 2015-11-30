package com.hams.servlets;

import org.hibernate.HibernateException;
import java.util.Calendar;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.hams.data.Appointment;


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

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message=request.getParameter("patient_data");  
		String act = request.getParameter("act");
		if (act == null) {
		    //no button has been selected
		} else if (act.equals("ok")) {
		    //ok button was pressed
			
			//get data from session and save in variables
			String patient_name = null;
			String clinic_detail = null;
			String time = null;
			
			patient_name = request.getSession(false).getAttribute("patient_name").toString();
			clinic_detail = request.getSession(false).getAttribute("clinic_detail").toString();
			time = request.getSession(false).getAttribute("time").toString();

			
			
			//creating configuration object  
			Configuration cfg = null;
			try {
				cfg = new Configuration();  
				//populates the data of the configuration file 
				cfg.configure("hibernate.cfg.xml"); 
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
				
			}
		    // creating seession factory object  
		    SessionFactory factory = null;
			try {
				factory = cfg.buildSessionFactory();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
			}  
		      
		    //creating session object  
		    Session session1 = null;
			try {
				session1 = factory.openSession();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
			}  
		      
		    //creating transaction object  
			Transaction t = null;
			try {
				t = session1.beginTransaction();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
				
			}  
			// 1) create a java calendar instance
			Calendar calendar = Calendar.getInstance();
			 
			// 2) get a java.util.Date from the calendar instance.
			//this date will represent the current instant, or "now".
			java.util.Date now = calendar.getTime();
			 
			// 3) a java current time (now) instance
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
					
		    Appointment appointment = new Appointment();  
		    appointment.setClinic_detail(clinic_detail);
		    appointment.setPatient_name(patient_name);
		    appointment.setTime(time);
		    appointment.setTime_stamp(currentTimestamp);
		    
		    
		    //persisting the object
		    try {
				session1.save(appointment);  
			} catch (HibernateException e) {
				
				e.printStackTrace();
				LOGGER.info(e);
			}
		    
		    //transaction is committed 
		    t.commit(); 
		    try {
				session1.close();
			} catch (HibernateException e) {
				
				e.printStackTrace();
				LOGGER.info(e);
			}  
		      
		    System.out.println("successfully saved");  
		      
			LOGGER.info(message);
			String nextJSP = "/appointment.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		
		} else if (act.equals("cancel")){
			//cancel button was pressed
			String nextJSP = "/edit.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		
        
	}

}
