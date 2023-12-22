package com.semi.sales.supply;

public class Company {
	private int c_no;
	private String c_name;
	private String c_keeper;
	private String c_phone;
	private String c_addr;
	private String c_text;
public Company() {
	// TODO Auto-generated constructor stub
}
public Company(int c_no, String c_name, String c_keeper, String c_phone, String c_addr, String c_text) {
	super();
	this.c_no = c_no;
	this.c_name = c_name;
	this.c_keeper = c_keeper;
	this.c_phone = c_phone;
	this.c_addr = c_addr;
	this.c_text = c_text;
}
public int getC_no() {
	return c_no;
}
public void setC_no(int c_no) {
	this.c_no = c_no;
}
public String getC_name() {
	return c_name;
}
public void setC_name(String c_name) {
	this.c_name = c_name;
}
public String getC_keeper() {
	return c_keeper;
}
public void setC_keeper(String c_keeper) {
	this.c_keeper = c_keeper;
}
public String getC_phone() {
	return c_phone;
}
public void setC_phone(String c_phone) {
	this.c_phone = c_phone;
}
public String getC_addr() {
	return c_addr;
}
public void setC_addr(String c_addr) {
	this.c_addr = c_addr;
}
public String getC_text() {
	return c_text;
}
public void setC_text(String c_text) {
	this.c_text = c_text;
}



}