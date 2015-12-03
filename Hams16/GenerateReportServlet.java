package com.hams.appointment;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import com.hams.data.Appointment;
import com.hams.data.TestingAppointment;
import com.hams.data.TestingUser;
import com.hams.data.User;


/**
 * Servlet implementation class GenerateReportServlet
 */
@WebServlet("/GenerateReportServlet")
public class GenerateReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	static final Logger LOGGER = Logger.getLogger(GenerateReportServlet.class);
	
	//make sessionfactory object static so we will need this only once
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	//build session factory
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("entered into dopost of GenerateReportServlet");
		
		//pick initial date & final date where we have to generate report
		
		String initial_date = request.getParameter("initial_date"); 
		String final_date = request.getParameter("final_date"); 
		
		
		Date date1 = null ;
		Date date2 = null;
		
		
		
		
		HttpSession session = request.getSession(true);
		
		//get who is logged in because we have to generate report only for admin
		String login_user = session.getAttribute("name").toString();
		
		//define format of date 
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
		
		
		   try {
		     
		     
		     date1 = originalFormat.parse(initial_date);
		     date2 = originalFormat.parse(final_date);
		     
		   	   } catch (ParseException ex) {
		    	ex.printStackTrace();
		    	LOGGER.error(ex);

		       }
		   
		
		//check login if value of authenticateAdmin(login_user) will be true then login should be successful otherwise don't give permission
		   
        boolean admin_check = authenticateAdmin(login_user);
		
        if( admin_check == true ){  
        	
            LOGGER.info("entered into if of GenerateReportServlet only if user is admin");
            
            //call generateReport function only for valid user with admin access
            
            generateReport( targetFormat.format(date1) , targetFormat.format(date2),request ,response);
            
            }  
            
        else{  
            	/* login unsuccessful because of failed validation */
            	
            	LOGGER.info("entered into else of GenerateReportServlet if admin not entered valid credentials");

            	/* redirect user again to appointment page */
            	
            	session.setAttribute("admin_check_response", "sorry , permission denied you have not admin access");	
            	
                String nextJSP = "/appointment.jsp";
    			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    			
    			try {
    				dispatcher.forward(request,response);
    			} catch (ServletException e) {
    				e.printStackTrace();
    				LOGGER.error(e);
    			} catch(IOException e ){
    				e.printStackTrace();
    				LOGGER.error(e);
    			}
            	
            }  
		
		}
	

		@SuppressWarnings("unchecked")
		public void generateReport(String date1 , String date2, HttpServletRequest request, ServletResponse response) {
			
			LOGGER.info("entered into generateReport method of  GenerateReportServlet to generate report from database");
			
			// creating session factory object  
			
			SessionFactory factory = getSessionFactory();
			
			Session session1 = factory.openSession();
			
	 
		    //creating session object  
			
		    try {
				session1 = factory.openSession();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.error(e);
			}  
			
		      
		    	
			/*SQL_QUERY to decide if user is admin or not   */
			
	        


		    String SQL_QUERY =  " from Appointment t where t.time_stamp BETWEEN '"+ date1 +"' AND '"+ date2 +"'";
		    
	        Query query = null ;
			try {
				query = session1.createQuery(SQL_QUERY);
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.error(e);
			}
			
			List<Appointment> models = null;
			try {
				models = query.list();
			} catch (HibernateException e1) {
				
				e1.printStackTrace();
			}
			
			int length = models.size();
			
			
	        //if size of returned data is more than 0 then assign data to variable so that it can be retrieved in jsp
			
	        if (models.size() > 0) {
	            
	            request.setAttribute("data", models); 
	            request.setAttribute("size", length); 
	            
	        		       
	        }


	        
	        try {
				session1.close();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.error(e);
			}
	        
	        /* redirect user to show records of appointment between specified date */
        	
            String nextJSP = "/generateReport.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			
			try {
				dispatcher.forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
				LOGGER.error(e);
			} catch(IOException e ){
				e.printStackTrace();
				LOGGER.error(e);
			}
	        
			LOGGER.info("exiting from generateReport method of  GenerateReportServlet ");
			
			
		}
		
		
	
	@SuppressWarnings("unchecked")
	public boolean authenticateAdmin(String login_user) {
		
		
		
			LOGGER.info("entered into authenticateAdmin method of  GenerateReportServlet to decide admin credential in database");

		

	    // creating session factory object  
		
		SessionFactory factory = getSessionFactory();
		
		Session session1 = factory.openSession();
		
 
	    //creating session object  
		
	    try {
			session1 = factory.openSession();
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}  
		
	      
	    	
		/* SQL_QUERY to fetch data for specified date  */
		
        String SQL_QUERY = "from User u where u.user_name='" + login_user + "' and u.admin ='" + true + "'";
        
        Query query = null ;
        
		try {
			query = session1.createQuery(SQL_QUERY);
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		List<User> list = null;
		try {
			list = query.list();
		} catch (HibernateException e1) {
						e1.printStackTrace();
		}
        if (list.size() > 0) {
            
            return true;
        }
        
        try {
			session1.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
        

		LOGGER.info("exiting from authenticateUser method of  GenerateReportServlet ");
		
		//return false if user is not authenticated to generate report
        return false;
        
        
    	}
}

