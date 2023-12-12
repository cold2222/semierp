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
		for (int i = startDataNum-1; i > endDataNum; i--) {
			items.add(empList.get(i));
		}
		request.setAttribute("empList", items);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);
		
	}
	
	public void getEmployee(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from distribution_employees order by employee_id";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("사원 조회성공");
			EmployeeDTO emp = null;
			empList = new ArrayList<EmployeeDTO>();
			while (rs.next()) {
				emp = new EmployeeDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
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
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hireDate");
		
		System.out.println(name);
		System.out.println(position);
		System.out.println(phone);
		System.out.println(email);
		System.out.println(hireDate);
		
		
		String sql = "INSERT INTO distribution_employees values(d_employees_seq.nextval,?,?,?,?,?)";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, position);
			pstmt.setString(3, phone);
			pstmt.setString(4, email);
			pstmt.setString(5, hireDate);
			
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
		
		String sql = "delete distribution_employees where employee_id = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("e_id"));
			
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

		String sql = "select * from distribution_employees where employee_id = ?";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			String e_id = request.getParameter("e_id");
			pstmt.setString(1, e_id);
			
			rs = pstmt.executeQuery();
			
			EmployeeDTO emp = null;
			if(rs.next()) {
				String content = rs.getString(3);
				System.out.println("사원1 조회성공");
				emp = new EmployeeDTO(rs.getString(1),rs.getString(2),content,rs.getString(4),rs.getString(5),rs.getDate(6));
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
		
		String sql = "UPDATE distribution_employees SET employee_name = ?, employee_position = ?, phone_number = ?, email = ?, hire_date = ? WHERE employee_id = ?";
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hireDate");
		String id = request.getParameter("id");
		
		System.out.println(name);
		System.out.println(position);
		System.out.println(phone);
		System.out.println(email);
		System.out.println(hireDate);
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, position);
			pstmt.setString(3, phone);
			pstmt.setString(4, email);
			pstmt.setString(5, hireDate);
			pstmt.setString(6, id);
			
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
