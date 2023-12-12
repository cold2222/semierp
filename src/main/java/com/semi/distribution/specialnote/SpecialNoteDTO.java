package com.semi.distribution.specialnote;

import java.sql.Date;


public class SpecialNoteDTO {
	private String s_num;
	private String s_title;
	private String s_content;
	private String s_img;
	private Date s_date;
	
	public SpecialNoteDTO() {
		super();
	}

	public SpecialNoteDTO(String s_num, String s_title, String s_content, String s_img, Date s_date) {
		super();
		this.s_num = s_num;
		this.s_title = s_title;
		this.s_content = s_content;
		this.s_img = s_img;
		this.s_date = s_date;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getS_title() {
		return s_title;
	}

	public void setS_title(String s_title) {
		this.s_title = s_title;
	}

	public String getS_content() {
		return s_content;
	}

	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	public String getS_img() {
		return s_img;
	}

	public void setS_img(String s_img) {
		this.s_img = s_img;
	}

	public Date getS_date() {
		return s_date;
	}

	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}

	
	
}
