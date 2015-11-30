package com.hams.data;

import javax.persistence.Entity;
@Entity

public class User {
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	
	private java.sql.Timestamp time_stamp ;
	public java.sql.Timestamp getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(java.sql.Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}
	

}
