package com.semi.distribution.delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.distribution.employee.EmployeeDTO;
import com.semi.distribution.shift.ShiftDTO;

public class DistributionWorkListDAO {
	
	private static final DistributionWorkListDAO WDAO = new DistributionWorkListDAO();
	
	private DistributionWorkListDAO() {
	}
	
	public static DistributionWorkListDAO getWdao() {
		return WDAO;
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
		
		String delivery_date = "";
			if(request.getParameter("delivery_date") != null) {
				delivery_date = request.getParameter("delivery_date");
			}else {
				LocalDate today = LocalDate.now();
				delivery_date = today.toString();
			}
		
		
		System.out.println("출근멤버조회api 지정날짜"+delivery_date);
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, delivery_date);
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
}
