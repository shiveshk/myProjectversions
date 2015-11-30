package com.hams.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;


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
        
		/*
		 * message output on console response.getWriter().println(html);*/
		try {
			String html = "<html><h2>"+message+"</h2></html>";
            response.getWriter().println(html);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e);
        }
		
		//form to send message to patient side
		PrintWriter out1 = null;
		try {
			out1 = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e);
		}
		out1.print("<form action=\"");
        out1.print(response.encodeURL("http://bhashsms.com/api/sendmsg.php?user=hamsind&pass=12345&sender=HAMSIN&phone="+patient_mobile_number+"&text="+message+"&priority=ndnd&stype=normal"));
        out1.print("\" ");
        out1.print("method=POST>");
        
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
	    // creating session factory object  
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
	    appointment.setPatient_mobile_number(patient_mobile_number);
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
        
        out1.print("to send message");
        out1.print("<input type=submit name=\"act\" value=\"send message\" style=\"margin-right: 30px\"> ");
        out1.print("</form>");
		//form closure
		
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
        
        
        out.print("to edit message");
        out.print("<input type=submit name=\"act\" value=\"cancel\">");
        out.print("</form>");	
        
        

	}


}