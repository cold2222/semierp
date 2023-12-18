package com.semi.distribution.deliverysale;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class DeliverySaleDAO {
	
	private static final DeliverySaleDAO DSDAO = new DeliverySaleDAO();
	private ArrayList<DeliverySaleDTO> deliverySaleList;
	
	private DeliverySaleDAO() {
		
	}

	public static DeliverySaleDAO getDSdao() {
		return DSDAO;
	}

	public void getDeliverySaleList(HttpServletRequest request) {
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select a.c_contract_no, a.c_c_no, a.c_e_id, a.c_created_date, a.c_due_date, a.c_status, "
				+ "b.c_name, c.e_name "
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "inner join employee c on  a.c_e_id = c.e_no "
				+ "where a.c_status = 1 and a.c_type = 2 order by a.c_due_date";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			deliverySaleList = new ArrayList<DeliverySaleDTO>();
			DeliverySaleDTO dec = null;
			while (rs.next()) {
				dec = new DeliverySaleDTO();
				dec.setC_contract_no(rs.getString("c_contract_no"));
				dec.setC_c_no(rs.getString("c_c_no"));
				dec.setC_e_id(rs.getString("c_e_id"));
				dec.setC_created_date(rs.getDate("c_created_date"));;
				dec.setC_due_date(rs.getDate("c_due_date"));
				dec.setC_status(rs.getString("c_status"));
				dec.setC_name(rs.getString("c_name"));
				dec.setE_name(rs.getString("e_name"));
				deliverySaleList.add(dec);
				
			}
		System.out.println("납품목록 조회 성공");
		request.setAttribute("deliverySaleList", deliverySaleList);
		
		
		} catch (Exception e) {
			System.out.println("납품목록 조회 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		
		
	}

	public void getdeliverySaleData(HttpServletRequest request) {
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select a.c_contract_no,a.c_created_date,a.c_due_date,b.c_name,b.c_keeper,b.c_phone "
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "where a.c_contract_no = ? order by c_due_date";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();
			
			DeliverySaleDTO dec = null;
			if (rs.next()) {
				dec = new DeliverySaleDTO();
				dec.setC_contract_no(rs.getString("c_contract_no"));
				dec.setC_created_date(rs.getDate("c_created_date"));
				dec.setC_due_date(rs.getDate("c_due_date"));
				dec.setC_name(rs.getString("c_name"));
				dec.setC_keeper(rs.getString("c_keeper"));
				dec.setC_phone(rs.getString("c_phone"));
				request.setAttribute("dec", dec);
				
			}
		System.out.println("납품상세 조회 성공");
		
		
		} catch (Exception e) {
			System.out.println("납품상세 조회 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		
		
		
	}

	public void getDeliverySaleItemList(HttpServletRequest request) {
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select p_name,p_quantity,p_si,p_type,ci_count,ci_unit_price "
				+ "from contract a inner join contract_items b on a.c_contract_no = b.ci_c_contract_no "
				+ "inner join product c on b.ci_p_id = c.p_id "
				+ "where a.c_contract_no = ? order by p_name";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();
			
			ArrayList<DeliverySaleDTO> itemList = new ArrayList<DeliverySaleDTO>();
			DeliverySaleDTO dec = null;
			while (rs.next()) {
				dec = new DeliverySaleDTO();
				dec.setP_name(rs.getString("p_name"));
				dec.setP_quantity(rs.getString("p_quantity"));
				dec.setP_si(rs.getString("p_si"));
				dec.setP_type(rs.getString("p_type"));
				dec.setCi_count(rs.getString("ci_count"));
				dec.setCi_unit_price(rs.getString("ci_unit_price"));
				
				itemList.add(dec);
				
			}
			request.setAttribute("itemList", itemList);
		System.out.println("납품상세아이템 조회 성공");
		
		
		} catch (Exception e) {
			System.out.println("납품상세아이템 조회 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}



}
