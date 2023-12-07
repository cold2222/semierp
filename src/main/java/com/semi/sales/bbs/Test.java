package com.semi.sales.bbs;

import java.util.Date;

public class Test {
	private int record_num;
	private int company_sell;
	private Date delivery_date;
	private Date sell_date;
	
	public Test() {

	}

	public Test(int record_num, int company_sell, Date delivery_date, Date sell_date) {
		super();
		this.record_num = record_num;
		this.company_sell = company_sell;
		this.delivery_date = delivery_date;
		this.sell_date = sell_date;
	}

	public int getRecord_num() {
		return record_num;
	}

	public void setRecord_num(int record_num) {
		this.record_num = record_num;
	}

	public int getCompany_sell() {
		return company_sell;
	}

	public void setCompany_sell(int company_sell) {
		this.company_sell = company_sell;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public Date getSell_date() {
		return sell_date;
	}

	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}
	
	
	
}
