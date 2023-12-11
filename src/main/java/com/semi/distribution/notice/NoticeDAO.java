package com.semi.distribution.notice;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.distribution.db.DBManger;

public class NoticeDAO {
	
	private ArrayList<NoticeDTO> bbsList;
	private static final NoticeDAO NDAO = new NoticeDAO();
	
	private NoticeDAO() {
	}
	
	public static NoticeDAO getNdao() {
		return NDAO;
	}

	public void paging(int pageNum, HttpServletRequest request) {
		int pageSize = 10; // 한 페이지당 보여줄 개수
		int totalData = bbsList.size();
		int totalPage = (int)Math.ceil((double)totalData / pageSize);
		
		int startDataNum = totalData - (pageSize * (pageNum - 1));
		int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
		
		ArrayList<NoticeDTO> items = new ArrayList<NoticeDTO>();
		for (int i = startDataNum-1; i > endDataNum; i--) {
			items.add(bbsList.get(i));
		}
		request.setAttribute("bbsList", items);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);
		
	}
	
	public void getBBS(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from distribution_bbs1 order by bbs1_num";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("공지 조회성공");
			NoticeDTO bbs = null;
			bbsList = new ArrayList<NoticeDTO>();
			while (rs.next()) {
				bbs = new NoticeDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				bbsList.add(bbs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("공지 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void getOneBBS(HttpServletRequest request) {
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

	public void InsertBBS(HttpServletRequest request) {
		
		String path = request.getServletContext().getRealPath("sb/distribution/imgfile");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			MultipartRequest mr = new MultipartRequest(request, path, 30 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());

			String name = mr.getParameter("n_title");
			String content = mr.getParameter("n_content");
			String img = mr.getFilesystemName("n_img");

			content = content.replaceAll("\r\n", "<br/>");

			String sql = "insert into distribution_bbs1 values(distribution_bbs1_seq.nextval,?,?,?,sysdate)";

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, content);
			pstmt.setString(3, img);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("bbs1 등록 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bbs1 등록 실패");
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void deleteBBS(HttpServletRequest request) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete distribution_bbs1 where bbs1_num = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("n_num"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공");
			}
			
		} catch (Exception e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, null);
		}
		
		
	}

	public void contentEnter(HttpServletRequest request) {
		NoticeDTO bbs  = (NoticeDTO)request.getAttribute("bbs");
		
		String content = bbs.getN_content().replaceAll("<br/>", "\r\n");
		bbs.setN_content(content);
		
		request.setAttribute("bbs", bbs);
	}

}
