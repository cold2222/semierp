package com.semi.sales.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class TestDAO {
	
	public static void getAllTest(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from distri_record_test";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Test t = null;
			ArrayList<Test> tests = new ArrayList<Test>();
			while (rs.next()) {
				t = new Test();
				t.setRecord_num(rs.getInt("record_num"));
				t.setCompany_sell(rs.getInt("company_sell"));
				t.setDelivery_date(rs.getDate("delivery_date"));
				t.setSell_date(rs.getDate("sell_date"));
				tests.add(t);
			}
			request.setAttribute("tests", tests);
			
			

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
		
	}

	public static void regTest(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		
		
	}

	

}
