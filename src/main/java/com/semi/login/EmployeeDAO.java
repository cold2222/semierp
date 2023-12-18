package com.semi.login;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class EmployeeDAO {
	
	private static EmployeeDAO employeeManager = null;
	private EmployeeDAO() {
		
	}
	public static EmployeeDAO getEmployeeManager() {
		if(employeeManager == null) {
			employeeManager = new EmployeeDAO();
		} 
		return employeeManager;
	}
	
	public boolean login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String sql = "select * from employee join dept on e_deptno = d_deptno where e_no=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String newPW = Encrypt.getPW(id, pw);
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(newPW.equals(rs.getString("e_pw"))) {
					EmployeeDTO empInfo = new EmployeeDTO();
					empInfo.setE_dept(rs.getString("d_dept"));
					empInfo.setE_inputPW(pw);
					empInfo.setE_no(id);
					empInfo.setE_name(rs.getString("e_name"));
					empInfo.setE_pw(rs.getString("e_pw"));
					empInfo.setE_rank(rs.getString("e_rank"));
					empInfo.setE_tel(rs.getString("e_tel"));
					empInfo.setE_deptno(rs.getInt("e_deptno"));
					request.getSession().setAttribute("empInfo", empInfo);
					request.getSession().setMaxInactiveInterval(10*60*60*60);
					return true;
				} else {
					request.setAttribute("error", "パスワードを確認してください!");
				}
			} else {
				request.setAttribute("error", "アカウント「社員番号」が存在しません");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
		return false;
		
	}
	
	public boolean loginCheck(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from employee join dept on e_deptno = d_deptno where e_no=?";
		EmployeeDTO empInfo = (EmployeeDTO) request.getSession().getAttribute("empInfo");
		if(empInfo == null)
			return false;
		
		try {
			String newPW = Encrypt.getPW(empInfo.getE_no(), empInfo.getE_inputPW());
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, empInfo.getE_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(newPW.equals(rs.getString("e_pw"))) 
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
		
		return false;
	}

}
