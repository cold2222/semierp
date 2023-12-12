package com.semi.warehouse.outcheck;

public class ExWarehouseTestDTO {

	private int p_id;
	private String p_name;
	private String p_type;
	private int p_quantity;
	private String p_si;
	private int record_sales_count;
	private String sell_date;
	private int status;

	
	public ExWarehouseTestDTO() {
		// TODO Auto-generated constructor stub
	}


	public ExWarehouseTestDTO(int p_id, String p_name, String p_type, int p_quantity, String p_si,
			int record_sales_count, String sell_date, int status) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.p_si = p_si;
		this.record_sales_count = record_sales_count;
		this.sell_date = sell_date;
		this.status = status;
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


	public int getRecord_sales_count() {
		return record_sales_count;
	}


	public void setRecord_sales_count(int record_sales_count) {
		this.record_sales_count = record_sales_count;
	}


	public String getSell_date() {
		return sell_date;
	}


	public void setSell_date(String sell_date) {
		this.sell_date = sell_date;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	
	
	
	
	
	
}
