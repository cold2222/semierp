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
		String sql = "SELECT \n"
				+ "    product_test_ksj.p_id,\n"
				+ "    product_test_ksj.p_name,\n"
				+ "    product_test_ksj.p_si,\n"
				+ "    product_test_ksj.p_type,\n"
				+ "    Purchase_record_ksj.record_count,\n"
				+ "    Purchase_recordall_ksj.in_warehouse_date,\n"
				+ "    Purchase_recordall_ksj.status\n"
				+ "FROM \n"
				+ "    product_test_ksj, \n"
				+ "    Purchase_record_ksj, \n"
				+ "    Purchase_recordall_ksj\n"
				+ "WHERE \n"
				+ "    product_test_ksj.p_id = Purchase_record_ksj.p_id\n"
				+ "    AND Purchase_record_ksj.recordall_num = Purchase_recordall_ksj.recordall_num\n"
				+ "    AND Purchase_recordall_ksj.status = 3";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<TestwarehouseDTO> testWarehouse = new ArrayList<TestwarehouseDTO>();
			TestwarehouseDTO t= null;
			
			while (rs.next()) {
			int p_id = rs.getInt("p_id");	
			String p_name = rs.getString("p_name");
			String p_si = rs.getString("p_si");
			String p_type = rs.getString("p_type");
			int record_count = rs.getInt("record_count");
			String in_warehouse_date = rs.getString("in_warehouse_date");
			int status = rs.getInt("status");
				// p_id로 pk
			t = new TestwarehouseDTO(p_id,p_name,p_si,p_type,record_count,in_warehouse_date,status );
			testWarehouse.add(t);
			
			System.out.println(p_id);
			System.out.println(p_name);
			System.out.println(p_si);
			System.out.println(p_type);
			System.out.println(record_count);
			System.out.println(in_warehouse_date);
			System.out.println(status);
			
			
			}
			request.setAttribute("testWarehouse", testWarehouse);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
	}

}
