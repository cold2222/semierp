package com.semi.distribution.shipping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class ShippingAPI {

	public static void getDelivery(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectedYear = request.getParameter("selectedYear");
	    String selectedMonth = request.getParameter("selectedMonth");
		
	    String sql = "select  from shipping";
		try {
			con = DBManger.connect();
			
		
		}  catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
}
