package com.semi.statistics.chart;

import java.util.ArrayList;
import java.util.Arrays;

public class DoughnutChartData {
	private String[] labels;
	private ArrayList<DoughnutChartDatasets> datasets;
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public ArrayList<DoughnutChartDatasets> getDatasets() {
		return datasets;
	}
	public void setDatasets(ArrayList<DoughnutChartDatasets> datasets) {
		this.datasets = datasets;
	}
	@Override
	public String toString() {
		return "DoughnutChartData [labels=" + Arrays.toString(labels) + ", datasets=" + datasets + "]";
	}
	

}
