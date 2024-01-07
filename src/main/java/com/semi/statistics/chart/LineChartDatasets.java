package com.semi.statistics.chart;

import java.util.Arrays;

public class LineChartDatasets {
	private String label;
	private int[] data;
	private boolean fill = false;
	private String borderColor;
	private float tension = 0.1f;
	
	public LineChartDatasets() {
		// TODO Auto-generated constructor stub
	}
	
	
	public LineChartDatasets(String label, int[] data, String borderColor) {
		super();
		this.label = label;
		this.data = data;
		this.borderColor = borderColor;
	}


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
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public float getTension() {
		return tension;
	}
	public void setTension(float tension) {
		this.tension = tension;
	}


	@Override
	public String toString() {
		return "LineChartDatasets [label=" + label + ", data=" + Arrays.toString(data) + ", fill=" + fill
				+ ", borderColor=" + borderColor + ", tension=" + tension + "]";
	}
	
	
	
	
}
