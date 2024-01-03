package com.semi.adminpage.inform;

import java.sql.Date;

public class CompanyInformDTO {
	private int ci_no;
	private String ci_title;
	private String ci_content;
	private Date date;
	private int ci_broadcastIdx;
	private int ci_deptIdxCode;
	private int ci_manageIdx;
	private int ci_importIdx;
	private int ci_salesIdx;
	private int ci_distributionIdx;
	private int ci_warehouseIdx;
	
	public CompanyInformDTO() {
		// TODO Auto-generated constructor stub
	}

	public CompanyInformDTO(int ci_no, String ci_title, String ci_content, Date date, int ci_broadcastIdx,
			int ci_deptIdxCode) {
		super();
		this.ci_no = ci_no;
		this.ci_title = ci_title;
		this.ci_content = ci_content;
		this.date = date;
		this.ci_broadcastIdx = ci_broadcastIdx;
		this.ci_deptIdxCode = ci_deptIdxCode;
	}

	public int getCi_no() {
		return ci_no;
	}

	public void setCi_no(int ci_no) {
		this.ci_no = ci_no;
	}

	public String getCi_title() {
		return ci_title;
	}

	public void setCi_title(String ci_title) {
		this.ci_title = ci_title;
	}

	public String getCi_content() {
		return ci_content;
	}

	public void setCi_content(String ci_content) {
		this.ci_content = ci_content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCi_broadcastIdx() {
		return ci_broadcastIdx;
	}

	public void setCi_broadcastIdx(int ci_broadcastIdx) {
		this.ci_broadcastIdx = ci_broadcastIdx;
	}

	public int getCi_deptIdxCode() {
		return ci_deptIdxCode;
	}

	public void setCi_deptIdxCode(int ci_deptIdxCode) {
		this.ci_deptIdxCode = ci_deptIdxCode;
	}

	@Override
	public String toString() {
		return "CompanyInformDTO [ci_no=" + ci_no + ", ci_title=" + ci_title + ", ci_content=" + ci_content + ", date="
				+ date + ", ci_broadcastIdx=" + ci_broadcastIdx + ", ci_deptIdxCode=" + ci_deptIdxCode + "]";
	}
	

}
