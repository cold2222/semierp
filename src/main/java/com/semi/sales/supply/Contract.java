package com.semi.sales.supply;

import java.util.Date;
import java.util.List;

public class Contract {
	private int c_contract_no;
	private int c_c_no;
	private int c_e_id;
	private Date c_created_date;
	private Date c_due_date;
	private Date c_delivery_date;
	private Date c_completed_date;
	private int c_status;
	private int c_type;
	private String c_name;

	private List<ContractItems> items;

	public List<ContractItems> getItems() {
		return items;
	}

	public void setItems(List<ContractItems> items) {
		this.items = items;
	}

	public int getC_c_no() {
		return c_c_no;
	}

	public void setC_c_no(int c_c_no) {
		this.c_c_no = c_c_no;
	}

	public int getC_contract_no() {
		return c_contract_no;
	}

	public void setC_contract_no(int c_contract_no) {
		this.c_contract_no = c_contract_no;
	}

	public int getC_e_id() {
		return c_e_id;
	}

	public void setC_e_id(int c_e_id) {
		this.c_e_id = c_e_id;
	}

	public Date getC_created_date() {
		return c_created_date;
	}

	public void setC_created_date(Date c_created_date) {
		this.c_created_date = c_created_date;
	}

	public Date getC_due_date() {
		return c_due_date;
	}

	public void setC_due_date(Date c_due_date) {
		this.c_due_date = c_due_date;
	}

	public Date getC_delivery_date() {
		return c_delivery_date;
	}

	public void setC_delivery_date(Date c_delivery_date) {
		this.c_delivery_date = c_delivery_date;
	}

	public Date getC_completed_date() {
		return c_completed_date;
	}

	public void setC_completed_date(Date c_completed_date) {
		this.c_completed_date = c_completed_date;
	}

	public int getC_status() {
		return c_status;
	}

	public void setC_status(int c_status) {
		this.c_status = c_status;
	}

	public int getC_type() {
		return c_type;
	}

	public void setC_type(int c_type) {
		this.c_type = c_type;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

}
