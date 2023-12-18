package com.semi.distribution.receipt;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class ReceiptDAO {
	
	private static final ReceiptDAO RDAO = new ReceiptDAO();
	private ArrayList<ReceiptDTO> receiptList;
	
	private ReceiptDAO() {
		
	}

	public static ReceiptDAO getRdao() {
		return RDAO;
	}

	public void getReceiptList(HttpServletRequest request) {
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from buy_contract where b_status = 1";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			receiptList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setB_contract_no(rs.getString("b_contract_no"));
				rec.setB_ic_no(rs.getString("b_ic_no"));
				rec.setB_e_id(rs.getString("b_e_id"));
				rec.setB_contract(rs.getDate("b_contract"));
				rec.setB_arrival_date(rs.getDate("b_arrival_date"));
				rec.setB_status(rs.getString("b_status"));
				receiptList.add(rec);
				
			}
		System.out.println("수령 조회 성공");
		request.setAttribute("receiptList", receiptList);
		
		
		} catch (Exception e) {
			System.out.println("수령목록 조회 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		
		
	}

	public void getReceiptData(HttpServletRequest request) {
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select a.b_contract_no,a.b_contract,a.b_arrival_date,b.ic_name,b.ic_keeper,b.ic_phone "
				+ "from buy_contract a inner join import_company b on a.b_ic_no = b.ic_no "
				+ "where a.b_contract_no = ? order by b_arrival_date";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("b_contract_no"));
			rs = pstmt.executeQuery();
			
			ReceiptDTO rec = null;
			if (rs.next()) {
				rec = new ReceiptDTO();
				rec.setB_contract_no(rs.getString("b_contract_no"));
				rec.setB_contract(rs.getDate("b_contract"));
				rec.setB_arrival_date(rs.getDate("b_arrival_date"));
				rec.setIc_name(rs.getString("ic_name"));
				rec.setIc_keeper(rs.getString("ic_keeper"));
				rec.setIc_phone(rs.getString("ic_phone"));
				request.setAttribute("rec", rec);
				
			}
		System.out.println("수령상세 조회 성공");
		
		
		} catch (Exception e) {
			System.out.println("수령상세 조회 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		
		
		
	}

	public void getReceiptItemList(HttpServletRequest request) {
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select p_name,p_quantity,p_si,p_type,bi_buy_count,bi_unit_price "
				+ "from buy_contract a inner join buy_item b on a.b_contract_no = b.bi_b_contract_no "
				+ "inner join product c on b.bi_p_id = c.P_id "
				+ "where a.b_contract_no = ? order by p_name";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("b_contract_no"));
			rs = pstmt.executeQuery();
			
			ArrayList<ReceiptDTO> itemList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setP_name(rs.getString("p_name"));
				rec.setP_quantity(rs.getString("p_quantity"));
				rec.setP_si(rs.getString("p_si"));
				rec.setP_type(rs.getString("p_type"));
				rec.setBi_buy_count(rs.getString("bi_buy_count"));
				rec.setBi_unit_price(rs.getString("bi_unit_price"));
				
				itemList.add(rec);
				
			}
			request.setAttribute("itemList", itemList);
		System.out.println("수령상세아이템 조회 성공");
		
		
		} catch (Exception e) {
			System.out.println("수령상세아이템 조회 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}



}
