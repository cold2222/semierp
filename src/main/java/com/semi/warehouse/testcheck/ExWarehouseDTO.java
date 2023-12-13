package com.semi.warehouse.testcheck;

public class ExWarehouseDTO {

	private String p_name;
	private String p_si;
	private String p_type;
	private int p_quantity;
	private String ex_warehouse_date;
	private int ex_warehouse_id;
	private int ex_warehouse_quantity;
	private String warehouse_name; 
	
	
	public ExWarehouseDTO() {
		// TODO Auto-generated constructor stub
	}


	public ExWarehouseDTO(String p_name, String p_si, String p_type, int p_quantity, String ex_warehouse_date,
			int ex_warehouse_id, int ex_warehouse_quantity, String warehouse_name) {
		super();
		this.p_name = p_name;
		this.p_si = p_si;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.ex_warehouse_date = ex_warehouse_date;
		this.ex_warehouse_id = ex_warehouse_id;
		this.ex_warehouse_quantity = ex_warehouse_quantity;
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


	public int getP_quantity() {
		return p_quantity;
	}


	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}


	public String getEx_warehouse_date() {
		return ex_warehouse_date;
	}


	public void setEx_warehouse_date(String ex_warehouse_date) {
		this.ex_warehouse_date = ex_warehouse_date;
	}


	public int getEx_warehouse_id() {
		return ex_warehouse_id;
	}


	public void setEx_warehouse_id(int ex_warehouse_id) {
		this.ex_warehouse_id = ex_warehouse_id;
	}


	public int getEx_warehouse_quantity() {
		return ex_warehouse_quantity;
	}


	public void setEx_warehouse_quantity(int ex_warehouse_quantity) {
		this.ex_warehouse_quantity = ex_warehouse_quantity;
	}


	public String getWarehouse_name() {
		return warehouse_name;
	}


	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	

	
	
	
	
	
	
	
}