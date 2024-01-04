package com.semi.adminpage.inform;

import java.sql.Date;

public class CompanyInformDTO {
	private int ci_no;
	private String ci_title;
	private String ci_content;
	private Date ci_date;
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

	public CompanyInformDTO(int ci_no, String ci_title, String ci_content, Date ci_date, int ci_broadcastIdx,
			int ci_deptIdxCode) {
		super();
		this.ci_no = ci_no;
		this.ci_title = ci_title;
		this.ci_content = ci_content;
		this.ci_date = ci_date;
		this.ci_broadcastIdx = ci_broadcastIdx;
		this.ci_deptIdxCode = ci_deptIdxCode;
		setCi_manageIdx(ci_deptIdxCode % 10);
		ci_deptIdxCode /= 10;
		setCi_importIdx(ci_deptIdxCode % 10);
		ci_deptIdxCode /= 10;
		setCi_salesIdx(ci_deptIdxCode % 10);
		ci_deptIdxCode /= 10;
		setCi_distributionIdx(ci_deptIdxCode % 10);
		ci_deptIdxCode /= 10;
		setCi_warehouseIdx(ci_deptIdxCode);
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

	public Date getCi_date() {
		return ci_date;
	}

	public void setCi_date(Date ci_date) {
		this.ci_date = ci_date;
	}

	public int getCi_manageIdx() {
		return ci_manageIdx;
	}

	public void setCi_manageIdx(int ci_manageIdx) {
		this.ci_manageIdx = ci_manageIdx;
	}

	public int getCi_importIdx() {
		return ci_importIdx;
	}

	public void setCi_importIdx(int ci_importIdx) {
		this.ci_importIdx = ci_importIdx;
	}

	public int getCi_salesIdx() {
		return ci_salesIdx;
	}

	public void setCi_salesIdx(int ci_salesIdx) {
		this.ci_salesIdx = ci_salesIdx;
	}

	public int getCi_distributionIdx() {
		return ci_distributionIdx;
	}

	public void setCi_distributionIdx(int ci_distributionIdx) {
		this.ci_distributionIdx = ci_distributionIdx;
	}

	public int getCi_warehouseIdx() {
		return ci_warehouseIdx;
	}

	public void setCi_warehouseIdx(int ci_warehouseIdx) {
		this.ci_warehouseIdx = ci_warehouseIdx;
	}

	@Override
	public String toString() {
		return "CompanyInformDTO [ci_no=" + ci_no + ", ci_title=" + ci_title + ", ci_content=" + ci_content
				+ ", ci_date=" + ci_date + ", ci_broadcastIdx=" + ci_broadcastIdx + ", ci_deptIdxCode=" + ci_deptIdxCode
				+ ", ci_manageIdx=" + ci_manageIdx + ", ci_importIdx=" + ci_importIdx + ", ci_salesIdx=" + ci_salesIdx
				+ ", ci_distributionIdx=" + ci_distributionIdx + ", ci_warehouseIdx=" + ci_warehouseIdx + "]";
	}

	

}
