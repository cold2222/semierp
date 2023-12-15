package com.semi.sales.supply;

public class ImportCompany {
	private int ic_no;
	private int ic_e_id;
	private String ic_name;
	private String ic_keeper;
	private String ic_phone;
	private String ic_addr;
	private String ic_text;
public ImportCompany() {
	// TODO Auto-generated constructor stub
}
public ImportCompany(int ic_no, int ic_e_id, String ic_name, String ic_keeper, String ic_phone, String ic_addr,
		String ic_text) {
	super();
	this.ic_no = ic_no;
	this.ic_e_id = ic_e_id;
	this.ic_name = ic_name;
	this.ic_keeper = ic_keeper;
	this.ic_phone = ic_phone;
	this.ic_addr = ic_addr;
	this.ic_text = ic_text;
}
public int getIc_no() {
	return ic_no;
}
public void setIc_no(int ic_no) {
	this.ic_no = ic_no;
}
public int getIc_e_id() {
	return ic_e_id;
}
public void setIc_e_id(int ic_e_id) {
	this.ic_e_id = ic_e_id;
}
public String getIc_name() {
	return ic_name;
}
public void setIc_name(String ic_name) {
	this.ic_name = ic_name;
}
public String getIc_keeper() {
	return ic_keeper;
}
public void setIc_keeper(String ic_keeper) {
	this.ic_keeper = ic_keeper;
}
public String getIc_phone() {
	return ic_phone;
}
public void setIc_phone(String ic_phone) {
	this.ic_phone = ic_phone;
}
public String getIc_addr() {
	return ic_addr;
}
public void setIc_addr(String ic_addr) {
	this.ic_addr = ic_addr;
}
public String getIc_text() {
	return ic_text;
}
public void setIc_text(String ic_text) {
	this.ic_text = ic_text;
}


}