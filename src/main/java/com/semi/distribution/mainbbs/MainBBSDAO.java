package com.semi.distribution.mainbbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.distribution.deliverysale.DeliverySaleDTO;
import com.semi.distribution.notice.NoticeDTO;
import com.semi.distribution.receipt.ReceiptDTO;
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
			NoticeDTO bbs = null;
			ArrayList<NoticeDTO> noticeList = new ArrayList<NoticeDTO>();
			while (rs.next()) {
				bbs = new NoticeDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				noticeList.add(bbs);
			}
			System.out.println("bbsmain공지 조회성공");
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
			SpecialNoteDTO bbs = null;
			ArrayList<SpecialNoteDTO> specialNoteList = new ArrayList<SpecialNoteDTO>();
			while (rs.next()) {
				bbs = new SpecialNoteDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				specialNoteList.add(bbs);
			}
			System.out.println("bbsmain주의사항 조회성공");
			request.setAttribute("specialNoteList", specialNoteList);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bbsmain주의사항 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void getDeliverySaleListOfFive(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from ("
				+ "select a.c_contract_no, a.c_c_no, a.c_e_id, a.c_created_date, a.c_due_date, a.c_status, "
				+ "b.c_name, c.e_name " 
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "inner join employee c on  a.c_e_id = c.e_no " 
				+ "where a.c_status = 1 and a.c_type = 2 order by a.c_due_date "
				+ ") where rownum <= 5";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			ArrayList<DeliverySaleDTO> deliverySaleList = new ArrayList<DeliverySaleDTO>();
			DeliverySaleDTO dec = null;
			while (rs.next()) {
				dec = new DeliverySaleDTO();
				dec.setC_contract_no(rs.getString("c_contract_no"));
				dec.setC_c_no(rs.getString("c_c_no"));
				dec.setC_e_id(rs.getString("c_e_id"));
				dec.setC_created_date(rs.getDate("c_created_date"));
				dec.setC_due_date(rs.getDate("c_due_date"));
				dec.setC_status(rs.getString("c_status"));
				dec.setC_name(rs.getString("c_name"));
				dec.setE_name(rs.getString("e_name"));
				deliverySaleList.add(dec);
			}
			System.out.println("판매배송미정 조회 성공");
			request.setAttribute("deliverySaleList", deliverySaleList);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("판매배송미정 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void getReceiptListOfFive(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from ( "
				+ "SELECT a.c_contract_no, a.c_c_no, a.c_e_id, a.c_created_date, a.c_due_date, a.c_status, "
				+ "b.c_name, c.e_name "
				+ "FROM contract a "
				+ "INNER JOIN company b ON a.c_c_no = b.c_no "
				+ "INNER JOIN employee c ON a.c_e_id = c.e_no "
				+ "WHERE a.c_status = 1 AND a.c_type = 1 "
				+ "ORDER BY a.c_due_date DESC "
				+ ") where rownum <= 5";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<ReceiptDTO> receiptList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setC_contract_no(rs.getString("c_contract_no"));
				rec.setC_c_no(rs.getString("c_c_no"));
				rec.setC_e_id(rs.getString("c_e_id"));
				rec.setC_created_date(rs.getDate("c_created_date"));
				rec.setC_due_date(rs.getDate("c_due_date"));
				rec.setC_status(rs.getString("c_status"));
				rec.setC_name(rs.getString("c_name"));
				rec.setE_name(rs.getString("e_name"));
				receiptList.add(rec);

			}
			request.setAttribute("receiptList", receiptList);
			System.out.println("수령미정 조회 성공");

		} catch (Exception e) {
			System.out.println("수령미정 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void getClearListOfFive(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

				String sql = "select * from ( "
				+ "SELECT a.c_contract_no, c.e_name, " 
				+ "a.c_delivery_date, a.c_due_date, a.c_status, a.c_type ,d.c_name "
				+ "FROM contract a " 
				+ "INNER JOIN shipping b ON a.c_contract_no = b.s_contract_no "
				+ "INNER JOIN employee c ON b.s_e_no = c.e_no " 
				+ "INNER JOIN company d ON a.c_c_no = d.c_no "
				+ "WHERE (a.c_type = 1 " 
				+ "AND a.c_status = 2) or (a.c_type = 2 AND a.c_status = 3) " 
				+ "ORDER BY a.c_delivery_date "
				+ ") where rownum <= 5";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<ReceiptDTO> receiptList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setC_contract_no(rs.getString("c_contract_no"));
				rec.setC_name(rs.getString("c_name"));
				rec.setE_name(rs.getString("e_name"));
				rec.setC_delivery_date(rs.getDate("c_delivery_date"));
				rec.setC_due_date(rs.getDate("c_due_date"));
				rec.setC_status(rs.getString("c_status"));
				rec.setC_type(rs.getString("c_type"));
				receiptList.add(rec);
			}
			request.setAttribute("clearList", receiptList);
			System.out.println("clearList 조회 성공");

		} catch (Exception e) {
			System.out.println("clearList 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
	
	
	
}
