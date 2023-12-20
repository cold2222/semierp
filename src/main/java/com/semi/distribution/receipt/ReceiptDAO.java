package com.semi.distribution.receipt;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.distribution.employee.EmployeeDTO;
import com.semi.distribution.shift.ShiftDTO;

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
		
		String sql = "select a.c_contract_no, a.c_c_no, a.c_e_id, a.c_created_date, a.c_due_date, a.c_status, "
				+ "b.c_name, c.e_name "
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "inner join employee c on  a.c_e_id = c.e_no "
				+ "where a.c_status = 1 and a.c_type = 1 order by a.c_due_date";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			receiptList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setC_contract_no(rs.getString("c_contract_no"));
				rec.setC_c_no(rs.getString("c_c_no"));
				rec.setC_e_id(rs.getString("c_e_id"));
				rec.setC_created_date(rs.getDate("c_created_date"));;
				rec.setC_due_date(rs.getDate("c_due_date"));
				rec.setC_status(rs.getString("c_status"));
				rec.setC_name(rs.getString("c_name"));
				rec.setE_name(rs.getString("e_name"));
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
		
		String sql = "select a.c_contract_no,a.c_created_date,a.c_due_date,b.c_name,b.c_keeper,b.c_phone "
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "where a.c_contract_no = ? order by c_due_date";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();
			
			ReceiptDTO rec = null;
			if (rs.next()) {
				rec = new ReceiptDTO();
				rec.setC_contract_no(rs.getString("c_contract_no"));
				rec.setC_created_date(rs.getDate("c_created_date"));
				rec.setC_due_date(rs.getDate("c_due_date"));
				rec.setC_name(rs.getString("c_name"));
				rec.setC_keeper(rs.getString("c_keeper"));
				rec.setC_phone(rs.getString("c_phone"));
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
		
		String sql = "select p_name,p_quantity,p_si,p_type,ci_count,ci_unit_price "
				+ "from contract a inner join contract_items b on a.c_contract_no = b.ci_c_contract_no "
				+ "inner join product c on b.ci_p_id = c.p_id "
				+ "where a.c_contract_no = ? order by p_name";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();
			
			ArrayList<ReceiptDTO> itemList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setP_name(rs.getString("p_name"));
				rec.setP_quantity(rs.getString("p_quantity"));
				rec.setP_si(rs.getString("p_si"));
				rec.setP_type(rs.getString("p_type"));
				rec.setCi_count(rs.getString("ci_count"));
				rec.setCi_unit_price(rs.getString("ci_unit_price"));
				
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

	public ArrayList<EmployeeDTO> getEmpList() {
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from employee where e_deptno = 201 order by e_no";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();
			EmployeeDTO emp = null;
			while (rs.next()) {
				emp = new EmployeeDTO();
				emp.setE_no(rs.getString("e_no"));
				emp.setE_name(rs.getString("e_name"));
				emp.setE_rank(rs.getString("e_rank"));
				
				empList.add(emp);
				
			}
			System.out.println("출근멤버 조회 성공");
			return empList;
		
		
		} catch (Exception e) {
			System.out.println("출근멤버 조회 실패");
			e.printStackTrace();
			return null;
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}


	public ArrayList<ShiftDTO> getRestMemberList(HttpServletRequest request) {
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from distribution_shift where work_date = ? order by e_no";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_due_date"));
			rs = pstmt.executeQuery();
			
			ArrayList<ShiftDTO> restMember = new ArrayList<ShiftDTO>();
			ShiftDTO emp = null;
			while (rs.next()) {
				emp = new ShiftDTO();
				emp.setE_id(rs.getString("e_no"));
				emp.setW_date(rs.getString("work_date"));
				emp.setW_n(rs.getString("work_num"));
				
				restMember.add(emp);
				
			}
			System.out.println("쉬는멤버 조회 성공");
			return restMember;
		
		
		} catch (Exception e) {
			System.out.println("쉬는멤버 조회 실패");
			e.printStackTrace();
			return null;
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		
	}

	public void Shift(ArrayList<EmployeeDTO> empList, ArrayList<ShiftDTO> restMemberList, HttpServletRequest request) {
		
		ArrayList<EmployeeDTO> ShiftList = new ArrayList<EmployeeDTO>();
		
		for (EmployeeDTO emp : empList) {
			for (ShiftDTO restMember : restMemberList) {
				
				
			}
		}
	}



}