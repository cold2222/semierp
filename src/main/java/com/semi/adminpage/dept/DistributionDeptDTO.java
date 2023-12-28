package com.semi.adminpage.dept;

public class DistributionDeptDTO {
private int d_deptno;
private String d_dept;
private int d_count;
private int d_waiting;
private int d_allocated;
private int d_allocatedThisMonth;
private int d_completedThisMonth;
private int d_expired;
private int d_dueDate;
private int d_todayDelivery;
private int d_todayCompleted;

public DistributionDeptDTO() {
	// TODO Auto-generated constructor stub
}




public DistributionDeptDTO(int d_deptno, String d_dept, int d_count, int d_waiting, int d_allocated,
		int d_allocatedThisMonth, int d_completedThisMonth, int d_expired, int d_dueDate, int d_todayDelivery,
		int d_todayCompleted) {
	super();
	this.d_deptno = d_deptno;
	this.d_dept = d_dept;
	this.d_count = d_count;
	this.d_waiting = d_waiting;
	this.d_allocated = d_allocated;
	this.d_allocatedThisMonth = d_allocatedThisMonth;
	this.d_completedThisMonth = d_completedThisMonth;
	this.d_expired = d_expired;
	this.d_dueDate = d_dueDate;
	this.d_todayDelivery = d_todayDelivery;
	this.d_todayCompleted = d_todayCompleted;
}




public int getD_expired() {
	return d_expired;
}



public void setD_expired(int d_expired) {
	this.d_expired = d_expired;
}



public int getD_dueDate() {
	return d_dueDate;
}



public void setD_dueDate(int d_dueDate) {
	this.d_dueDate = d_dueDate;
}



public int getD_todayDelivery() {
	return d_todayDelivery;
}



public void setD_todayDelivery(int d_todayDelivery) {
	this.d_todayDelivery = d_todayDelivery;
}



public int getD_todayCompleted() {
	return d_todayCompleted;
}



public void setD_todayCompleted(int d_todayCompleted) {
	this.d_todayCompleted = d_todayCompleted;
}



public int getD_deptno() {
	return d_deptno;
}

public void setD_deptno(int d_deptno) {
	this.d_deptno = d_deptno;
}

public String getD_dept() {
	return d_dept;
}

public void setD_dept(String d_dept) {
	this.d_dept = d_dept;
}

public int getD_count() {
	return d_count;
}

public void setD_count(int d_count) {
	this.d_count = d_count;
}

public int getD_waiting() {
	return d_waiting;
}

public void setD_waiting(int d_waiting) {
	this.d_waiting = d_waiting;
}

public int getD_allocated() {
	return d_allocated;
}

public void setD_allocated(int d_allocated) {
	this.d_allocated = d_allocated;
}

public int getD_allocatedThisMonth() {
	return d_allocatedThisMonth;
}

public void setD_allocatedThisMonth(int d_allocatedThisMonth) {
	this.d_allocatedThisMonth = d_allocatedThisMonth;
}

public int getD_completedThisMonth() {
	return d_completedThisMonth;
}

public void setD_completedThisMonth(int d_completedThisMonth) {
	this.d_completedThisMonth = d_completedThisMonth;
}




@Override
public String toString() {
	return "DistributionDeptDTO [d_deptno=" + d_deptno + ", d_dept=" + d_dept + ", d_count=" + d_count + ", d_waiting="
			+ d_waiting + ", d_allocated=" + d_allocated + ", d_allocatedThisMonth=" + d_allocatedThisMonth
			+ ", d_completedThisMonth=" + d_completedThisMonth + ", d_expired=" + d_expired + ", d_dueDate=" + d_dueDate
			+ ", d_todayDelivery=" + d_todayDelivery + ", d_todayCompleted=" + d_todayCompleted + "]";
}







}
