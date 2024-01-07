package com.semi.statistics.chart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineChartOfYear {
	private String[] labels = {"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
	private ArrayList<LineChartDatasets> datasets;
	
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public List<LineChartDatasets> getDatasets() {
		return datasets;
	}
	public void setDatasets(ArrayList<LineChartDatasets> datasets) {
		this.datasets = datasets;
	}
	@Override
	public String toString() {
		return "LineChartOfYear [labels=" + Arrays.toString(labels) + ", datasets=" + datasets + "]";
	}	
	
}
