package com.semi.sales.supply;

public class BuyItem {
	private int bi_no;
	private int bi_b_contract_no;
	private int bi_p_id;
	private int bi_buy_count;
	private int bi_unit_price;
	public BuyItem() {
		// TODO Auto-generated constructor stub
	}
	
	public BuyItem(int bi_no, int bi_b_contract_no, int bi_p_id, int bi_buy_count, int bi_unit_price) {
		super();
		this.bi_no = bi_no;
		this.bi_b_contract_no = bi_b_contract_no;
		this.bi_p_id = bi_p_id;
		this.bi_buy_count = bi_buy_count;
		this.bi_unit_price = bi_unit_price;
	}

	public int getBi_no() {
		return bi_no;
	}
	public void setBi_no(int bi_no) {
		this.bi_no = bi_no;
	}
	public int getBi_b_contract_no() {
		return bi_b_contract_no;
	}
	public void setBi_b_contract_no(int bi_b_contract_no) {
		this.bi_b_contract_no = bi_b_contract_no;
	}
	public int getBi_p_id() {
		return bi_p_id;
	}
	public void setBi_p_id(int bi_p_id) {
		this.bi_p_id = bi_p_id;
	}
	public int getBi_buy_count() {
		return bi_buy_count;
	}
	public void setBi_buy_count(int bi_buy_count) {
		this.bi_buy_count = bi_buy_count;
	}
	public int getBi_unit_price() {
		return bi_unit_price;
	}
	public void setBi_unit_price(int bi_unit_price) {
		this.bi_unit_price = bi_unit_price;
	}



}
