package com.semi.distribution.shift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.semi.distribution.db.DBManger;

public class PaidVacationDAO {

	public static ArrayList<ShiftDTO> getPaidVacation() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select ds.e_no, ds.work_num, TO_CHAR(ds.work_date, 'YYYY-MM-DD') as work_date, e.e_name"
				+ " from distribution_shift ds inner join employee e on ds.e_no = e.e_no where work_num = 3 order by e_name";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<ShiftDTO> workList = new ArrayList<ShiftDTO>();
			ShiftDTO work = null;
			while(rs.next()) {
				work = new ShiftDTO(rs.getString(1),rs.getString(2),rs.getString(3));
				workList.add(work);
			}
			
			return workList;
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
		return null;
		
	}
}
