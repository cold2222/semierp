package com.semi.warehouse.warehouseboard;

public class WarehouseBoardTestDTO {

	
	private int p_id;
	private String p_name;
	private int p_quantity;
	private String p_si;
	private String p_type;
	private int p_unicost;
	private String warehouse_name;
	private String manufacture_name;
	private int stock;
	private String today_date;
	private int warehouse_id;
	private int total_stock;
	private String e_name;
	
	
	public WarehouseBoardTestDTO() {
		// TODO Auto-generated constructor stub
	}


	public WarehouseBoardTestDTO(int p_id, String p_name, int p_quantity, String p_si, String p_type, int p_unicost,
			String warehouse_name, String manufacture_name, int stock, String today_date, int warehouse_id,
			int total_stock, String e_name) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_quantity = p_quantity;
		this.p_si = p_si;
		this.p_type = p_type;
		this.p_unicost = p_unicost;
		this.warehouse_name = warehouse_name;
		this.manufacture_name = manufacture_name;
		this.stock = stock;
		this.today_date = today_date;
		this.warehouse_id = warehouse_id;
		this.total_stock = total_stock;
		this.e_name = e_name;
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


	public int getP_unicost() {
		return p_unicost;
	}


	public void setP_unicost(int p_unicost) {
		this.p_unicost = p_unicost;
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


	public int getWarehouse_id() {
		return warehouse_id;
	}


	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}


	public int getTotal_stock() {
		return total_stock;
	}


	public void setTotal_stock(int total_stock) {
		this.total_stock = total_stock;
	}


	public String getE_name() {
		return e_name;
	}


	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	
	
	
}
