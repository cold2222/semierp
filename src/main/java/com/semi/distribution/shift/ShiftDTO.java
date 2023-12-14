package com.semi.distribution.shift;

public class ShiftDTO {
	private String e_id;
	private String w_n;
	private String w_date;
	public ShiftDTO() {
	}
	public ShiftDTO(String e_id, String w_n, String w_date) {
		super();
		this.e_id = e_id;
		this.w_n = w_n;
		this.w_date = w_date;
	}
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getW_n() {
		return w_n;
	}
	public void setW_n(String w_n) {
		this.w_n = w_n;
	}
	public String getW_date() {
		return w_date;
	}
	public void setW_date(String w_date) {
		this.w_date = w_date;
	}
}
