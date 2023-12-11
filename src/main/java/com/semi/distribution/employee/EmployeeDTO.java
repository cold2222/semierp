package com.semi.distribution.employee;

import java.sql.Date;

public class EmployeeDTO {
	
	private String e_id;
	private String e_name;
	private String e_position;
	private String e_phone_number;
	private String e_email;
	private Date e_hire_date;
	
	public EmployeeDTO() {
		super();
	}
	public EmployeeDTO(String e_id, String e_name, String e_position, String e_phone_number, String e_email,
			Date e_hire_date) {
		super();
		this.e_id = e_id;
		this.e_name = e_name;
		this.e_position = e_position;
		this.e_phone_number = e_phone_number;
		this.e_email = e_email;
		this.e_hire_date = e_hire_date;
	}
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_position() {
		return e_position;
	}
	public void setE_position(String e_position) {
		this.e_position = e_position;
	}
	public String getE_phone_number() {
		return e_phone_number;
	}
	public void setE_phone_number(String e_phone_number) {
		this.e_phone_number = e_phone_number;
	}
	public String getE_email() {
		return e_email;
	}
	public void setE_email(String e_email) {
		this.e_email = e_email;
	}
	public Date getE_hire_date() {
		return e_hire_date;
	}
	public void setE_hire_date(Date e_hire_date) {
		this.e_hire_date = e_hire_date;
	}
	
	
	
	
}
