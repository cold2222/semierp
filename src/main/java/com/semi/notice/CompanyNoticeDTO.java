package com.semi.notice;

import java.sql.Date;

public class CompanyNoticeDTO {
	private int cn_no;
	private String cn_content;
	private Date cn_date;
	private int cn_castIdx;
	private int cn_toCastCode;
	private String cn_url;
	private int ccn_e_no;
	private int ccn_cn_no;
	private int ccn_checked;
	
	public CompanyNoticeDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CompanyNoticeDTO(int cn_no, String cn_content, Date cn_date, int cn_castIdx, int cn_toCastCode,
			String cn_url, int ccn_e_no, int ccn_cn_no, int ccn_checked) {
		super();
		this.cn_no = cn_no;
		this.cn_content = cn_content;
		this.cn_date = cn_date;
		this.cn_castIdx = cn_castIdx;
		this.cn_toCastCode = cn_toCastCode;
		this.cn_url = cn_url;
		this.ccn_e_no = ccn_e_no;
		this.ccn_cn_no = ccn_cn_no;
		this.ccn_checked = ccn_checked;
	}

	

	public int getCn_no() {
		return cn_no;
	}
	public void setCn_no(int cn_no) {
		this.cn_no = cn_no;
	}
	public String getCn_content() {
		return cn_content;
	}
	public void setCn_content(String cn_content) {
		this.cn_content = cn_content;
	}
	public Date getCn_date() {
		return cn_date;
	}
	public void setCn_date(Date cn_date) {
		this.cn_date = cn_date;
	}
	public int getCn_castIdx() {
		return cn_castIdx;
	}
	public void setCn_castIdx(int cn_castIdx) {
		this.cn_castIdx = cn_castIdx;
	}
	public int getCn_toCastCode() {
		return cn_toCastCode;
	}
	public void setCn_toCastCode(int cn_toCastCode) {
		this.cn_toCastCode = cn_toCastCode;
	}
	public String getCn_url() {
		return cn_url;
	}
	public void setCn_url(String cn_url) {
		this.cn_url = cn_url;
	}
	public int getCcn_e_no() {
		return ccn_e_no;
	}
	public void setCcn_e_no(int ccn_e_no) {
		this.ccn_e_no = ccn_e_no;
	}
	public int getCcn_cn_no() {
		return ccn_cn_no;
	}
	public void setCcn_cn_no(int ccn_cn_no) {
		this.ccn_cn_no = ccn_cn_no;
	}
	public int getCcn_checked() {
		return ccn_checked;
	}
	public void setCcn_checked(int ccn_checked) {
		this.ccn_checked = ccn_checked;
	}

	@Override
	public String toString() {
		return "CompanyNoticeDTO [cn_no=" + cn_no + ", cn_content=" + cn_content + ", cn_date=" + cn_date
				+ ", cn_castIdx=" + cn_castIdx + ", cn_toCastCode=" + cn_toCastCode + ", cn_url=" + cn_url
				+ ", ccn_e_no=" + ccn_e_no + ", ccn_cn_no=" + ccn_cn_no + ", ccn_checked=" + ccn_checked + "]";
	}
	
	

}
