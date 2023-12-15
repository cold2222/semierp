package com.semi.warehouse.warehouseboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


public class WarehouseBoardTestDAO {

	public static void getWBTest(HttpServletRequest request, String operationType) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
	            + "    product_test_ksj.p_id,\n"
	            + "    product_test_ksj.p_name,\n"
	            + "    product_test_ksj.p_quantity,\n"
	            + "    product_test_ksj.p_si,\n"
	            + "    product_test_ksj.p_type,\n"
	            + "    warehouse_test.warehouse_name,\n"
	            + "    manufacture_test.manufacture_name,\n"
	            + "    stock_test.stock,\n"
	            + "    sysdate AS today_date\n"
	            + "FROM\n"
	            + "    product_test_ksj\n"
	            + "JOIN\n"
	            + "    stock_test ON product_test_ksj.p_id = stock_test.p_id\n"
	            + "JOIN\n"
	            + "    warehouse_test ON stock_test.warehouse_id = warehouse_test.warehouse_id\n"
	            + "JOIN\n"
	            + "    manufacture_test ON product_test_ksj.p_manufacturer = manufacture_test.p_manufacturer\n";
		
			
		if ("one".equals(operationType)) {
            sql += " WHERE warehouse_test.warehouse_id = 1 "
            		+ "ORDER BY\n"
            		+ "    product_test_ksj.p_type ASC,\n"
            		+ "    product_test_ksj.p_name ASC,\n"
            		+ "    product_test_ksj.p_quantity ASC";
        } else if ("two".equals(operationType)) {
            sql += " WHERE warehouse_test.warehouse_id = 2"
            		+ "ORDER BY\n"
            		+ "    product_test_ksj.p_type ASC,\n"
            		+ "    product_test_ksj.p_name ASC,\n"
            		+ "    product_test_ksj.p_quantity ASC";
        } else if ("three".equals(operationType)) {
            sql += " WHERE warehouse_test.warehouse_id = 3"
            		+ "ORDER BY\n"
            		+ "    product_test_ksj.p_type ASC,\n"
                                   		+ "    product_test_ksj.p_name ASC,\n"
            		+ "    product_test_ksj.p_quantity ASC";
        } else {
			sql += "ORDER BY\n"
					+ "    product_test_ksj.p_type ASC,\n"
					+ "    product_test_ksj.p_name ASC,\n"
					+ "    product_test_ksj.p_quantity ASC";
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<WarehouseBoardTestDTO> warehouseBoard = new ArrayList<WarehouseBoardTestDTO>();
			WarehouseBoardTestDTO wb = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				int p_quantity = rs.getInt("p_quantity");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				String warehouse_name = rs.getString("warehouse_name");
				String manufacture_name = rs.getString("manufacture_name");
				int stock = rs.getInt("stock");
				String today_date = rs.getString("today_date");
				
				// p_id로 pk
				
				wb = new WarehouseBoardTestDTO(p_id, p_name, p_quantity, p_si, p_type, warehouse_name, manufacture_name, stock,  today_date);
				warehouseBoard.add(wb);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_quantity);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(warehouse_name);
				System.out.println(manufacture_name);
				System.out.println(stock);
				System.out.println(today_date);

			}
			request.setAttribute("warehouseBoard", warehouseBoard);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	
		
		
		
		
	}

	public static void calcStock(HttpServletRequest request, String operationType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
			
		if ("one".equals(operationType)) {
            sql += " WHERE warehouse_test.warehouse_id = 1";
        } else if ("two".equals(operationType)) {
            sql += " WHERE warehouse_test.warehouse_id = 2";
        } else if ("three".equals(operationType)) {
            sql += " WHERE warehouse_test.warehouse_id = 3";
        } 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<WarehouseBoardTestDTO> warehouseBoard = new ArrayList<WarehouseBoardTestDTO>();
			WarehouseBoardTestDTO wb = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				int p_quantity = rs.getInt("p_quantity");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				String warehouse_name = rs.getString("warehouse_name");
				String manufacture_name = rs.getString("manufacture_name");
				int stock = rs.getInt("stock");
				String today_date = rs.getString("today_date");
				
				// p_id로 pk
				
				wb = new WarehouseBoardTestDTO(p_id, p_name, p_quantity, p_si, p_type, warehouse_name, manufacture_name, stock,  today_date);
				warehouseBoard.add(wb);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_quantity);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(warehouse_name);
				System.out.println(manufacture_name);
				System.out.println(stock);
				System.out.println(today_date);

			}
			request.setAttribute("warehouseBoard", warehouseBoard);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	
		
		
		
	}

}
