package com.semi.statistics.chart;

import java.util.Arrays;

public class BarChartDataSets {
	private String label;
	private int[] data;
	private String[] backgroundColor;
	private String[] borderColor;
	private int borderWidth = 2;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int[] getData() {
		return data;
	}
	public void setData(int[] data) {
		this.data = data;
	}
	public String[] getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String[] backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor, int size) {
		String[] arr = new String[size];
		Arrays.fill(arr, backgroundColor);
		this.backgroundColor = arr;
	}
	public String[] getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String[] borderColor) {
		this.borderColor = borderColor;
	}
	public void setBorderColor(String borderColor, int size) {
		String[] arr = new String[size];
		Arrays.fill(arr, borderColor);
		this.borderColor = arr;
	}
	public int getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	@Override
	public String toString() {
		return "BarChartDataSets [label=" + label + ", data=" + Arrays.toString(data) + ", backgroundColor="
				+ Arrays.toString(backgroundColor) + ", borderColor=" + Arrays.toString(borderColor) + ", borderWidth="
				+ borderWidth + "]";
	}
	
	
}
