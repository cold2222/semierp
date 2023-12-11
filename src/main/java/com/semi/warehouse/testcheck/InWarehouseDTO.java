package com.semi.warehouse.testcheck;

public class InWarehouseDTO {

	private String p_name;
	private String p_si;
	private String p_type;
	private String in_warehouse_date;
	private int in_warehouse_id;
	private int in_warehouse_quantity;
	private String warehouse_name; 
	
	
	public InWarehouseDTO() {
		// TODO Auto-generated constructor stub
	}


	public InWarehouseDTO(String p_name, String p_si, String p_type, String in_warehouse_date, int in_warehouse_id,
			int in_warehouse_quantity, String warehouse_name) {
		super();
		this.p_name = p_name;
		this.p_si = p_si;
		this.p_type = p_type;
		this.in_warehouse_date = in_warehouse_date;
		this.in_warehouse_id = in_warehouse_id;
		this.in_warehouse_quantity = in_warehouse_quantity;
		this.warehouse_name = warehouse_name;
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


	public String getIn_warehouse_date() {
		return in_warehouse_date;
	}


	public void setIn_warehouse_date(String in_warehouse_date) {
		this.in_warehouse_date = in_warehouse_date;
	}


	public int getIn_warehouse_id() {
		return in_warehouse_id;
	}


	public void setIn_warehouse_id(int in_warehouse_id) {
		this.in_warehouse_id = in_warehouse_id;
	}


	public int getIn_warehouse_quantity() {
		return in_warehouse_quantity;
	}


	public void setIn_warehouse_quantity(int in_warehouse_quantity) {
		this.in_warehouse_quantity = in_warehouse_quantity;
	}


	public String getWarehouse_name() {
		return warehouse_name;
	}


	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	
	
	
	
	
	
	
}