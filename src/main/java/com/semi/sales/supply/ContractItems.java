package com.semi.sales.supply;

public class ContractItems {
	private int ci_no;
	private int ci_c_contract_no;
	private int ci_p_id;
	private int ci_count;
	private int ci_unit_price;
	public ContractItems() {
		// TODO Auto-generated constructor stub
	}
	public ContractItems(int ci_no, int ci_c_contract_no, int ci_p_id, int ci_count, int ci_unit_price) {
		super();
		this.ci_no = ci_no;
		this.ci_c_contract_no = ci_c_contract_no;
		this.ci_p_id = ci_p_id;
		this.ci_count = ci_count;
		this.ci_unit_price = ci_unit_price;
	}
	public int getCi_no() {
		return ci_no;
	}
	public void setCi_no(int ci_no) {
		this.ci_no = ci_no;
	}
	public int getCi_c_contract_no() {
		return ci_c_contract_no;
	}
	public void setCi_c_contract_no(int ci_c_contract_no) {
		this.ci_c_contract_no = ci_c_contract_no;
	}
	public int getCi_p_id() {
		return ci_p_id;
	}
	public void setCi_p_id(int ci_p_id) {
		this.ci_p_id = ci_p_id;
	}
	public int getCi_count() {
		return ci_count;
	}
	public void setCi_count(int ci_count) {
		this.ci_count = ci_count;
	}
	public int getCi_unit_price() {
		return ci_unit_price;
	}
	public void setCi_unit_price(int ci_unit_price) {
		this.ci_unit_price = ci_unit_price;
	}
	


}
