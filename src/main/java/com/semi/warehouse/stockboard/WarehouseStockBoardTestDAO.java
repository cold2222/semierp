package com.semi.warehouse.stockboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


public class WarehouseStockBoardTestDAO {

	public static void getWSBTest(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product_test.p_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_si,\n"
				+ "    product_test.p_type,\n"
				+ "    product_test.p_unicost,\n"
				+ "    manufacture_test.manufacture_name,\n"
				+ "    stock_test.stock\n"
				+ "FROM\n"
				+ "    product_test\n"
				+ "JOIN\n"
				+ "    stock_test ON product_test.p_id = stock_test.p_id\n"
				+ "JOIN\n"
				+ "    warehouse_test ON stock_test.warehouse_id = warehouse_test.warehouse_id\n"
				+ "JOIN\n"
				+ "    manufacture_test ON product_test.p_manufacturer = manufacture_test.p_manufacturer\n"
				+ "ORDER BY\n"
				+ "    product_test.p_type ASC,\n"
				+ "    product_test.p_name ASC,\n"
				+ "    product_test.p_quantity ASC";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<WarehouseStockBoardTestDTO> warehouseStockBoard = new ArrayList<WarehouseStockBoardTestDTO>();
			WarehouseStockBoardTestDTO wsb = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				int p_quantity = rs.getInt("p_quantity");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_unicost = rs.getInt("p_unicost");
				String manufacture_name = rs.getString("manufacture_name");
				int stock = rs.getInt("stock");
				//String today_date = rs.getString("today_date");
				
				// p_id로 pk
				
				wsb = new WarehouseStockBoardTestDTO();
				wsb.setP_id(p_id);
				wsb.setP_name(p_name);
				wsb.setP_quantity(p_quantity);
				wsb.setP_si(p_si);
				wsb.setP_type(p_type);
				wsb.setP_unicost(p_unicost);
				wsb.setManufacture_name(manufacture_name);
				wsb.setStock(stock);

				
				
				warehouseStockBoard.add(wsb);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_quantity);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_unicost);
				System.out.println(manufacture_name);
				System.out.println(stock);

			}
			request.setAttribute("warehouseStockBoard", warehouseStockBoard);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
	}

	

}
