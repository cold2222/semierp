package com.semi.statistics.chart;

import java.util.Arrays;

public class DoughnutChartDatasets {
	private String label;
	private long[] data;
	private String[] backgroundColor = { 
			"rgba(255, 99, 132, 0.2)",
		      "rgba(255, 159, 64, 0.2)",
		      "rgba(255, 205, 86, 0.2)",
		      "rgba(75, 192, 192, 0.2)",
		      "rgba(54, 162, 235, 0.2)",
		      "rgba(153, 102, 255, 0.2)",
		      "rgba(201, 203, 207, 0.2)"};
	private int hoverOffset = 10;
	public int getHoverOffset() {
		return hoverOffset;
	}
	public void setHoverOffset(int hoverOffset) {
		this.hoverOffset = hoverOffset;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public long[] getData() {
		return data;
	}
	public void setData(long[] data) {
		this.data = data;
	}
	public String[] getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String[] backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	@Override
	public String toString() {
		return "DoughnutChartDatasets [label=" + label + ", data=" + Arrays.toString(data) + ", backgroundColor="
				+ Arrays.toString(backgroundColor) + ", hoverOffset=" + hoverOffset + "]";
	}
	
}


