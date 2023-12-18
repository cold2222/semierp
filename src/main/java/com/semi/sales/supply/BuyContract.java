package com.semi.sales.supply;

import java.util.Date;

public class BuyContract {
	private int b_contract_no;
	private int b_ic_no;
	private int b_e_id;
	private Date b_contract;
	private Date b_arrival_date;
	private Date b_delivery_date;
	private Date b_date;
	private int b_status;
	private String ic_name;
public BuyContract() {
	// TODO Auto-generated constructor stub
}
public BuyContract(int b_contract_no, int b_ic_no, int b_e_id, Date b_contract, Date b_arrival_date,
		Date b_delivery_date, Date b_date, int b_status, String ic_name) {
	super();
	this.b_contract_no = b_contract_no;
	this.b_ic_no = b_ic_no;
	this.b_e_id = b_e_id;
	this.b_contract = b_contract;
	this.b_arrival_date = b_arrival_date;
	this.b_delivery_date = b_delivery_date;
	this.b_date = b_date;
	this.b_status = b_status;
	this.ic_name = ic_name;
}
public int getB_contract_no() {
	return b_contract_no;
}
public void setB_contract_no(int b_contract_no) {
	this.b_contract_no = b_contract_no;
}
public int getB_ic_no() {
	return b_ic_no;
}
public void setB_ic_no(int b_ic_no) {
	this.b_ic_no = b_ic_no;
}
public int getB_e_id() {
	return b_e_id;
}
public void setB_e_id(int b_e_id) {
	this.b_e_id = b_e_id;
}
public Date getB_contract() {
	return b_contract;
}
public void setB_contract(Date b_contract) {
	this.b_contract = b_contract;
}
public Date getB_arrival_date() {
	return b_arrival_date;
}
public void setB_arrival_date(Date b_arrival_date) {
	this.b_arrival_date = b_arrival_date;
}
public Date getB_delivery_date() {
	return b_delivery_date;
}
public void setB_delivery_date(Date b_delivery_date) {
	this.b_delivery_date = b_delivery_date;
}
public Date getB_date() {
	return b_date;
}
public void setB_date(Date b_date) {
	this.b_date = b_date;
}
public int getB_status() {
	return b_status;
}
public void setB_status(int b_status) {
	this.b_status = b_status;
}
public String getIc_name() {
	return ic_name;
}
public void setIc_name(String ic_name) {
	this.ic_name = ic_name;
}
	

}
