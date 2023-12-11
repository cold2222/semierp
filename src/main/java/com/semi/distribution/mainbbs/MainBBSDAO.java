package com.semi.distribution.mainbbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.distribution.notice.NoticeDTO;
import com.semi.distribution.specialnote.SpecialNoteDTO;

public class MainBBSDAO {
	
	private static final MainBBSDAO BBSDAO = new MainBBSDAO();

	private MainBBSDAO() {
	}

	public static MainBBSDAO getBbsdao() {
		return BBSDAO;
	}

	public void getNotice(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from ( select * from distribution_bbs1 order by bbs1_num desc ) where rownum <= 5";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("bbsmain공지 조회성공");
			NoticeDTO bbs = null;
			ArrayList<NoticeDTO> noticeList = new ArrayList<NoticeDTO>();
			while (rs.next()) {
				bbs = new NoticeDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				noticeList.add(bbs);
			}
			request.setAttribute("noticeList", noticeList);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bbsmain공지 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void getSpecialNote(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from ( select * from distribution_bbs2 order by bbs2_num desc ) where rownum <= 5";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("bbsmain주의사항 조회성공");
			SpecialNoteDTO bbs = null;
			ArrayList<SpecialNoteDTO> specialNoteList = new ArrayList<SpecialNoteDTO>();
			while (rs.next()) {
				bbs = new SpecialNoteDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				specialNoteList.add(bbs);
			}
			request.setAttribute("specialNoteList", specialNoteList);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bbsmain주의사항 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
	
	
	
}
