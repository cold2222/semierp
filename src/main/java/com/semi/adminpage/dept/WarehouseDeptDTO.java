package com.semi.adminpage.dept;

public class WarehouseDeptDTO {
	private int w_deptno;
	private String w_dept;
	private int w_count;
	private int w_products;
	private long w_value;
	private int w_underMinStock;
	private int w_overMaxStock;
	private int w_stockInCompletedThisMonth;
	private int w_watingStockInThisMonth;
	private int w_stockInToday;
	private int w_stockInCompletedToday;
	private int w_stockOutCompletedThisMonth;
	private int w_watingStockOutThisMonth;
	private int w_stockOutToday;
	private int w_stockOutCompletedToday;
	
	public WarehouseDeptDTO() {
		// TODO Auto-generated constructor stub
	}

	public WarehouseDeptDTO(int w_deptno, String w_dept, int w_count, int w_products, long w_value, int w_underMinStock,
			int w_overMaxStock, int w_stockInCompletedThisMonth, int w_watingStockInThisMonth, int w_stockInToday,
			int w_stockInCompletedToday, int w_stockOutCompletedThisMonth, int w_watingStockOutThisMonth,
			int w_stockOutToday, int w_stockOutCompletedToday) {
		super();
		this.w_deptno = w_deptno;
		this.w_dept = w_dept;
		this.w_count = w_count;
		this.w_products = w_products;
		this.w_value = w_value;
		this.w_underMinStock = w_underMinStock;
		this.w_overMaxStock = w_overMaxStock;
		this.w_stockInCompletedThisMonth = w_stockInCompletedThisMonth;
		this.w_watingStockInThisMonth = w_watingStockInThisMonth;
		this.w_stockInToday = w_stockInToday;
		this.w_stockInCompletedToday = w_stockInCompletedToday;
		this.w_stockOutCompletedThisMonth = w_stockOutCompletedThisMonth;
		this.w_watingStockOutThisMonth = w_watingStockOutThisMonth;
		this.w_stockOutToday = w_stockOutToday;
		this.w_stockOutCompletedToday = w_stockOutCompletedToday;
	}

	public int getW_deptno() {
		return w_deptno;
	}

	public void setW_deptno(int w_deptno) {
		this.w_deptno = w_deptno;
	}

	public String getW_dept() {
		return w_dept;
	}

	public void setW_dept(String w_dept) {
		this.w_dept = w_dept;
	}

	public int getW_count() {
		return w_count;
	}

	public void setW_count(int w_count) {
		this.w_count = w_count;
	}

	public int getW_products() {
		return w_products;
	}

	public void setW_products(int w_products) {
		this.w_products = w_products;
	}

	public long getW_value() {
		return w_value;
	}

	public void setW_value(long w_value) {
		this.w_value = w_value;
	}

	public int getW_underMinStock() {
		return w_underMinStock;
	}

	public void setW_underMinStock(int w_underMinStock) {
		this.w_underMinStock = w_underMinStock;
	}

	public int getW_overMaxStock() {
		return w_overMaxStock;
	}

	public void setW_overMaxStock(int w_overMaxStock) {
		this.w_overMaxStock = w_overMaxStock;
	}

	public int getW_stockInCompletedThisMonth() {
		return w_stockInCompletedThisMonth;
	}

	public void setW_stockInCompletedThisMonth(int w_stockInCompletedThisMonth) {
		this.w_stockInCompletedThisMonth = w_stockInCompletedThisMonth;
	}

	public int getW_watingStockInThisMonth() {
		return w_watingStockInThisMonth;
	}

	public void setW_watingStockInThisMonth(int w_watingStockInThisMonth) {
		this.w_watingStockInThisMonth = w_watingStockInThisMonth;
	}

	public int getW_stockInToday() {
		return w_stockInToday;
	}

	public void setW_stockInToday(int w_stockInToday) {
		this.w_stockInToday = w_stockInToday;
	}

	public int getW_stockInCompletedToday() {
		return w_stockInCompletedToday;
	}

	public void setW_stockInCompletedToday(int w_stockInCompletedToday) {
		this.w_stockInCompletedToday = w_stockInCompletedToday;
	}

	public int getW_stockOutCompletedThisMonth() {
		return w_stockOutCompletedThisMonth;
	}

	public void setW_stockOutCompletedThisMonth(int w_stockOutCompletedThisMonth) {
		this.w_stockOutCompletedThisMonth = w_stockOutCompletedThisMonth;
	}

	public int getW_watingStockOutThisMonth() {
		return w_watingStockOutThisMonth;
	}

	public void setW_watingStockOutThisMonth(int w_watingStockOutThisMonth) {
		this.w_watingStockOutThisMonth = w_watingStockOutThisMonth;
	}

	public int getW_stockOutToday() {
		return w_stockOutToday;
	}

	public void setW_stockOutToday(int w_stockOutToday) {
		this.w_stockOutToday = w_stockOutToday;
	}

	public int getW_stockOutCompletedToday() {
		return w_stockOutCompletedToday;
	}

	public void setW_stockOutCompletedToday(int w_stockOutCompletedToday) {
		this.w_stockOutCompletedToday = w_stockOutCompletedToday;
	}

	@Override
	public String toString() {
		return "WarehouseDeptDTO [w_deptno=" + w_deptno + ", w_dept=" + w_dept + ", w_count=" + w_count
				+ ", w_products=" + w_products + ", w_value=" + w_value + ", w_underMinStock=" + w_underMinStock
				+ ", w_overMaxStock=" + w_overMaxStock + ", w_stockInCompletedThisMonth=" + w_stockInCompletedThisMonth
				+ ", w_watingStockInThisMonth=" + w_watingStockInThisMonth + ", w_stockInToday=" + w_stockInToday
				+ ", w_stockInCompletedToday=" + w_stockInCompletedToday + ", w_stockOutCompletedThisMonth="
				+ w_stockOutCompletedThisMonth + ", w_watingStockOutThisMonth=" + w_watingStockOutThisMonth
				+ ", w_stockOutToday=" + w_stockOutToday + ", w_stockOutCompletedToday=" + w_stockOutCompletedToday
				+ "]";
	}
	
	
	
}
