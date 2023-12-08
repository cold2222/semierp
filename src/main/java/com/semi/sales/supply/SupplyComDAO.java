package com.semi.sales.supply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.sales.bbs.DBManager;

public class SupplyComDAO {

	public static void getAllCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from supply_status";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SupplyCompany sc = null;
			ArrayList<SupplyCompany> scs = new ArrayList<SupplyCompany>();
			while (rs.next()) {
				sc = new SupplyCompany();
				sc.setSupply_num(rs.getInt("supply_num"));
				sc.setSupply_company(rs.getString("supply_company"));
				sc.setSupply_name(rs.getString("supply_name"));
				sc.setSupplied_name(rs.getString("supplied_name"));
				sc.setSupply_addr(rs.getString("supply_addr"));
				sc.setPurchase_text(rs.getString("purchase_text"));
				scs.add(sc);
			}
			request.setAttribute("scs", scs);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}
	
	public static void regCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into supply_status values(supply_status_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("supply_company"));
			pstmt.setString(2, request.getParameter("supply_name"));
			pstmt.setString(3, request.getParameter("supplied_name"));
			pstmt.setString(4, request.getParameter("supply_addr"));
			pstmt.setString(5, request.getParameter("purchase_text"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		
	}

	public static void getCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from supply_status where supply_num=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			SupplyCompany sc = null;
			if (rs.next()) {
				sc = new SupplyCompany();
				sc.setSupply_num(rs.getInt("supply_num"));
				sc.setSupply_company(rs.getString("supply_company"));
				sc.setSupply_name(rs.getString("supply_name"));
				sc.setSupplied_name(rs.getString("supplied_name"));
				sc.setSupply_addr(rs.getString("supply_addr"));
				sc.setPurchase_text(rs.getString("purchase_text"));
				request.setAttribute("sc", sc);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}

	public static void getAllCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from purchase_buy_recordall";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SupplyContract st = null;
			ArrayList<SupplyContract> sts = new ArrayList<SupplyContract>();
			while (rs.next()) {
				st = new SupplyContract();
				st.setRecordall_buy_num(rs.getInt("recordall_buy_num"));
				st.setSupply_num(rs.getInt("supply_num"));
				st.setPurchase_date(rs.getDate("purchase_date"));
				st.setTransaction_date(rs.getDate("transaction_date"));
				st.setIn_warehouse_date(rs.getDate("in_warehouse_date"));
				st.setStatus(rs.getInt("status"));
				sts.add(st);
			}
			request.setAttribute("sts", sts);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void regCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into purchase_buy_recordall values(purchase_buy_recordall_seq.nextval, ?, ?, ?, ?, 1)";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("supply_num"));
			pstmt.setString(2, request.getParameter("purchase_date"));
			pstmt.setString(3, request.getParameter("transaction_date"));
			pstmt.setString(4, request.getParameter("in_warehouse_date"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void getContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from purchase_buy_recordall where recordall_buy_num=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			SupplyContract st = null;
			if (rs.next()) {
				st = new SupplyContract();
				st.setRecordall_buy_num(rs.getInt("recordall_buy_num"));
				st.setSupply_num(rs.getInt("supply_num"));
				st.setPurchase_date(rs.getDate("purchase_date"));
				st.setTransaction_date(rs.getDate("transaction_date"));
				st.setIn_warehouse_date(rs.getDate("in_warehouse_date"));
				st.setStatus(rs.getInt("status"));
				request.setAttribute("st", st);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
		
	}

	public static void regContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into purchase_buy_record values(purchase_buy_record_seq.nextval, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("recordall_buy_num"));
			pstmt.setString(2, request.getParameter("p_id"));
			pstmt.setString(3, request.getParameter("record_count"));
			pstmt.setString(4, request.getParameter("record_price"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getAllContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from purchase_buy_record";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SupplyContents content = null;
			ArrayList<SupplyContents> contents = new ArrayList<SupplyContents>();
			while (rs.next()) {
				content = new SupplyContents();
				content.setRecord_buy_num(rs.getInt("record_buy_num"));
				content.setRecordall_buy_num(rs.getInt("recordall_buy_num"));
				content.setP_id(rs.getInt("p_id"));
				content.setRecord_count(rs.getInt("record_count"));
				content.setRecord_price(rs.getInt("record_price"));
				contents.add(content);
			}
			request.setAttribute("contents", contents);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	

}
