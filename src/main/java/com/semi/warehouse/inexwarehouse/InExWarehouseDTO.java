package com.semi.warehouse.inexwarehouse;

public class InExWarehouseDTO {

	private int p_id;
	private String p_name;
	private String p_si;
	private String p_type;
	private int p_quantity;
	private int quantity;
	private String warehouse_date;
	private String warehouse_type;
	private int warehouse_id; 
	private String warehouse_name; 
	
	public InExWarehouseDTO() {
		// TODO Auto-generated constructor stub
	}

	public InExWarehouseDTO(int p_id, String p_name, String p_si, String p_type, int p_quantity, int quantity,
			String warehouse_date, String warehouse_type, int warehouse_id, String warehouse_name) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_si = p_si;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.quantity = quantity;
		this.warehouse_date = warehouse_date;
		this.warehouse_type = warehouse_type;
		this.warehouse_id = warehouse_id;
		this.warehouse_name = warehouse_name;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getWarehouse_date() {
		return warehouse_date;
	}

	public void setWarehouse_date(String warehouse_date) {
		this.warehouse_date = warehouse_date;
	}

	public String getWarehouse_type() {
		return warehouse_type;
	}

	public void setWarehouse_type(String warehouse_type) {
		this.warehouse_type = warehouse_type;
	}

	public int getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	
	
	
	
}
