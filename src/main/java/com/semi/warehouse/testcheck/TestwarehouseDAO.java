package com.semi.warehouse.testcheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


public class TestwarehouseDAO {

	public static void getAllTest(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		String sql = "select * from product_test_ksj";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<TestwarehouseDTO> testWarehouse = new ArrayList<TestwarehouseDTO>();
			TestwarehouseDTO t= null;
			
			while (rs.next()) {
			int p_id = rs.getInt("p_id");	
			String p_si = rs.getString("p_si");
			String p_type = rs.getString("p_type");
			int p_quantity = rs.getInt("p_quantity");
			String p_name = rs.getString("p_name");
			int p_unicost = rs.getInt("p_unicost");
			int p_minstock = rs.getInt("p_minstock");
			int p_maxstock = rs.getInt("p_maxstock");
			int p_manufacturer = rs.getInt("p_manufacturer");
				// p_id로 pk
			t = new TestwarehouseDTO(p_id,p_si,p_type,p_quantity,p_name,p_unicost,p_minstock,p_maxstock,p_manufacturer);
			testWarehouse.add(t);
			
			}
			request.setAttribute("testWarehouse", testWarehouse);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
	}

}
