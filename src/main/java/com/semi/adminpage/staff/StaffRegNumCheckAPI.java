package com.semi.adminpage.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.distribution.db.DBManger;
import com.semi.distribution.employee.EmployeeDTO;

public class StaffRegNumCheckAPI {
	
	private static final StaffRegNumCheckAPI SCAPI = new StaffRegNumCheckAPI();
	
	private StaffRegNumCheckAPI() {
	}

	public static StaffRegNumCheckAPI getScapi() {
		return SCAPI;
	}

	public void getEmployeeNumbers(HttpServletResponse response) {
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from employee order by e_no";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();
			EmployeeDTO emp = null;
			while (rs.next()) {
				emp = new EmployeeDTO();
				emp.setE_no(rs.getString("e_no"));
				
				empList.add(emp);
			}
			System.out.println("중복처리용넘버 조회 성공");
			
			response.setContentType("application/json; charset=utf-8");
			Gson gson = new Gson();
			String jsonEmpList = gson.toJson(empList);
			System.out.println("중복체크API 제이슨 반환성공");
			response.getWriter().print(jsonEmpList);
			
		
		
		} catch (Exception e) {
			System.out.println("중복처리용넘버or제이슨반환 조회 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
	
}
