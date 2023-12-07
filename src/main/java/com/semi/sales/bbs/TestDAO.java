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
				t.getRecord_num();
				t.getCompany_sell();
				t.getDelivery_date();
				t.getSell_date();
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
