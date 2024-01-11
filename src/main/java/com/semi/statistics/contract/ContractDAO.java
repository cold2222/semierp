package com.semi.statistics.contract;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.adminpage.util.AdminUtils;
import com.semi.distribution.db.DBManger;
import com.semi.statistics.chart.LineChartDatasets;
import com.semi.statistics.chart.LineChartOfYear;

public class ContractDAO {

	public static void getContractsInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MainContractsDTO contracts = new MainContractsDTO();
		int[] importCountArr = new int[12];
		int[] salesCountArr = new int[12];
		int[] importCostArr = new int[12];
		int[] salesCostArr = new int[12];
		int[] diffCostArr = new int[12];
		int importCount = 0;
		int salesCount = 0;
		long sumImportCost = 0;
		long sumSalesCost = 0;
		String sql = "select to_char(c_created_date,'yyyy-mm'), count(distinct c_contract_no), sum(ci_count * ci_unit_price), c_type\r\n"
				+ "from contract \r\n" + "join contract_items\r\n" + "on c_contract_no = ci_c_contract_no\r\n"
				+ "where to_char(c_created_date,'yyyy') = ?\r\n"
				+ "group by to_char(c_created_date,'yyyy-mm'), c_type\r\n"
				+ "order by c_type ,to_char(c_created_date,'yyyy-mm')";

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, AdminUtils.getParamYear(request));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("c_type") == 1) {
					importCountArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					importCostArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
					diffCostArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] -= rs.getInt(3);

				} else {
					salesCountArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					salesCostArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
					diffCostArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] += rs.getInt(3);
				}
			}

			for (int i = 0; i < 12; i++) {
				importCount += importCountArr[i];
				salesCount += salesCountArr[i];
				sumImportCost += importCostArr[i];
				sumSalesCost += salesCostArr[i];
			}

			contracts.setImportCount(importCount);
			contracts.setSalesCount(salesCount);
			contracts.setImportCostSum(sumImportCost);
			contracts.setSalesCostSum(sumSalesCost);
			contracts.setImportArr(importCountArr);
			contracts.setImportCostArr(importCostArr);
			contracts.setSalesArr(salesCountArr);
			contracts.setSalesCostArr(salesCostArr);
			contracts.setDiffCostArr(diffCostArr);

			request.setAttribute("sumDif", (sumSalesCost - sumImportCost) / 1000);
			request.setAttribute("contractsInfo", contracts);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public static void getContractChart(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LineChartDatasets> contractsCount = new ArrayList<LineChartDatasets>();
		ArrayList<LineChartDatasets> contractsCost = new ArrayList<LineChartDatasets>();

		int[] importCountArr = new int[12];
		int[] salesCountArr = new int[12];
		int[] importCostArr = new int[12];
		int[] salesCostArr = new int[12];

		String sql = "select to_char(c_created_date,'yyyy-mm'), count(distinct c_contract_no), sum(ci_count * ci_unit_price), c_type\r\n"
				+ "from contract \r\n" + "join contract_items\r\n" + "on c_contract_no = ci_c_contract_no\r\n"
				+ "where to_char(c_created_date,'yyyy') = ?\r\n"
				+ "group by to_char(c_created_date,'yyyy-mm'), c_type\r\n"
				+ "order by c_type ,to_char(c_created_date,'yyyy-mm')";

		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, AdminUtils.getParamYear(request));
			System.out.println(AdminUtils.getParamYear(request));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("c_type") == 1) {
					importCountArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					importCostArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
				} else {
					salesCountArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					salesCostArr[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
				}
			}

			contractsCount.add(new LineChartDatasets("輸入契約件数(月)", importCountArr, "#f9c00c"));
			contractsCount.add(new LineChartDatasets("販売契約件数(月)", salesCountArr, "#00b9f1"));
			contractsCost.add(new LineChartDatasets("輸入金額(月)", importCostArr, "#f9c00c"));
			contractsCost.add(new LineChartDatasets("販売金額(月)", salesCostArr, "#00b9f1"));

			LineChartOfYear contractLineChartOfYear = new LineChartOfYear();
			contractLineChartOfYear.setDatasets(contractsCount);
			LineChartOfYear contractCostLineChartOfYear = new LineChartOfYear();
			contractCostLineChartOfYear.setDatasets(contractsCost);

			Gson gson = new Gson();
			String contractLineChartJson = gson.toJson(contractLineChartOfYear);
			String contractCostLineChartJson = gson.toJson(contractCostLineChartOfYear);

			response.getWriter().print("[" + contractLineChartJson + "," + contractCostLineChartJson + "]");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public static void getContractChartByType(HttpServletRequest request, HttpServletResponse response, int c_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LineChartDatasets> contractsCount = new ArrayList<LineChartDatasets>();
		ArrayList<LineChartDatasets> contractsCost = new ArrayList<LineChartDatasets>();

		int[] contractCountArr1 = new int[12];
		int[] contractCountArr2 = new int[12];
		int[] contractCountArr3 = new int[12];
		int[] contractCostArr1 = new int[12];
		int[] contractCostArr2 = new int[12];
		int[] contractCostArr3 = new int[12];
		int selectedYear = Integer.parseInt(AdminUtils.getParamYear(request));

		String sql = "select to_char(c_created_date,'yyyy-mm'), count(distinct c_contract_no), sum(ci_count * ci_unit_price), c_type\r\n"
				+ "from contract \r\n" + "join contract_items\r\n" + "on c_contract_no = ci_c_contract_no\r\n"
				+ "where c_type = ?\r\n" + "group by to_char(c_created_date,'yyyy-mm'), c_type\r\n"
				+ "order by c_type ,to_char(c_created_date,'yyyy-mm')";

		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_type);
			System.out.println(AdminUtils.getParamYear(request));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (Integer.parseInt(rs.getString(1).split("-")[0]) == selectedYear) {
					contractCountArr1[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					contractCostArr1[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);

				} else if (Integer.parseInt(rs.getString(1).split("-")[0]) == selectedYear - 1) {
					contractCountArr2[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					contractCostArr2[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
				} else if (Integer.parseInt(rs.getString(1).split("-")[0]) == selectedYear - 2) {
					contractCountArr3[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					contractCostArr3[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);

				}
			}

			contractsCount.add(new LineChartDatasets(selectedYear + "年度", contractCountArr1, "#333300"));
			contractsCount.add(new LineChartDatasets((selectedYear - 1) + "年度", contractCountArr2, "#999900"));
			contractsCount.add(new LineChartDatasets((selectedYear - 2) + "年度", contractCountArr3, "#FFFF99"));
			contractsCost.add(new LineChartDatasets(selectedYear + "年度", contractCostArr1, "#002D62"));
			contractsCost.add(new LineChartDatasets((selectedYear - 1) + "年度", contractCostArr2, "#0066b2"));
			contractsCost.add(new LineChartDatasets((selectedYear - 2) + "年度", contractCostArr3, "#89CFF0"));

			LineChartOfYear contractLineChartOfYear = new LineChartOfYear();
			contractLineChartOfYear.setDatasets(contractsCount);
			LineChartOfYear contractCostLineChartOfYear = new LineChartOfYear();
			contractCostLineChartOfYear.setDatasets(contractsCost);

			Gson gson = new Gson();
			String contractLineChartJson = gson.toJson(contractLineChartOfYear);
			String contractCostLineChartJson = gson.toJson(contractCostLineChartOfYear);

			response.getWriter().print("[" + contractLineChartJson + "," + contractCostLineChartJson + "]");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public static void getContractsInfoByType(HttpServletRequest request, int c_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int selectedYear = Integer.parseInt(AdminUtils.getParamYear(request));
		int[] contractCountArr1 = new int[12];
		int[] contractCountArr2 = new int[12];
		int[] contractCountArr3 = new int[12];
		int[] contractCostArr1 = new int[12];
		int[] contractCostArr2 = new int[12];
		int[] contractCostArr3 = new int[12];
		int[] diffCostArr1 = new int[12];
		int[] diffCostArr2 = new int[12];
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		long cost1 = 0;
		long cost2 = 0;
		long cost3 = 0;
		long difCos1 = 0;
		long difCos2 = 0;

		String sql = "select to_char(c_created_date,'yyyy-mm'), count(distinct c_contract_no), sum(ci_count * ci_unit_price), c_type\r\n"
				+ "from contract \r\n" + "join contract_items\r\n" + "on c_contract_no = ci_c_contract_no\r\n"
				+ "where c_type = ?\r\n" + "group by to_char(c_created_date,'yyyy-mm'), c_type\r\n"
				+ "order by c_type ,to_char(c_created_date,'yyyy-mm')";

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_type);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				if (Integer.parseInt(rs.getString(1).split("-")[0]) == selectedYear) {
					contractCountArr1[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					contractCostArr1[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
					diffCostArr1[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] += rs.getInt(3);
					diffCostArr2[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] += rs.getInt(3);

				} else if (Integer.parseInt(rs.getString(1).split("-")[0]) == selectedYear - 1) {
					contractCountArr2[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					contractCostArr2[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
					diffCostArr1[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] -= rs.getInt(3);
				} else if (Integer.parseInt(rs.getString(1).split("-")[0]) == selectedYear - 2) {
					contractCountArr3[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(2);
					contractCostArr3[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] = rs.getInt(3);
					diffCostArr2[Integer.parseInt(rs.getString(1).split("-")[1]) - 1] -= rs.getInt(3);

				}

			}

			for (int i = 0; i < 12; i++) {
				count1 += contractCountArr1[i];
				count2 += contractCountArr2[i];
				count3 += contractCountArr3[i];
				cost1 += contractCostArr1[i];
				cost2 += contractCostArr2[i];
				cost3 += contractCostArr3[i];
				difCos1 += diffCostArr1[i];
				difCos2 += diffCostArr2[i];
			}

			request.setAttribute("count1", count1);
			request.setAttribute("count2", count2);
			request.setAttribute("count3", count3);

			request.setAttribute("cost1", cost1);
			request.setAttribute("cost2", cost2);
			request.setAttribute("cost3", cost3);

			request.setAttribute("contractCostArr1", contractCostArr1);
			request.setAttribute("contractCostArr2", contractCostArr2);
			request.setAttribute("contractCostArr3", contractCostArr3);

			request.setAttribute("contractCountArr1", contractCountArr1);
			request.setAttribute("contractCountArr2", contractCountArr2);
			request.setAttribute("contractCountArr3", contractCountArr3);

			request.setAttribute("diffCostArr1", diffCostArr1);
			request.setAttribute("diffCostArr2", diffCostArr2);
			request.setAttribute("difCos1", difCos1);
			request.setAttribute("difCos2", difCos2);

			request.setAttribute("selectedYear", selectedYear);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

}
