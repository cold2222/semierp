package com.semi.adminpage.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class DeptDAO {
	
	public static void getDeptInfo(HttpServletRequest request) {
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DeptDTO> deptInfo = new ArrayList<DeptDTO>();
		String sql = "select d_deptno, d_dept, count(d_dept) as d_count\r\n"
				+ "from employee\r\n"
				+ "join dept\r\n"
				+ "on e_deptno = d_deptno\r\n"
				+ "group by d_deptno, d_dept\r\n"
				+ "order by d_deptno";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("d_deptno")==999) {
					continue;
				}
				deptInfo.add(new DeptDTO(rs.getString("d_dept"),rs.getInt("d_deptno"),rs.getInt("d_count")));
				System.out.println(rs.getString("d_dept"));
			}
			
			request.setAttribute("deptInfo", deptInfo);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
	}
}
