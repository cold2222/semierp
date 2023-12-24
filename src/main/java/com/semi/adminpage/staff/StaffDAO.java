package com.semi.adminpage.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class StaffDAO {

	public static void getStaffsInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StaffDTO> staffsInfo = new ArrayList<StaffDTO>();
		String sql = "select e_deptno, d_dept as e_dept, e_no, e_name, e_rank, e_tel, e_email, e_joined_company\r\n"
				+ "from employee\r\n" + "join dept\r\n" + "on e_deptno = d_deptno\r\n" + "order by e_deptno";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("e_deptno") == 999 || rs.getInt("e_deptno") == 998)
					continue;

				StaffDTO tempStaff = new StaffDTO();
				tempStaff.setE_deptno(rs.getInt("e_deptno"));
				tempStaff.setE_dept(rs.getString("e_dept"));
				tempStaff.setE_no(rs.getInt("e_no"));
				tempStaff.setE_name(rs.getString("e_name"));
				tempStaff.setE_rank(rs.getString("e_rank"));
				tempStaff.setE_email(rs.getString("e_email"));
				tempStaff.setE_tel(rs.getString("e_tel"));
				tempStaff.setE_joined_company(rs.getDate("e_joined_company"));
				staffsInfo.add(tempStaff);
			}

			request.setAttribute("staffsInfo", staffsInfo);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public static void getStaffInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StaffDTO tempStaff= null;
		String sql = "select e_deptno, d_dept as e_dept, e_no, e_name, e_rank, e_tel, e_email, e_joined_company\r\n"
				+ "from employee\r\n"
				+ "join dept\r\n"
				+ "on e_deptno = d_deptno\r\n"
				+ "where e_no=?";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("e_no"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				tempStaff = new StaffDTO();
				tempStaff.setE_deptno(rs.getInt("e_deptno"));
				tempStaff.setE_dept(rs.getString("e_dept"));
				tempStaff.setE_no(rs.getInt("e_no"));
				tempStaff.setE_name(rs.getString("e_name"));
				tempStaff.setE_rank(rs.getString("e_rank"));
				tempStaff.setE_email(rs.getString("e_email"));
				tempStaff.setE_tel(rs.getString("e_tel"));
				tempStaff.setE_joined_company(rs.getDate("e_joined_company"));
				
			}
			request.setAttribute("staffInfo", tempStaff);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		

	}

}
