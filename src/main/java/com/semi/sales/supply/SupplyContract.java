package com.semi.sales.supply;

import java.util.Date;

public class SupplyContract {
	private int recordall_buy_num;
	private int supply_num;
	private Date purchase_date;
	private Date transaction_date;
	private Date in_warehouse_date;
	private int status;
public SupplyContract() {
}
public SupplyContract(int recordall_buy_num, int supply_num, Date purchase_date, Date transaction_date,
		Date in_warehouse_date, int status) {
	super();
	this.recordall_buy_num = recordall_buy_num;
	this.supply_num = supply_num;
	this.purchase_date = purchase_date;
	this.transaction_date = transaction_date;
	this.in_warehouse_date = in_warehouse_date;
	this.status = status;
}
public int getRecordall_buy_num() {
	return recordall_buy_num;
}
public void setRecordall_buy_num(int recordall_buy_num) {
	this.recordall_buy_num = recordall_buy_num;
}
public int getSupply_num() {
	return supply_num;
}
public void setSupply_num(int supply_num) {
	this.supply_num = supply_num;
}
public Date getPurchase_date() {
	return purchase_date;
}
public void setPurchase_date(Date purchase_date) {
	this.purchase_date = purchase_date;
}
public Date getTransaction_date() {
	return transaction_date;
}
public void setTransaction_date(Date transaction_date) {
	this.transaction_date = transaction_date;
}
public Date getIn_warehouse_date() {
	return in_warehouse_date;
}
public void setIn_warehouse_date(Date in_warehouse_date) {
	this.in_warehouse_date = in_warehouse_date;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}

}
