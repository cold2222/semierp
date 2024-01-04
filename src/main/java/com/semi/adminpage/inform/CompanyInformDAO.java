package com.semi.adminpage.inform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.adminpage.util.AdminUtils;
import com.semi.distribution.db.DBManger;

public class CompanyInformDAO {
	public static void getCompanyInfroms(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CompanyInformDTO> companyInforms = new ArrayList<CompanyInformDTO>();
		String sql = "select * from CompanyInform order by ci_no desc";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CompanyInformDTO tempCompanyInform = new CompanyInformDTO(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getDate(4), rs.getInt(5), rs.getInt(6));
				
				companyInforms.add(tempCompanyInform);
			}
			request.setAttribute("companyInforms", AdminUtils.setPaging(request, companyInforms, 18));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
	}

}
