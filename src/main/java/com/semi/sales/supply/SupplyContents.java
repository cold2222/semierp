package com.semi.sales.supply;

public class SupplyContents {
	private int record_buy_num;
	private int recordall_buy_num;
	private int p_id;
	private int record_count;
	private int record_price;
	public SupplyContents() {
	}
	public SupplyContents(int record_buy_num, int recordall_buy_num, int p_id, int record_count, int record_price) {
		super();
		this.record_buy_num = record_buy_num;
		this.recordall_buy_num = recordall_buy_num;
		this.p_id = p_id;
		this.record_count = record_count;
		this.record_price = record_price;
	}
	public int getRecord_buy_num() {
		return record_buy_num;
	}
	public void setRecord_buy_num(int record_buy_num) {
		this.record_buy_num = record_buy_num;
	}
	public int getRecordall_buy_num() {
		return recordall_buy_num;
	}
	public void setRecordall_buy_num(int recordall_buy_num) {
		this.recordall_buy_num = recordall_buy_num;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getRecord_count() {
		return record_count;
	}
	public void setRecord_count(int record_count) {
		this.record_count = record_count;
	}
	public int getRecord_price() {
		return record_price;
	}
	public void setRecord_price(int record_price) {
		this.record_price = record_price;
	}



}
