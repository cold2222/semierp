package com.semi.statistics.contract;

public class MainContractsDTO {
	private int[] importArr;
	private int[] salesArr;
	private int[] importCostArr;
	private int[] salesCostArr;
	private int[] diffCostArr;
	private int importCount = 0;
	private int salesCount = 0;
	private long importCostSum = 0;
	private long salesCostSum = 0;
	
	public int getImportCount() {
		return importCount;
	}
	public void setImportCount(int importCount) {
		this.importCount = importCount;
	}
	public int getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}
	public long getImportCostSum() {
		return importCostSum;
	}
	public void setImportCostSum(long importCostSum) {
		this.importCostSum = importCostSum;
	}
	public int[] getDiffCostArr() {
		return diffCostArr;
	}
	public void setDiffCostArr(int[] diffCostArr) {
		this.diffCostArr = diffCostArr;
	}
	public long getSalesCostSum() {
		return salesCostSum;
	}
	public void setSalesCostSum(long salesCostSum) {
		this.salesCostSum = salesCostSum;
	}
	public int[] getImportArr() {
		return importArr;
	}
	public void setImportArr(int[] importArr) {
		this.importArr = importArr;
	}
	public int[] getSalesArr() {
		return salesArr;
	}
	public void setSalesArr(int[] salesArr) {
		this.salesArr = salesArr;
	}
	public int[] getImportCostArr() {
		return importCostArr;
	}
	public void setImportCostArr(int[] importCostArr) {
		this.importCostArr = importCostArr;
	}
	public int[] getSalesCostArr() {
		return salesCostArr;
	}
	public void setSalesCostArr(int[] salesCostArr) {
		this.salesCostArr = salesCostArr;
	}
	
	
}
