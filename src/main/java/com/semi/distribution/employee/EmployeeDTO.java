package com.semi.distribution.employee;

import java.sql.Date;

public class EmployeeDTO {
	
	private String e_no;
	private String e_pw;
	private String e_deptno;
	private String e_name;
	private String e_rank;
	private String e_tel;
	private String e_email;
	private Date e_joined_company;
	
	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(String e_no, String e_pw, String e_deptno, String e_name, String e_rank, String e_tel,
			String e_email, Date e_joined_company) {
		super();
		this.e_no = e_no;
		this.e_pw = e_pw;
		this.e_deptno = e_deptno;
		this.e_name = e_name;
		this.e_rank = e_rank;
		this.e_tel = e_tel;
		this.e_email = e_email;
		this.e_joined_company = e_joined_company;
	}

	public String getE_no() {
		return e_no;
	}

	public void setE_no(String e_no) {
		this.e_no = e_no;
	}

	public String getE_pw() {
		return e_pw;
	}

	public void setE_pw(String e_pw) {
		this.e_pw = e_pw;
	}

	public String getE_deptno() {
		return e_deptno;
	}

	public void setE_deptno(String e_deptno) {
		this.e_deptno = e_deptno;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	public String getE_rank() {
		return e_rank;
	}

	public void setE_rank(String e_rank) {
		this.e_rank = e_rank;
	}

	public String getE_tel() {
		return e_tel;
	}

	public void setE_tel(String e_tel) {
		this.e_tel = e_tel;
	}

	public String getE_email() {
		return e_email;
	}

	public void setE_email(String e_email) {
		this.e_email = e_email;
	}

	public Date getE_joined_company() {
		return e_joined_company;
	}

	public void setE_joined_company(Date e_joined_company) {
		this.e_joined_company = e_joined_company;
	}
	
	
	
	
	
}
