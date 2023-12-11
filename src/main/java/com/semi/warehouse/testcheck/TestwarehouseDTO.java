package com.semi.warehouse.testcheck;

public class TestwarehouseDTO {

	private int p_id;
	private String p_name;
	private String p_si;
	private String p_type;
	private int record_count;
	private String in_warehouse_date;
	private int status;

//	private int warehouse_id;
//	private int in_warehouse_quantity;
//	private String warehouse_name;


	public TestwarehouseDTO() {
		// TODO Auto-generated constructor stub
	}

public TestwarehouseDTO(int p_id, String p_name, String p_si, String p_type, int record_count, String in_warehouse_date,
		int status) {
	super();
	this.p_id = p_id;
	this.p_name = p_name;
	this.p_si = p_si;
	this.p_type = p_type;
	this.record_count = record_count;
	this.in_warehouse_date = in_warehouse_date;
	this.status = status;
}

public int getP_id() {
	return p_id;
}

public void setP_id(int p_id) {
	this.p_id = p_id;
}

public String getP_name() {
	return p_name;
}

public void setP_name(String p_name) {
	this.p_name = p_name;
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

public int getRecord_count() {
	return record_count;
}

public void setRecord_count(int record_count) {
	this.record_count = record_count;
}

public String getIn_warehouse_date() {
	return in_warehouse_date;
}

public void setIn_warehouse_date(String in_warehouse_date) {
	this.in_warehouse_date = in_warehouse_date;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}


	
	
	
	
}
