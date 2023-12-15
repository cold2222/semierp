package com.semi.login;

public class EmployeeDTO {
	private String e_no;
	private String e_pw;
	private String e_inputPW;
	private String e_name;
	private String e_rank;
	private String e_tel;
	private String e_mail;
	private String e_dept;
	private int e_deptno;
	public EmployeeDTO() {
		// TODO Auto-generated constructor stub
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

	public String getE_inputPW() {
		return e_inputPW;
	}

	public void setE_inputPW(String e_inputPW) {
		this.e_inputPW = e_inputPW;
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

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getE_dept() {
		return e_dept;
	}

	public void setE_dept(String e_dept) {
		this.e_dept = e_dept;
	}
	

	public int getE_deptno() {
		return e_deptno;
	}

	public void setE_deptno(int e_deptno) {
		this.e_deptno = e_deptno;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [e_no=" + e_no + ", e_pw=" + e_pw + ", e_inputPW=" + e_inputPW + ", e_name=" + e_name
				+ ", e_rank=" + e_rank + ", e_tel=" + e_tel + ", e_mail=" + e_mail + ", e_dept=" + e_dept
				+ ", e_deptno=" + e_deptno + "]";
	}

	
	
	

}
