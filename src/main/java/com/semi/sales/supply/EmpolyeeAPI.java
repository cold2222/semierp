package com.semi.sales.supply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.distribution.db.DBManger;

public class EmpolyeeAPI {
	
	private static final EmpolyeeAPI EMPAPI = new EmpolyeeAPI(); 
	private  ArrayList<EmployeeDTO> emps;
	
	private EmpolyeeAPI() {
	}
	
	public static EmpolyeeAPI getEmpapi() {
		return EMPAPI;
	}

	
	public void searchEmployee(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "select e.*, d.d_dept from employee e, dept d where e.e_deptno = d.d_deptno and e_name like ? AND (e_deptno = 101 OR e_deptno = 102)";
					
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + request.getParameter("search") + "%");
			rs = pstmt.executeQuery();
			
			EmployeeDTO emp = null;
			emps = new ArrayList<EmployeeDTO>();
			while (rs.next()) {
				emp = new EmployeeDTO();
				emp.setE_no(rs.getInt("e_no"));
				emp.setE_name(rs.getString("e_name"));
				emp.setE_rank(rs.getString("e_rank"));
				emp.setE_deptno(rs.getInt("e_deptno"));
				emp.setE_tel(rs.getString("e_tel"));
				emp.setE_email(rs.getString("e_email"));
				emp.setE_joined_company(rs.getDate("e_joined_company"));
				emp.setD_dept(rs.getString("d_dept"));
				emp.setE_pw(sql);
				
				emps.add(emp);
			}
			Gson g = new Gson();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			response.getWriter().write(g.toJson(emps));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}
}
