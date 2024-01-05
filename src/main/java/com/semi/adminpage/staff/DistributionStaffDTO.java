package com.semi.adminpage.staff;

public class DistributionStaffDTO {
	private int d_no;
	private String d_name;
	private String d_rank;
	private String d_tel;
	private String d_email;
	private int d_shippingThisMonth;
	private int d_completedThisMonth;
	private int d_shippingToday;
	private int d_completedToday;
	
	public DistributionStaffDTO() {
		// TODO Auto-generated constructor stub
	}

	public DistributionStaffDTO(int d_no, String d_name, String d_rank, String d_tel, String d_email,
			int d_shippingThisMonth, int d_completedThisMonth, int d_shippingToday, int d_completedToday) {
		super();
		this.d_no = d_no;
		this.d_name = d_name;
		this.d_rank = d_rank;
		this.d_tel = d_tel;
		this.d_email = d_email;
		this.d_shippingThisMonth = d_shippingThisMonth;
		this.d_completedThisMonth = d_completedThisMonth;
		this.d_shippingToday = d_shippingToday;
		this.d_completedToday = d_completedToday;
	}

	public int getD_no() {
		return d_no;
	}

	public void setD_no(int d_no) {
		this.d_no = d_no;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getD_rank() {
		return d_rank;
	}

	public void setD_rank(String d_rank) {
		this.d_rank = d_rank;
	}

	public String getD_tel() {
		return d_tel;
	}

	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}

	public String getD_email() {
		return d_email;
	}

	public void setD_email(String d_email) {
		this.d_email = d_email;
	}

	public int getD_shippingThisMonth() {
		return d_shippingThisMonth;
	}

	public void setD_shippingThisMonth(int d_shippingThisMonth) {
		this.d_shippingThisMonth = d_shippingThisMonth;
	}

	public int getD_completedThisMonth() {
		return d_completedThisMonth;
	}

	public void setD_completedThisMonth(int d_completedThisMonth) {
		this.d_completedThisMonth = d_completedThisMonth;
	}

	public int getD_shippingToday() {
		return d_shippingToday;
	}

	public void setD_shippingToday(int d_shippingToday) {
		this.d_shippingToday = d_shippingToday;
	}

	public int getD_completedToday() {
		return d_completedToday;
	}

	public void setD_completedToday(int d_completedToday) {
		this.d_completedToday = d_completedToday;
	}
	
	
}
