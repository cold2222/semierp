package com.semi.adminpage.staff;

public class WarehouseStaffDTO {
	private int ws_no;
	private String ws_name;
	private String ws_rank;
	private String ws_tel;
	private String ws_email;

	public WarehouseStaffDTO() {
		// TODO Auto-generated constructor stub
	}

	public WarehouseStaffDTO(int ws_no, String ws_name, String ws_rank, String ws_tel, String ws_email) {
		super();
		this.ws_no = ws_no;
		this.ws_name = ws_name;
		this.ws_rank = ws_rank;
		this.ws_tel = ws_tel;
		this.ws_email = ws_email;
	}

	public int getWs_no() {
		return ws_no;
	}

	public void setWs_no(int ws_no) {
		this.ws_no = ws_no;
	}

	public String getWs_name() {
		return ws_name;
	}

	public void setWs_name(String ws_name) {
		this.ws_name = ws_name;
	}

	public String getWs_rank() {
		return ws_rank;
	}

	public void setWs_rank(String ws_rank) {
		this.ws_rank = ws_rank;
	}

	public String getWs_tel() {
		return ws_tel;
	}

	public void setWs_tel(String ws_tel) {
		this.ws_tel = ws_tel;
	}

	public String getWs_email() {
		return ws_email;
	}

	public void setWs_email(String ws_email) {
		this.ws_email = ws_email;
	}

	@Override
	public String toString() {
		return "WarehouseStaffDTO [ws_no=" + ws_no + ", ws_name=" + ws_name + ", ws_rank=" + ws_rank + ", ws_tel="
				+ ws_tel + ", ws_email=" + ws_email + "]";
	}

}
