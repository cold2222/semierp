package com.semi.warehouse.inwarehouse;

public class InWarehouseDTO {

	private int p_id;
	private String p_name;
	private String p_si;
	private String p_type;
	private int p_quantity;
	private int ci_count;
	private String c_completed_date;
	private int c_status;
	private int c_contract_no;


	public InWarehouseDTO() {
		// TODO Auto-generated constructor stub
	}


	public InWarehouseDTO(int p_id, String p_name, String p_si, String p_type, int p_quantity, int ci_count,
			String c_completed_date, int c_status, int c_contract_no) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_si = p_si;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.ci_count = ci_count;
		this.c_completed_date = c_completed_date;
		this.c_status = c_status;
		this.c_contract_no = c_contract_no;
	}


	public int getP_id() {
		return p_id;
	}


	public void setP_id(int p_id) {
		this.p_id = p_id;
	}


	public String getP_name() {
		return p_name;
	}


	public void setP_name(String p_name) {
		this.p_name = p_name;
	}


	public String getP_si() {
		return p_si;
	}


	public void setP_si(String p_si) {
		this.p_si = p_si;
	}


	public String getP_type() {
		return p_type;
	}


	public void setP_type(String p_type) {
		this.p_type = p_type;
	}


	public int getP_quantity() {
		return p_quantity;
	}


	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}


	public int getCi_count() {
		return ci_count;
	}


	public void setCi_count(int ci_count) {
		this.ci_count = ci_count;
	}


	public String getC_completed_date() {
		return c_completed_date;
	}


	public void setC_completed_date(String c_completed_date) {
		this.c_completed_date = c_completed_date;
	}


	public int getC_status() {
		return c_status;
	}


	public void setC_status(int c_status) {
		this.c_status = c_status;
	}


	public int getC_contract_no() {
		return c_contract_no;
	}


	public void setC_contract_no(int c_contract_no) {
		this.c_contract_no = c_contract_no;
	}



	

	
	
}
	
	
