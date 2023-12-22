package com.semi.warehouse.exwarehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBManager {
	private static BasicDataSource dataSource;

	// 클래스 초기화 블록에서 BasicDataSource 초기화
	static {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl(
				"jdbc:oracle:thin:@db202204301707_medium?TNS_ADMIN=/Users/sungjookim/Desktop/Ksj/Wallet_DB202204301707");
		dataSource.setUsername("FORMAC");
		dataSource.setPassword("Soldesk802!!");
	}

	// 데이터베이스 연결을 가져오는 메서드
	public static Connection connect() throws SQLException {
		return dataSource.getConnection();
	}

	// 데이터베이스 자원 반환 메서드
	public static void close(Connection connection, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
