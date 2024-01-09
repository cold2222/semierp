package com.semi.statistics.distribution;

public class StatisticsDistribuitionDTO {
	private String month;
	private int type1Completed;
	private int type1Delayed;
	private int type2Completed;
	private int type2Delayed;
	
	public StatisticsDistribuitionDTO() {
		// TODO Auto-generated constructor stub
	}

	public StatisticsDistribuitionDTO(String month, int type1Completed, int type1Delayed, int type2Completed,
			int type2Delayed) {
		super();
		this.month = month;
		this.type1Completed = type1Completed;
		this.type1Delayed = type1Delayed;
		this.type2Completed = type2Completed;
		this.type2Delayed = type2Delayed;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getType1Completed() {
		return type1Completed;
	}

	public void setType1Completed(int type1Completed) {
		this.type1Completed = type1Completed;
	}

	public int getType1Delayed() {
		return type1Delayed;
	}

	public void setType1Delayed(int type1Delayed) {
		this.type1Delayed = type1Delayed;
	}

	public int getType2Completed() {
		return type2Completed;
	}

	public void setType2Completed(int type2Completed) {
		this.type2Completed = type2Completed;
	}

	public int getType2Delayed() {
		return type2Delayed;
	}

	public void setType2Delayed(int type2Delayed) {
		this.type2Delayed = type2Delayed;
	}

	@Override
	public String toString() {
		return "StatisticsDistribuitionDTO [month=" + month + ", type1Completed=" + type1Completed + ", type1Delayed="
				+ type1Delayed + ", type2Completed=" + type2Completed + ", type2Delayed=" + type2Delayed + "]";
	}
	
	
	

}
