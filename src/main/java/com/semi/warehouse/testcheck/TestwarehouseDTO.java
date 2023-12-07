package com.semi.warehouse.testcheck;

public class TestwarehouseDTO {

	private int p_id;
	private String p_si;
	private String p_type;
	private int p_quantity;
	private String p_name;
	private int p_unicost;
	private int p_minstock;
	private int p_maxstock;
	private int p_manufacturer;
	
	
	public TestwarehouseDTO() {
	// TODO Auto-generated constructor stub
	}


	public TestwarehouseDTO(int p_id, String p_si, String p_type, int p_quantity, String p_name, int p_unicost,
			int p_minstock, int p_maxstock, int p_manufacturer) {
		super();
		this.p_id = p_id;
		this.p_si = p_si;
		this.p_type = p_type;
		this.p_quantity = p_quantity;
		this.p_name = p_name;
		this.p_unicost = p_unicost;
		this.p_minstock = p_minstock;
		this.p_maxstock = p_maxstock;
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


	public int getP_quantity() {
		return p_quantity;
	}


	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}


	public String getP_name() {
		return p_name;
	}


	public void setP_name(String p_name) {
		this.p_name = p_name;
	}


	public int getP_unicost() {
		return p_unicost;
	}


	public void setP_unicost(int p_unicost) {
		this.p_unicost = p_unicost;
	}


	public int getP_minstock() {
		return p_minstock;
	}


	public void setP_minstock(int p_minstock) {
		this.p_minstock = p_minstock;
	}


	public int getP_maxstock() {
		return p_maxstock;
	}


	public void setP_maxstock(int p_maxstock) {
		this.p_maxstock = p_maxstock;
	}


	public int getP_manufacturer() {
		return p_manufacturer;
	}


	public void setP_manufacturer(int p_manufacturer) {
		this.p_manufacturer = p_manufacturer;
	}


	
	
	
	
	
}
