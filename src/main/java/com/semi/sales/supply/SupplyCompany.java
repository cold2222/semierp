package com.semi.sales.supply;

public class SupplyCompany {
	private int supply_num;
    private String supply_company;
    private String supply_name;
    private String supplied_name;
    private String supply_addr;
    private String purchase_text;
    
    public SupplyCompany() {
	}

	public SupplyCompany(int supply_num, String supply_company, String supply_name,
			String supplied_name, String supply_addr, String purchase_text) {
		super();
		this.supply_num = supply_num;
		this.supply_company = supply_company;
		this.supply_name = supply_name;
		this.supplied_name = supplied_name;
		this.supply_addr = supply_addr;
		this.purchase_text = purchase_text;
	}

	public int getSupply_num() {
		return supply_num;
	}

	public void setSupply_num(int supply_num) {
		this.supply_num = supply_num;
	}



	public String getSupply_company() {
		return supply_company;
	}

	public void setSupply_company(String supply_company) {
		this.supply_company = supply_company;
	}

	public String getSupply_name() {
		return supply_name;
	}

	public void setSupply_name(String supply_name) {
		this.supply_name = supply_name;
	}

	public String getSupplied_name() {
		return supplied_name;
	}

	public void setSupplied_name(String supplied_name) {
		this.supplied_name = supplied_name;
	}

	public String getSupply_addr() {
		return supply_addr;
	}

	public void setSupply_addr(String supply_addr) {
		this.supply_addr = supply_addr;
	}

	public String getPurchase_text() {
		return purchase_text;
	}

	public void setPurchase_text(String purchase_text) {
		this.purchase_text = purchase_text;
	}



}
