package com.semi.adminpage.staff;

public class DistributionStaffDTO {
	private int ds_no;
	private String ds_name;
	private String ds_rank;
	private String ds_tel;
	private String ds_email;
	private int ds_shippingThisMonth;
	private int ds_completedThisMonth;
	private int ds_shippingToday;
	private int ds_completedToday;
	
	public DistributionStaffDTO() {
		// TODO Auto-generated constructor stub
	}

	public DistributionStaffDTO(int ds_no, String ds_name, String ds_rank, String ds_tel, String ds_email,
			int ds_shippingThisMonth, int ds_completedThisMonth, int ds_shippingToday, int ds_completedToday) {
		super();
		this.ds_no = ds_no;
		this.ds_name = ds_name;
		this.ds_rank = ds_rank;
		this.ds_tel = ds_tel;
		this.ds_email = ds_email;
		this.ds_shippingThisMonth = ds_shippingThisMonth;
		this.ds_completedThisMonth = ds_completedThisMonth;
		this.ds_shippingToday = ds_shippingToday;
		this.ds_completedToday = ds_completedToday;
	}

	public int getDs_no() {
		return ds_no;
	}

	public void setDs_no(int ds_no) {
		this.ds_no = ds_no;
	}

	public String getDs_name() {
		return ds_name;
	}

	public void setDs_name(String ds_name) {
		this.ds_name = ds_name;
	}

	public String getDs_rank() {
		return ds_rank;
	}

	public void setDs_rank(String ds_rank) {
		this.ds_rank = ds_rank;
	}

	public String getDs_tel() {
		return ds_tel;
	}

	public void setDs_tel(String ds_tel) {
		this.ds_tel = ds_tel;
	}

	public String getDs_email() {
		return ds_email;
	}

	public void setDs_email(String ds_email) {
		this.ds_email = ds_email;
	}

	public int getDs_shippingThisMonth() {
		return ds_shippingThisMonth;
	}

	public void setDs_shippingThisMonth(int ds_shippingThisMonth) {
		this.ds_shippingThisMonth = ds_shippingThisMonth;
	}

	public int getDs_completedThisMonth() {
		return ds_completedThisMonth;
	}

	public void setDs_completedThisMonth(int ds_completedThisMonth) {
		this.ds_completedThisMonth = ds_completedThisMonth;
	}

	public int getDs_shippingToday() {
		return ds_shippingToday;
	}

	public void setDs_shippingToday(int ds_shippingToday) {
		this.ds_shippingToday = ds_shippingToday;
	}

	public int getDs_completedToday() {
		return ds_completedToday;
	}

	public void setDs_completedToday(int ds_completedToday) {
		this.ds_completedToday = ds_completedToday;
	}

	@Override
	public String toString() {
		return "DistributionStaffDTO [ds_no=" + ds_no + ", ds_name=" + ds_name + ", ds_rank=" + ds_rank + ", ds_tel="
				+ ds_tel + ", ds_email=" + ds_email + ", ds_shippingThisMonth=" + ds_shippingThisMonth
				+ ", ds_completedThisMonth=" + ds_completedThisMonth + ", ds_shippingToday=" + ds_shippingToday
				+ ", ds_completedToday=" + ds_completedToday + "]";
	}
	
	
}
