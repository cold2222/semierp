package com.semi.sales.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	public static Connection connect() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		return DriverManager.getConnection(url, "c##jh0102", "jh0102");
	}

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if (rs != null) {
				rs.close();
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
