package com.semi.warehouse.warehouseboard;

public class WarehouseBoardTestDTO {

	
	private int p_id;
	private String p_name;
	private int p_quantity;
	private String p_si;
	private String p_type;
	private String warehouse_name;
	private String manufacture_name;
	private int stock;
	private String today_date;

	public WarehouseBoardTestDTO() {
		// TODO Auto-generated constructor stub
	}

	public WarehouseBoardTestDTO(int p_id, String p_name, int p_quantity, String p_si, String p_type,
			String warehouse_name, String manufacture_name, int stock, String today_date) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_quantity = p_quantity;
		this.p_si = p_si;
		this.p_type = p_type;
		this.warehouse_name = warehouse_name;
		this.manufacture_name = manufacture_name;
		this.stock = stock;
		this.today_date = today_date;
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

	public String getP_type() {
		return p_type;
	}

	public void setP_type(String p_type) {
		this.p_type = p_type;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public String getManufacture_name() {
		return manufacture_name;
	}

	public void setManufacture_name(String manufacture_name) {
		this.manufacture_name = manufacture_name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getToday_date() {
		return today_date;
	}

	public void setToday_date(String today_date) {
		this.today_date = today_date;
	}
	
	
	
	
	
	
}
