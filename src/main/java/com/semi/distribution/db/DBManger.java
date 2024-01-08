package com.semi.distribution.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManger {
	

	public static Connection connect() throws SQLException, ClassNotFoundException {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(url,"jh940502","jh940502");
	}

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
