package com.semi.adminpage.dept;

public class DeptDTO {
	private String d_dept;
	private int d_deptno;
	private int d_count;
	
	public DeptDTO() {
		// TODO Auto-generated constructor stub
	}

	public DeptDTO(String d_dept, int d_deptno, int d_count) {
		super();
		this.d_dept = d_dept;
		this.d_deptno = d_deptno;
		this.d_count = d_count;
	}

	public String getD_dept() {
		return d_dept;
	}

	public void setD_dept(String d_dept) {
		this.d_dept = d_dept;
	}

	public int getD_deptno() {
		return d_deptno;
	}

	public void setD_deptNo(int d_deptno) {
		this.d_deptno = d_deptno;
	}

	public int getD_count() {
		return d_count;
	}

	public void setD_count(int d_count) {
		this.d_count = d_count;
	}

	@Override
	public String toString() {
		return "DeptDTO [d_dept=" + d_dept + ", d_deptNo=" + d_deptno + ", d_count=" + d_count + "]";
	}
	
	
}
