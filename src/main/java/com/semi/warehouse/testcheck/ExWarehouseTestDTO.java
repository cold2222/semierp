package com.semi.warehouse.testcheck;

public class ExWarehouseTestDTO {

	private int p_id;
	private String p_name;
	private String p_type;
	private int p_quantity;
	private String p_si;
	private int ci_count;
	private String c_completed_date;
	private int c_status;

	
	public ExWarehouseTestDTO() {
		// TODO Auto-generated constructor stub
	}


	public ExWarehouseTestDTO(int p_id, String p_name, String p_type, int p_quantity, String p_si, int ci_count,
			String c_completed_date, int c_status) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.p_si = p_si;
		this.ci_count = ci_count;
		this.c_completed_date = c_completed_date;
		this.c_status = c_status;
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


	public String getP_si() {
		return p_si;
	}


	public void setP_si(String p_si) {
		this.p_si = p_si;
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



	
	
	
	
	
	
}
