package com.semi.adminpage.dept;

public class ContractDeptDTO {
	private int c_deptno;
	private String c_dept;
	private int c_count;
	private int c_contract_count;
	private int c_contract_items;
	private int c_total_price;
	private int c_contract_completed;
	private int c_awaiting_stock;
	

	public ContractDeptDTO() {
	}


	public ContractDeptDTO(int c_deptno, String c_dept, int c_count, int c_contract_count, int c_contract_items,
			int c_total_price, int c_contract_completed, int c_awaiting_stock) {
		super();
		this.c_deptno = c_deptno;
		this.c_dept = c_dept;
		this.c_count = c_count;
		this.c_contract_count = c_contract_count;
		this.c_contract_items = c_contract_items;
		this.c_total_price = c_total_price;
		this.c_contract_completed = c_contract_completed;
		this.c_awaiting_stock = c_awaiting_stock;
	}


	public int getC_deptno() {
		return c_deptno;
	}


	public void setC_deptno(int c_deptno) {
		this.c_deptno = c_deptno;
	}


	public String getC_dept() {
		return c_dept;
	}


	public void setC_dept(String c_dept) {
		this.c_dept = c_dept;
	}


	public int getC_count() {
		return c_count;
	}


	public void setC_count(int c_count) {
		this.c_count = c_count;
	}


	public int getC_contract_count() {
		return c_contract_count;
	}


	public void setC_contract_count(int c_contract_count) {
		this.c_contract_count = c_contract_count;
	}


	public int getC_contract_items() {
		return c_contract_items;
	}


	public void setC_contract_items(int c_contract_items) {
		this.c_contract_items = c_contract_items;
	}


	public int getC_total_price() {
		return c_total_price;
	}


	public void setC_total_price(int c_total_price) {
		this.c_total_price = c_total_price;
	}


	public int getC_contract_completed() {
		return c_contract_completed;
	}


	public void setC_contract_completed(int c_contract_completed) {
		this.c_contract_completed = c_contract_completed;
	}


	public int getC_awaiting_stock() {
		return c_awaiting_stock;
	}


	public void setC_awaiting_stock(int c_awaiting_stock) {
		this.c_awaiting_stock = c_awaiting_stock;
	}


	@Override
	public String toString() {
		return "ContractDeptDTO [c_deptno=" + c_deptno + ", c_dept=" + c_dept + ", c_count=" + c_count
				+ ", c_contract_count=" + c_contract_count + ", c_contract_items=" + c_contract_items
				+ ", c_total_price=" + c_total_price + ", c_contract_completed=" + c_contract_completed
				+ ", c_awaiting_stock=" + c_awaiting_stock + "]";
	}

	

	

}
