package com.semi.adminpage.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.adminpage.util.AdminUtils;
import com.semi.distribution.db.DBManger;

public class AdminProductDAO {
	public static void getWarehouseProductsInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AdminProductDTO> productsInfo = new ArrayList<AdminProductDTO>();
		String sql = "SELECT \r\n"
				+ "    p.p_id, \r\n"
				+ "    p.p_si, \r\n"
				+ "    p.p_type, \r\n"
				+ "    p.p_quantity, \r\n"
				+ "    p.p_name, \r\n"
				+ "    p.p_unitcost,\r\n"
				+ "    NVL(s.s_stock, 0) AS p_stock,\r\n"
				+ "    p.p_minstock, \r\n"
				+ "    p.p_maxstock, \r\n"
				+ "    p.p_manufacturer, \r\n"
				+ "    CASE \r\n"
				+ "        WHEN NVL(s.s_stock, 0) < p.p_minstock THEN 2 \r\n"
				+ "        WHEN NVL(s.s_stock, 0) > p.p_maxstock THEN 1 \r\n"
				+ "        ELSE 0 \r\n"
				+ "    END AS p_check\r\n"
				+ "FROM \r\n"
				+ "    product p\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "    (SELECT p_id AS s_p_id, SUM(rm_stock) AS s_stock FROM stock GROUP BY p_id) s\r\n"
				+ "ON \r\n"
				+ "    p.p_id = s.s_p_id\r\n"
				+ "order by p_check desc, p_id";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AdminProductDTO tempProductDTO = new AdminProductDTO(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getString(10),
						rs.getInt(11));
				
				productsInfo.add(tempProductDTO);
			}

			// 페이징
			request.setAttribute("productsInfo",
					AdminUtils.setPagingWithIndex(request, productsInfo, 5, 2));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

}
