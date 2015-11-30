package com.hams.data;
//import java.util.Date;
//import java.util.Calendar;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
//import javax.persistence.Id;
@Entity
public class Appointment implements java.io.Serializable {

	//@Id
	private long appointment_id ;
	public long getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(long appointment_id) {
		this.appointment_id = appointment_id;
	}

	private String patient_name ;
	private String clinic_detail ;
	private String time ;
	private java.sql.Timestamp time_stamp ;
	
	
	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getClinic_detail() {
		return clinic_detail;
	}

	public void setClinic_detail(String clinic_detail) {
		this.clinic_detail = clinic_detail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public java.sql.Timestamp getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(java.sql.Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}

	


	
	


}
