package com.semi.sales.product;

public class Product {
	private int p_id;
	private String p_si;
	private String p_type;
	private String p_quantity;
	private String p_name;
	private String p_unitCost;
	private String p_minStock;
	private String p_maxStock;
	private String p_manufacturer;	
	
public Product() {
}



public Product(int p_id, String p_si, String p_type, String p_quantity, String p_name, String p_unitCost,
		String p_minStock, String p_maxStock, String p_manufacturer) {
	super();
	this.p_id = p_id;
	this.p_si = p_si;
	this.p_type = p_type;
	this.p_quantity = p_quantity;
	this.p_name = p_name;
	this.p_unitCost = p_unitCost;
	this.p_minStock = p_minStock;
	this.p_maxStock = p_maxStock;
	this.p_manufacturer = p_manufacturer;
}



public String getP_manufacturer() {
	return p_manufacturer;
}



public void setP_manufacturer(String p_manufacturer) {
	this.p_manufacturer = p_manufacturer;
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

public String getP_unitCost() {
	return p_unitCost;
}

public void setP_unitCost(String p_unitCost) {
	this.p_unitCost = p_unitCost;
}

public String getP_minStock() {
	return p_minStock;
}

public void setP_minStock(String p_minStock) {
	this.p_minStock = p_minStock;
}

public String getP_maxStock() {
	return p_maxStock;
}

public void setP_maxStock(String p_maxStock) {
	this.p_maxStock = p_maxStock;
}


	

}
