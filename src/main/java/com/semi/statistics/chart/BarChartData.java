package com.semi.statistics.chart;

import java.util.ArrayList;
import java.util.Arrays;

public class BarChartData {
	private String[] labels = {"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
	private ArrayList<BarChartDataSets> datasets;
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public ArrayList<BarChartDataSets> getDatasets() {
		return datasets;
	}
	public void setDatasets(ArrayList<BarChartDataSets> datasets) {
		this.datasets = datasets;
	}
	public BarChartData(String[] labels, ArrayList<BarChartDataSets> datasets) {
		super();
		this.labels = labels;
		this.datasets = datasets;
	}
	public BarChartData() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BarChartData [labels=" + Arrays.toString(labels) + ", datasets=" + datasets + "]";
	}
	
	
	
}
