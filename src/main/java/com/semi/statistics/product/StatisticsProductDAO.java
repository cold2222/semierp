package com.semi.statistics.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.adminpage.util.AdminUtils;
import com.semi.distribution.db.DBManger;
import com.semi.statistics.chart.DoughnutChartData;
import com.semi.statistics.chart.DoughnutChartDatasets;
import com.semi.statistics.chart.LineChartDatasets;
import com.semi.statistics.chart.LineChartOfYear;

public class StatisticsProductDAO {
	public static void getProductsOrderByCountAll(HttpServletRequest request, int c_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StatistcsProductDTO> products = new ArrayList<StatistcsProductDTO>();
		String sql = "select p_name, p_type, p_si, p_quantity, nvl(p_unitcost,0), p_minstock, p_maxstock, p_manufacturer, nvl(sum(ci_count),0), nvl(sum(ci_count * ci_unit_price),0)\r\n"
				+ "from\r\n" + "product\r\n" + "left outer join\r\n"
				+ "(select * from contract join contract_items on c_contract_no = ci_c_contract_no where to_char(c_created_date,'yyyy-mm')=? and c_type= ?)\r\n"
				+ "on p_id = ci_p_id\r\n"
				+ "group by p_name, p_type, p_si, p_quantity, p_unitcost, p_minstock, p_maxstock, p_manufacturer\r\n"
				+ "order by  nvl(sum(ci_count),0) desc";

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, AdminUtils.getParamYearMonth(request));
			pstmt.setInt(2, c_type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StatistcsProductDTO tempProduct = new StatistcsProductDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8),
						rs.getLong(9), rs.getLong(10));

				products.add(tempProduct);
			}
			request.setAttribute("products", AdminUtils.setPaging(request, products, 10));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public static void getProductsChart(HttpServletRequest request, HttpServletResponse response, int c_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DoughnutChartDatasets> productsCount = new ArrayList<DoughnutChartDatasets>();
		ArrayList<DoughnutChartDatasets> productsCost = new ArrayList<DoughnutChartDatasets>();

		long[] proCount1 = new long[7];
		long[] proCost1 = new long[7];
		String[] labels1 = new String[7];
		long[] proCost2 = new long[7];
		long[] proCount2 = new long[7];
		String[] labels2 = new String[7];

		String sql = "select * from\r\n"
				+ "(select p_name, p_type, p_si, p_quantity, p_unitcost, p_minstock, p_maxstock, p_manufacturer, nvl(sum(ci_count),0), nvl(sum(ci_count * ci_unit_price),0)\r\n"
				+ "from\r\n" + "product\r\n" + "left outer join\r\n"
				+ "(select * from contract join contract_items on c_contract_no = ci_c_contract_no where to_char(c_created_date,'yyyy-mm')=? and c_type= ?)\r\n"
				+ "on p_id = ci_p_id\r\n"
				+ "group by p_name, p_type, p_si, p_quantity, p_unitcost, p_minstock, p_maxstock, p_manufacturer\r\n"
				+ "order by nvl(sum(ci_count),0) desc)\r\n" + "where rownum < 8\r\n" + "union all\r\n"
				+ "select * from\r\n"
				+ "(select p_name, p_type, p_si, p_quantity, p_unitcost, p_minstock, p_maxstock, p_manufacturer, nvl(sum(ci_count),0), nvl(sum(ci_count * ci_unit_price),0)\r\n"
				+ "from\r\n" + "product\r\n" + "left outer join\r\n"
				+ "(select * from contract join contract_items on c_contract_no = ci_c_contract_no where to_char(c_created_date,'yyyy-mm')=? and c_type= ?)\r\n"
				+ "on p_id = ci_p_id\r\n"
				+ "group by p_name, p_type, p_si, p_quantity, p_unitcost, p_minstock, p_maxstock, p_manufacturer\r\n"
				+ "order by  nvl(sum(ci_count * ci_unit_price),0) desc)\r\n" + "where rownum < 8";

		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, AdminUtils.getParamYearMonth(request));
			pstmt.setInt(2, c_type);
			pstmt.setString(3, AdminUtils.getParamYearMonth(request));
			pstmt.setInt(4, c_type);
			rs = pstmt.executeQuery();
			int count1 = 0;
			int count2 = 0;
			while (rs.next()) {
				if (count1 < 7) {
					proCount1[count1] = rs.getLong(9);
					proCost1[count1] = rs.getLong(10);
					labels1[count1] = rs.getString(1);
				} else {
					proCount2[count2] = rs.getLong(9);
					proCost2[count2] = rs.getLong(10);
					labels2[count2] = rs.getString(1);
					count2++;
				}

				count1++;
			}

			DoughnutChartDatasets datasets1 = new DoughnutChartDatasets();
			DoughnutChartDatasets datasets2 = new DoughnutChartDatasets();
			datasets1.setLabel("상품 거래량(거래량순)");
			datasets2.setLabel("상품 금액(거래량순)");
			datasets1.setData(proCount1);
			datasets2.setData(proCost1);
			
			DoughnutChartDatasets datasets3 = new DoughnutChartDatasets();
			DoughnutChartDatasets datasets4 = new DoughnutChartDatasets();
			datasets3.setLabel("상품 금액(금액순)");
			datasets4.setLabel("상품 거래량(금액순)");
			datasets3.setData(proCost2);
			datasets4.setData(proCount2);
			
			DoughnutChartData data1 = new DoughnutChartData();
			data1.setLabels(labels1);
			data1.getDatasets().add(datasets1);
			data1.getDatasets().add(datasets2);
			DoughnutChartData data2 = new DoughnutChartData();
			data2.setLabels(labels2);
			data2.getDatasets().add(datasets3);
			data2.getDatasets().add(datasets4);
			
			Gson gson = new Gson();
			String doughnutCountChartJson = gson.toJson(data1);
			String doughnutCostChartDataJson = gson.toJson(data2);

			response.getWriter().print("[" + doughnutCountChartJson + "," + doughnutCostChartDataJson + "]");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

}
