package com.semi.distribution.shipping;

import java.util.Date;

public class ShippingDTO {
	private String c_name;
	private String e_name;
	private String e_rank;
	private String s_contract_no;
	private String c_type;
	private Date c_due_date;
	private Date c_delivery_date;
	public Date getC_delivery_date() {
		return c_delivery_date;
	}
	public void setC_delivery_date(Date c_delivery_date) {
		this.c_delivery_date = c_delivery_date;
	}
	public Date getC_due_date() {
		return c_due_date;
	}
	public void setC_due_date(Date c_due_date) {
		this.c_due_date = c_due_date;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
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
	public String getS_contract_no() {
		return s_contract_no;
	}
	public void setS_contract_no(String s_contract_no) {
		this.s_contract_no = s_contract_no;
	}
	public String getC_type() {
		return c_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}
	
}
