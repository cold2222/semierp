package com.semi.statistics.product;

public class StatistcsProductDTO {
	private String p_name;
	private String p_type;
	private String p_si;
	private String p_quantity;
	private int p_unitcost;
	private int p_minStock;
	private int p_maxStock;
	private String p_manufacturer;
	private long p_countAll;
	private long p_sumByRealCost;
	private long p_sumByUnitCost;
	
	public StatistcsProductDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public StatistcsProductDTO(String p_name, String p_type, String p_si, String p_quantity, int p_unitcost,
			int p_minStock, int p_maxStock, String p_manufacturer, long p_countAll, long p_sumCost) {
		super();
		this.p_name = p_name;
		this.p_type = p_type;
		this.p_si = p_si;
		this.p_quantity = p_quantity;
		this.p_unitcost = p_unitcost;
		this.p_minStock = p_minStock;
		this.p_maxStock = p_maxStock;
		this.p_manufacturer = p_manufacturer;
		this.p_countAll = p_countAll;
		this.p_sumByRealCost = p_sumCost;
		this.p_sumByUnitCost = p_unitcost * p_countAll;
	}


	public long getP_sumByRealCost() {
		return p_sumByRealCost;
	}


	public void setP_sumByRealCost(long p_sumByRealCost) {
		this.p_sumByRealCost = p_sumByRealCost;
	}


	public long getP_sumByUnitCost() {
		return p_sumByUnitCost;
	}


	public void setP_sumByUnitCost(long p_sumByUnitCost) {
		this.p_sumByUnitCost = p_sumByUnitCost;
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
	public String getP_si() {
		return p_si;
	}
	public void setP_si(String p_si) {
		this.p_si = p_si;
	}
	public String getP_quantity() {
		return p_quantity;
	}
	public void setP_quantity(String p_quantity) {
		this.p_quantity = p_quantity;
	}
	public int getP_unitcost() {
		return p_unitcost;
	}
	public void setP_unitcost(int p_unitcost) {
		this.p_unitcost = p_unitcost;
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
	public long getP_countAll() {
		return p_countAll;
	}
	public void setP_countAll(long p_countAll) {
		this.p_countAll = p_countAll;
	}
	public long getP_sumCost() {
		return p_sumByRealCost;
	}
	public void setP_sumCost(long p_sumByRealCost) {
		this.p_sumByRealCost = p_sumByRealCost;
	}
	@Override
	public String toString() {
		return "ProductDTO [p_name=" + p_name + ", p_type=" + p_type + ", p_si=" + p_si + ", p_quantity=" + p_quantity
				+ ", p_unitcost=" + p_unitcost + ", p_minStock=" + p_minStock + ", p_maxStock=" + p_maxStock
				+ ", p_manufacturer=" + p_manufacturer + ", p_countAll=" + p_countAll + ", p_sumByRealCost=" + p_sumByRealCost + ", p_sumByUnitCost=" + p_sumByUnitCost
				+ "]";
	}
	
	
	

}
