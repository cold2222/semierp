package com.semi.warehouse.stockboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

	// db관련 작업을 할때 연결코드를 쓴 이후 작업 하는것 . 
	
	// 다쓰면 닫음
	//그걸 AOP 하자 
	public static Connection connect() throws SQLException {
		
		String url = "jdbc:oracle:thin:@db202204301707_medium?TNS_ADMIN=/Users/sungjookim/Desktop/Ksj/Wallet_DB202204301707";
		String id = "FORMAC";
		String pw = "Soldesk802!!";
		
		return DriverManager.getConnection(url, id, pw);
	}

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
			
		try {
			
			if (rs !=null) {
				rs.close();
				
			}
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
