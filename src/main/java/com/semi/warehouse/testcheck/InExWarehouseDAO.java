package com.semi.warehouse.testcheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class InExWarehouseDAO {

	public static void getallWare(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product_test_ksj.p_id,\n"
				+ "    product_test_ksj.p_name,\n"
				+ "    product_test_ksj.p_type,\n"
				+ "    product_test_ksj.p_quantity,\n"
				+ "    product_test_ksj.p_si,\n"
				+ "    CASE\n"
				+ "        WHEN in_warehouse_test.in_warehouse_id IS NOT NULL THEN in_warehouse_test.in_warehouse_quantity\n"
				+ "        WHEN ex_warehouse_test.ex_warehouse_id IS NOT NULL THEN ex_warehouse_test.ex_warehouse_quantity\n"
				+ "        ELSE NULL\n"
				+ "    END AS quantity,\n"
				+ "    CASE\n"
				+ "        WHEN in_warehouse_test.in_warehouse_id IS NOT NULL THEN in_warehouse_test.in_warehouse_date\n"
				+ "        WHEN ex_warehouse_test.ex_warehouse_id IS NOT NULL THEN ex_warehouse_test.ex_warehouse_date\n"
				+ "        ELSE NULL\n"
				+ "    END AS warehouse_date,\n"
				+ "    CASE\n"
				+ "        WHEN in_warehouse_test.in_warehouse_id IS NOT NULL THEN '입고'\n"
				+ "        WHEN ex_warehouse_test.ex_warehouse_id IS NOT NULL THEN '출고'\n"
				+ "        ELSE NULL\n"
				+ "    END AS warehouse_type,\n"
				+ "    CASE\n"
				+ "        WHEN in_warehouse_test.in_warehouse_id IS NOT NULL THEN in_warehouse_test.warehouse_id\n"
				+ "        WHEN ex_warehouse_test.ex_warehouse_id IS NOT NULL THEN ex_warehouse_test.warehouse_id\n"
				+ "        ELSE NULL\n"
				+ "    END AS warehouse_id,\n"
				+ "    CASE\n"
				+ "        WHEN in_warehouse_test.in_warehouse_id IS NOT NULL THEN warehouse_test.warehouse_name\n"
				+ "        WHEN ex_warehouse_test.ex_warehouse_id IS NOT NULL THEN warehouse_test.warehouse_name\n"
				+ "        ELSE NULL\n"
				+ "    END AS warehouse_name\n"
				+ "FROM\n"
				+ "    product_test_ksj\n"
				+ "LEFT JOIN\n"
				+ "    in_warehouse_test ON product_test_ksj.p_id = in_warehouse_test.p_id\n"
				+ "LEFT JOIN\n"
				+ "    ex_warehouse_test ON product_test_ksj.p_id = ex_warehouse_test.p_id\n"
				+ "LEFT JOIN\n"
				+ "    warehouse_test ON COALESCE(in_warehouse_test.warehouse_id, ex_warehouse_test.warehouse_id) = warehouse_test.warehouse_id\n"
				+ "WHERE\n"
				+ "    (in_warehouse_test.in_warehouse_id IS NOT NULL OR ex_warehouse_test.ex_warehouse_id IS NOT NULL)\n"
				+ "ORDER BY\n"
				+ "    warehouse_date DESC";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<allInExDTO> allInExWarehouse = new ArrayList<allInExDTO>();
			allInExDTO all = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_quantity = rs.getInt("p_quantity");
				int quantity = rs.getInt("quantity");
				String warehouse_date = rs.getString("warehouse_date");
				String warehouse_type = rs.getString("warehouse_type");
				int warehouse_id = rs.getInt("warehouse_id");
				String warehouse_name = rs.getString("warehouse_name");
				
				// p_id로 pk
				
				all = new allInExDTO(p_id, p_name, p_si, p_type, p_quantity, quantity, warehouse_date,warehouse_type, warehouse_id, warehouse_name);
				allInExWarehouse.add(all);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(quantity);
				System.out.println(warehouse_date);
				System.out.println(warehouse_type);
				System.out.println(warehouse_id);
				System.out.println(warehouse_name);

			}
			request.setAttribute("allInExWarehouse", allInExWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
			
			
			
	}

	
	
}
