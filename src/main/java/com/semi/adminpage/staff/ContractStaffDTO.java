package com.semi.adminpage.staff;

public class ContractStaffDTO extends StaffDTO{
	private int cs_no;
	private String cs_name;
	private String cs_rank;
	private String cs_tel;
	private String cs_email;
	private int cs_thisMonthContract;
	private int cs_thisMonthProduct;
	private int cs_thisMonthTotalPrice;
	
	public ContractStaffDTO() {
	}

	public ContractStaffDTO(int cs_no, String cs_name, String cs_rank, String cs_tel, String cs_email,
			int cs_thisMonthContract, int cs_thisMonthProduct, int cs_thisMonthTotalPrice) {
		super();
		this.cs_no = cs_no;
		this.cs_name = cs_name;
		this.cs_rank = cs_rank;
		this.cs_tel = cs_tel;
		this.cs_email = cs_email;
		this.cs_thisMonthContract = cs_thisMonthContract;
		this.cs_thisMonthProduct = cs_thisMonthProduct;
		this.cs_thisMonthTotalPrice = cs_thisMonthTotalPrice;
	}

	public int getCs_no() {
		return cs_no;
	}

	public void setCs_no(int cs_no) {
		this.cs_no = cs_no;
	}

	public String getCs_name() {
		return cs_name;
	}

	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
	}

	public String getCs_rank() {
		return cs_rank;
	}

	public void setCs_rank(String cs_rank) {
		this.cs_rank = cs_rank;
	}

	public String getCs_tel() {
		return cs_tel;
	}

	public void setCs_tel(String cs_tel) {
		this.cs_tel = cs_tel;
	}

	public String getCs_email() {
		return cs_email;
	}

	public void setCs_email(String cs_email) {
		this.cs_email = cs_email;
	}

	public int getCs_thisMonthContract() {
		return cs_thisMonthContract;
	}

	public void setCs_thisMonthContract(int cs_thisMonthContract) {
		this.cs_thisMonthContract = cs_thisMonthContract;
	}

	public int getCs_thisMonthProduct() {
		return cs_thisMonthProduct;
	}

	public void setCs_thisMonthProduct(int cs_thisMonthProduct) {
		this.cs_thisMonthProduct = cs_thisMonthProduct;
	}

	public int getCs_thisMonthTotalPrice() {
		return cs_thisMonthTotalPrice;
	}

	public void setCs_thisMonthTotalPrice(int cs_thisMonthTotalPrice) {
		this.cs_thisMonthTotalPrice = cs_thisMonthTotalPrice;
	}

	@Override
	public String toString() {
		return "ContractStaffDTO [cs_no=" + cs_no + ", cs_name=" + cs_name + ", cs_rank=" + cs_rank + ", cs_tel="
				+ cs_tel + ", cs_email=" + cs_email + ", cs_thisMonthContract=" + cs_thisMonthContract
				+ ", cs_thisMonthProduct=" + cs_thisMonthProduct + ", cs_thisMonthTotalPrice=" + cs_thisMonthTotalPrice
				+ "]";
	}
	
	
	
	

}
