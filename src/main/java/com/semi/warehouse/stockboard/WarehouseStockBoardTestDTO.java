package com.semi.warehouse.stockboard;

public class WarehouseStockBoardTestDTO {

	private int p_id;
	private String p_name;
	private String p_type;
	private int p_quantity;
	private String p_si;
	private String manufacture_name;
	private int p_unicost;
	private int stock;
	
	public WarehouseStockBoardTestDTO() {
		// TODO Auto-generated constructor stub
	}

	public WarehouseStockBoardTestDTO(int p_id, String p_name, String p_type, int p_quantity, String p_si,
			String manufacture_name, int p_unicost, int stock) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.p_si = p_si;
		this.manufacture_name = manufacture_name;
		this.p_unicost = p_unicost;
		this.stock = stock;
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

	public String getManufacture_name() {
		return manufacture_name;
	}

	public void setManufacture_name(String manufacture_name) {
		this.manufacture_name = manufacture_name;
	}

	public int getP_unicost() {
		return p_unicost;
	}

	public void setP_unicost(int p_unicost) {
		this.p_unicost = p_unicost;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	
	

	
	
	
}
