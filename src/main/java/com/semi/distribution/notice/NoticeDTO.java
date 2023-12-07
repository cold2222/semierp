package com.semi.distribution.notice;

import java.sql.Date;

import oracle.sql.DATE;

public class NoticeDTO {
	private String n_num;
	private String n_title;
	private String n_content;
	private String n_img;
	private Date n_date;
	
	public NoticeDTO() {
		super();
	}

	public NoticeDTO(String n_num, String n_title, String n_content, String n_img, Date n_date) {
		super();
		this.n_num = n_num;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_img = n_img;
		this.n_date = n_date;
	}

	public String getN_num() {
		return n_num;
	}

	public void setN_num(String n_num) {
		this.n_num = n_num;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public String getN_img() {
		return n_img;
	}

	public void setN_img(String n_img) {
		this.n_img = n_img;
	}

	public Date getN_date() {
		return n_date;
	}

	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}
	
}
