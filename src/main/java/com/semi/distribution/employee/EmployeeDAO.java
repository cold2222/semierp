package com.semi.distribution.employee;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.distribution.notice.NoticeDAO;
import com.semi.distribution.notice.NoticeDTO;

public class EmployeeDAO {

	private ArrayList<EmployeeDTO> empList;
	private static final EmployeeDAO EDAO = new EmployeeDAO();
	
	private EmployeeDAO() {
	}
	
	public static EmployeeDAO getEdao() {
		return EDAO;
	}

	public void paging(int pageNum, HttpServletRequest request) {
		int pageSize = 10; // 한 페이지당 보여줄 개수
		int totalData = empList.size();
		int totalPage = (int)Math.ceil((double)totalData / pageSize);
		
		int startDataNum = totalData - (pageSize * (pageNum - 1));
		int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
		
		ArrayList<EmployeeDTO> items = new ArrayList<EmployeeDTO>();
		if(empList.size() > 0) {
			for (int i = startDataNum-1; i > endDataNum; i--) {
				items.add(empList.get(i));
			}
		}
		request.setAttribute("empList", items);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);
		
	}
	
	public void getEmployee(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from employee where e_deptno = 201 order by "
					+ "case e_rank when '社長' then 7 "
					+ "when '部長' then 6 "
					+ "when '課長' then 5 "
					+ "when '係長' then 4 "
					+ "when '主任' then 3 "
					+ "when '社員' then 2 else 1 end, e_name ";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("사원 조회성공");
			EmployeeDTO emp = null;
			empList = new ArrayList<EmployeeDTO>();
			while (rs.next()) {
				emp = new EmployeeDTO();
				emp.setE_no(rs.getString(1));
				emp.setE_pw(rs.getString(2));
				emp.setE_deptno(rs.getString(3));
				emp.setE_name(rs.getString(4));
				emp.setE_rank(rs.getString(5));
				emp.setE_tel(rs.getString(6));
				emp.setE_email(rs.getString(7));
				emp.setE_joined_company(rs.getDate(8));
				empList.add(emp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("사원 조회 실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void InsertEmployee(HttpServletRequest request) throws UnsupportedEncodingException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		request.setCharacterEncoding("utf-8");
		String e_pw = request.getParameter("e_pw");
		String e_name = request.getParameter("e_name");
		String e_rank = request.getParameter("e_rank");
		String e_tel = request.getParameter("e_tel");
		String e_email = request.getParameter("e_email");
		String e_joined_company = request.getParameter("e_joined_company");
		
		System.out.println(e_name);
		System.out.println(e_rank);
		System.out.println(e_tel);
		System.out.println(e_email);
		System.out.println(e_joined_company);
		
		
		String sql = "INSERT INTO employee values(employee_seq.nextval,?,201,?,?,?,?,?)";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, e_pw);
			pstmt.setString(2, e_name);
			pstmt.setString(3, e_rank);
			pstmt.setString(4, e_tel);
			pstmt.setString(5, e_email);
			pstmt.setString(6, e_joined_company);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("사원등록 성공");
			}
			
		} catch (Exception e) {
			System.out.println("사원등록 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void DeleteEmployee(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete employee where e_no = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("e_no"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("사원삭제 성공");
			}
			
		} catch (Exception e) {
			System.out.println("사원삭제 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void getOneEmployee(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from employee where e_no = ?";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			String e_no = request.getParameter("e_no");
			pstmt.setString(1, e_no);
			
			rs = pstmt.executeQuery();
			
			EmployeeDTO emp = null;
			if(rs.next()) {
				System.out.println("사원1 조회성공");
				emp = new EmployeeDTO();
				emp.setE_no(rs.getString(1));
				emp.setE_pw(rs.getString(2));
				emp.setE_deptno(rs.getString(3));
				emp.setE_name(rs.getString(4));
				emp.setE_rank(rs.getString(5));
				emp.setE_tel(rs.getString(6));
				emp.setE_email(rs.getString(7));
				emp.setE_joined_company(rs.getDate(8));
			}
			
			request.setAttribute("emp", emp);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("사원1 조회실패");
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void updateEmployee(HttpServletRequest request) throws UnsupportedEncodingException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE employee SET e_pw = ?, e_name = ?, e_rank = ?, e_tel = ?, e_email = ?, e_joined_company = ? WHERE e_no = ?";
		request.setCharacterEncoding("utf-8");
		
		String e_no = request.getParameter("e_no");
		String e_pw = request.getParameter("e_pw");
		String e_name = request.getParameter("e_name");
		String e_rank = request.getParameter("e_rank");
		String e_tel = request.getParameter("e_tel");
		String e_email = request.getParameter("e_email");
		String e_joined_company = request.getParameter("e_joined_company");
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, e_pw);
			pstmt.setString(2, e_name);
			pstmt.setString(3, e_rank);
			pstmt.setString(4, e_tel);
			pstmt.setString(5, e_email);
			pstmt.setString(6, e_joined_company);
			pstmt.setString(7, e_no);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("사원1 수정성공");
			}
		
		
		}catch (Exception e) {
			System.out.println("사원1 수정실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, null);
		}
	}



}
