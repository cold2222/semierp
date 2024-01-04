package com.semi.adminpage.product;

public class AdminProductDTO {
	private int p_id;
	private String p_si;
	private String p_type;
	private String p_quantity;
	private String p_name;
	private int p_unitCost;
	private int p_stock;
	private int p_minStock;
	private int p_maxStock;
	private String p_manufacturer;
	private int p_check;
	
	public AdminProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public AdminProductDTO(int p_id, String p_si, String p_type, String p_quantity, String p_name, int p_unitCost,
			int p_stock, int p_minStock, int p_maxStock, String p_manufacturer, int p_check) {
		super();
		this.p_id = p_id;
		this.p_si = p_si;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.p_name = p_name;
		this.p_unitCost = p_unitCost;
		this.p_stock = p_stock;
		this.p_minStock = p_minStock;
		this.p_maxStock = p_maxStock;
		this.p_manufacturer = p_manufacturer;
		this.p_check = p_check;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
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

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_unitCost() {
		return p_unitCost;
	}

	public void setP_unitCost(int p_unitCost) {
		this.p_unitCost = p_unitCost;
	}

	public int getP_stock() {
		return p_stock;
	}

	public void setP_stock(int p_stock) {
		this.p_stock = p_stock;
	}

	public int getP_minStock() {
		return p_minStock;
	}

	public void setP_minStock(int p_minStock) {
		this.p_minStock = p_minStock;
	}

	public int getP_maxStock() {
		return p_maxStock;
	}

	public void setP_maxStock(int p_maxStock) {
		this.p_maxStock = p_maxStock;
	}

	public String getP_manufacturer() {
		return p_manufacturer;
	}

	public void setP_manufacturer(String p_manufacturer) {
		this.p_manufacturer = p_manufacturer;
	}

	public int getP_check() {
		return p_check;
	}

	public void setP_check(int p_check) {
		this.p_check = p_check;
	}

	@Override
	public String toString() {
		return "AdminProductDTO [p_id=" + p_id + ", p_si=" + p_si + ", p_type=" + p_type + ", p_quantity=" + p_quantity
				+ ", p_name=" + p_name + ", p_unitCost=" + p_unitCost + ", p_stock=" + p_stock + ", p_minStock="
				+ p_minStock + ", p_maxStock=" + p_maxStock + ", p_manufacturer=" + p_manufacturer + ", p_check="
				+ p_check + "]";
	}
	
	
}
