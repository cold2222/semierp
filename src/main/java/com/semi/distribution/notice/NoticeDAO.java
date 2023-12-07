package com.semi.distribution.notice;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class NoticeDAO {

	public static void getBBS(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from distribution_bbs1";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("공지 조회성공");
			NoticeDTO bbs = null;
			ArrayList<NoticeDTO> bbsList = new ArrayList<NoticeDTO>();
			while (rs.next()) {
				bbs = new NoticeDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				bbsList.add(bbs);
			}
			
			request.setAttribute("bbsList", bbsList);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("공지 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public static void getOneBBS(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from distribution_bbs1 where bbs1_num = ?";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			String n_num = request.getParameter("n_num");
			pstmt.setString(1, n_num);
			
			rs = pstmt.executeQuery();
			
			NoticeDTO bbs = null;
			if(rs.next()) {
				String content = rs.getString(3);
				content = content.replaceAll("<br>", "\r\n");
				System.out.println("공지 뷰 조회성공");
				bbs = new NoticeDTO(rs.getString(1),rs.getString(2),content,rs.getString(4),rs.getDate(5));
			}
			
			request.setAttribute("bbs", bbs);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("공지 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

}
