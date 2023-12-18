package com.semi.distribution.shift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class ShiftAPIDAO {
	public static void changeShift(HttpServletRequest request) {
		String e_no = request.getParameter("e_no");
		String e_date = request.getParameter("e_date");
		String e_status = request.getParameter("e_status");

		System.out.println("ajax");
		System.out.println(e_no);
		System.out.println(e_date);
		System.out.println(e_status);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean insertOrUpdate = null;
		String sql = "select * from distribution_shift where e_no = ? and work_date = ?";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, e_no);
			pstmt.setString(2, e_date);
			rs = pstmt.executeQuery();
			System.out.println("ajax조회성공");
			insertOrUpdate = rs.next();
			System.out.println("셀렉트판정 :" + insertOrUpdate);
		} catch (Exception e) {
			System.out.println("ajax조회실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

		con = null;
		pstmt = null;

		if (insertOrUpdate) {
			sql = "update distribution_shift set work_num = ? where e_no = ? and work_date = ?";
			try {
				con = DBManger.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, e_status);
				pstmt.setString(2, e_no);
				pstmt.setString(3, e_date);

				if (pstmt.executeUpdate() == 1) {
					System.out.println("ajax업데이트 성공");
				}

			} catch (Exception e) {
				System.out.println("ajax업데이트 실패");
				e.printStackTrace();
			} finally {
				DBManger.close(con, pstmt, null);
			}

		} else {
			sql = "insert into distribution_shift values(?,?,?)";
			try {
				con = DBManger.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, e_no);
				pstmt.setString(2, e_status);
				pstmt.setString(3, e_date);

				if (pstmt.executeUpdate() == 1) {
					System.out.println("ajax등록 성공");
				}

			} catch (Exception e) {
				System.out.println("ajax등록 실패");
				e.printStackTrace();
			} finally {
				DBManger.close(con, pstmt, null);
			}

		}
	}
}
