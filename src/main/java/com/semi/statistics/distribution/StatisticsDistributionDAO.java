package com.semi.statistics.distribution;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.adminpage.util.AdminUtils;
import com.semi.distribution.db.DBManger;
import com.semi.statistics.chart.BarChartData;
import com.semi.statistics.chart.BarChartDataSets;

public class StatisticsDistributionDAO {
	public static void getDistributionChart(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BarChartDataSets> datasets = new ArrayList<BarChartDataSets>();
		int[][] dataArrs = new int[6][12];
		String[] label = {"계약 운송 정상건", "수입 운송 정상건", "판매 운송 정상건", "계약 운송 지연건", "수입 운송 지연건", "판매 운송 지연건"};
		String[][] color = {{"rgb(255, 99, 132)","rgb(255, 99, 132)"},{"rgb(255, 205, 86)","rgb(255, 205, 86)"},{"rgb(75, 192, 192)", "rgb(75, 192, 192)"},
							{"rgb(255, 99, 132)","rgba(255, 99, 132, 0.2)"}, {"rgb(255, 205, 86)","rgba(255, 205, 86, 0.2)"},{"rgb(75, 192, 192)", "rgba(75, 192, 192, 0.2)"}};
		
		String sql = "WITH months AS (\r\n"
				+ "    SELECT to_char(add_months(to_date(?, 'YYYY-MM-DD'), level - 1), 'YYYY-MM') AS month\r\n"
				+ "    FROM dual\r\n"
				+ "    CONNECT BY level <= 12\r\n"
				+ ")\r\n"
				+ "SELECT\r\n"
				+ "    m.month,\r\n"
				+ "    COALESCE(c1.count, 0) AS type1_completed,\r\n"
				+ "    COALESCE(c2.count, 0) AS type1_not_completed,\r\n"
				+ "    COALESCE(c3.count, 0) AS type2_completed,\r\n"
				+ "    COALESCE(c4.count, 0) AS type2_not_completed\r\n"
				+ "FROM\r\n"
				+ "    months m\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 1\r\n"
				+ "        AND (c_completed_date >= c_delivery_date OR c_completed_date IS NULL)\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c1 ON m.month = c1.month\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 1\r\n"
				+ "        AND c_completed_date < c_delivery_date\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c2 ON m.month = c2.month\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 2\r\n"
				+ "        AND (c_completed_date <= c_due_date OR c_completed_date IS NULL)\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c3 ON m.month = c3.month\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 2\r\n"
				+ "        AND c_completed_date > c_due_date\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c4 ON m.month = c4.month\r\n"
				+ "ORDER BY m.month"; 
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			int paramYear = Integer.parseInt(AdminUtils.getParamYear(request));
			pstmt.setString(1, paramYear+"-01-01");
			pstmt.setInt(2, paramYear);
			pstmt.setInt(3, paramYear);
			pstmt.setInt(4, paramYear);
			pstmt.setInt(5, paramYear);
			rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				dataArrs[0][count] = rs.getInt(2) + rs.getInt(4);
				dataArrs[1][count] = rs.getInt(2);
				dataArrs[2][count] = rs.getInt(4);
				dataArrs[3][count] = rs.getInt(3) + rs.getInt(5);
				dataArrs[4][count] = rs.getInt(3);
				dataArrs[5][count] = rs.getInt(5);
				count++;
			}
			for(int i = 0; i< 6; i++) {
				BarChartDataSets tempDataSets = new BarChartDataSets();
				tempDataSets.setBorderColor(color[i][0], 12);
				tempDataSets.setBackgroundColor(color[i][1], 12);
				tempDataSets.setData(dataArrs[i]);
				tempDataSets.setLabel(label[i]);
				datasets.add(tempDataSets);
			}
			BarChartData tempData = new BarChartData();
			tempData.setDatasets(datasets);
			
			Gson gson = new Gson();
			String data = gson.toJson(tempData);
			System.out.println(gson.toJson("buchu.png"));
			response.getWriter().print("["+data+"]");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "DBFail");

		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
	public static void getDistributionStatistic(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StatisticsDistribuitionDTO> dtoArr = new ArrayList<StatisticsDistribuitionDTO>();
		int sums[] = new int[6];
		String sql = "WITH months AS (\r\n"
				+ "    SELECT to_char(add_months(to_date(?, 'YYYY-MM-DD'), level - 1), 'YYYY-MM') AS month\r\n"
				+ "    FROM dual\r\n"
				+ "    CONNECT BY level <= 12\r\n"
				+ ")\r\n"
				+ "SELECT\r\n"
				+ "    m.month,\r\n"
				+ "    COALESCE(c1.count, 0) AS type1_completed,\r\n"
				+ "    COALESCE(c2.count, 0) AS type1_not_completed,\r\n"
				+ "    COALESCE(c3.count, 0) AS type2_completed,\r\n"
				+ "    COALESCE(c4.count, 0) AS type2_not_completed\r\n"
				+ "FROM\r\n"
				+ "    months m\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 1\r\n"
				+ "        AND (c_completed_date >= c_delivery_date OR c_completed_date IS NULL)\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c1 ON m.month = c1.month\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 1\r\n"
				+ "        AND c_completed_date < c_delivery_date\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c2 ON m.month = c2.month\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 2\r\n"
				+ "        AND (c_completed_date <= c_due_date OR c_completed_date IS NULL)\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c3 ON m.month = c3.month\r\n"
				+ "LEFT JOIN (\r\n"
				+ "    SELECT to_char(c_created_date, 'yyyy-mm') AS month, count(*) AS count\r\n"
				+ "    FROM contract\r\n"
				+ "    WHERE EXTRACT(YEAR FROM c_created_date) = ?\r\n"
				+ "        AND c_status = 4\r\n"
				+ "        AND c_type = 2\r\n"
				+ "        AND c_completed_date > c_due_date\r\n"
				+ "    GROUP BY to_char(c_created_date, 'yyyy-mm')\r\n"
				+ ") c4 ON m.month = c4.month\r\n"
				+ "ORDER BY m.month"; 
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			int paramYear = Integer.parseInt(AdminUtils.getParamYear(request));
			pstmt.setString(1, paramYear+"-01-01");
			pstmt.setInt(2, paramYear);
			pstmt.setInt(3, paramYear);
			pstmt.setInt(4, paramYear);
			pstmt.setInt(5, paramYear);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StatisticsDistribuitionDTO tempDTO = new StatisticsDistribuitionDTO();
				tempDTO.setMonth(rs.getString(1));
				tempDTO.setType1Completed(rs.getInt(2));
				tempDTO.setType1Delayed(rs.getInt(3));
				tempDTO.setType2Completed(rs.getInt(4));
				tempDTO.setType2Delayed(rs.getInt(5));
				dtoArr.add(tempDTO);
				sums[0] += rs.getInt(2) + rs.getInt(4);
				sums[1] += rs.getInt(3) + rs.getInt(5);
				sums[2] += rs.getInt(2);
				sums[3] += rs.getInt(3);
				sums[4] += rs.getInt(4);
				sums[5] += rs.getInt(5);
				
			}
			
			request.setAttribute("distributionStatistic", dtoArr);
			request.setAttribute("sums", sums);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "DBFail");

		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

}
