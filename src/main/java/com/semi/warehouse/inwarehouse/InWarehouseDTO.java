package com.semi.warehouse.inwarehouse;

public class InWarehouseDTO {

	private int ci_p_id;
	private String p_name;
	private String p_si;
	private String p_type;
	private String p_quantity;
	private int ci_count;
	private String c_completed_date;
	private int c_status;

	// 이것도 보여줄거 확인
	private int c_contract_no;
	private String c_created_date;
	private String c_name;
	private String e_name;
	
	
	public InWarehouseDTO() {
		// TODO Auto-generated constructor stub
	}


	public InWarehouseDTO(int ci_p_id, String p_name, String p_si, String p_type, String p_quantity, int ci_count,
			String c_completed_date, int c_status, int c_contract_no, String c_created_date, String c_name,
			String e_name) {
		super();
		this.ci_p_id = ci_p_id;
		this.p_name = p_name;
		this.p_si = p_si;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.ci_count = ci_count;
		this.c_completed_date = c_completed_date;
		this.c_status = c_status;
		this.c_contract_no = c_contract_no;
		this.c_created_date = c_created_date;
		this.c_name = c_name;
		this.e_name = e_name;
	}


	public int getCi_p_id() {
		return ci_p_id;
	}


	public void setCi_p_id(int ci_p_id) {
		this.ci_p_id = ci_p_id;
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


	public String getP_quantity() {
		return p_quantity;
	}


	public void setP_quantity(String p_quantity) {
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


	public String getC_created_date() {
		return c_created_date;
	}


	public void setC_created_date(String c_created_date) {
		this.c_created_date = c_created_date;
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


	


	
	

	
	
}
	
	
