package com.semi.adminpage.dept;

public class ImportDeptDTO {
	private int i_deptno;
	private String i_dept;
	private int i_count;
	private int i_contract_count;
	private int i_contract_items;
	private int i_total_price;
	private int i_contract_completed;
	private int i_awaiting_stock;
	

	public ImportDeptDTO() {
	}

	public ImportDeptDTO(int i_deptno, String i_dept, int i_count, int i_contract_count, int i_contract_items,
			int i_total_price, int i_contract_completed, int i_awaiting_stock) {
		super();
		this.i_deptno = i_deptno;
		this.i_dept = i_dept;
		this.i_count = i_count;
		this.i_contract_count = i_contract_count;
		this.i_contract_items = i_contract_items;
		this.i_total_price = i_total_price;
		this.i_awaiting_stock = i_awaiting_stock;
		this.i_contract_completed = i_contract_completed;
	}

	public int getI_deptno() {
		return i_deptno;
	}

	public void setI_deptno(int i_deptno) {
		this.i_deptno = i_deptno;
	}

	public String getI_dept() {
		return i_dept;
	}

	public void setI_dept(String i_dept) {
		this.i_dept = i_dept;
	}

	public int getI_count() {
		return i_count;
	}

	public void setI_count(int i_count) {
		this.i_count = i_count;
	}

	public int getI_contract_count() {
		return i_contract_count;
	}

	public void setI_contract_count(int i_contract_count) {
		this.i_contract_count = i_contract_count;
	}

	public int getI_contract_items() {
		return i_contract_items;
	}

	public void setI_contract_items(int i_contract_items) {
		this.i_contract_items = i_contract_items;
	}

	public int getI_total_price() {
		return i_total_price;
	}

	public void setI_total_price(int i_total_price) {
		this.i_total_price = i_total_price;
	}

	public int getI_contract_completed() {
		return i_contract_completed;
	}

	public void setI_contract_completed(int i_contract_completed) {
		this.i_contract_completed = i_contract_completed;
	}

	public int getI_awaiting_stock() {
		return i_awaiting_stock;
	}

	public void setI_awaiting_stock(int i_awaiting_stock) {
		this.i_awaiting_stock = i_awaiting_stock;
	}

	@Override
	public String toString() {
		return "ImportDeptDTO [i_deptno=" + i_deptno + ", i_dept=" + i_dept + ", i_count=" + i_count
				+ ", i_contract_count=" + i_contract_count + ", i_contract_items=" + i_contract_items
				+ ", i_total_price=" + i_total_price + ", i_contract_completed=" + i_contract_completed
				+ ", i_awaiting_stock=" + i_awaiting_stock + "]";
	}

	

}
