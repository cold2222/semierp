package com.semi.adminpage.inform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public static void getBroadCastCompanyInfroms(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CompanyInformDTO> companyInforms = new ArrayList<CompanyInformDTO>();
		String sql = "select * from (select * from CompanyInform where ci_broadcastidx=1 order by ci_no desc) where rownum <6";
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
			request.setAttribute("broadCastInforms", companyInforms);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
	}

	public static void companyInformReg(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into CompanyInform values(seq_companyinfrom.nextval, ?, ?, sysdate, ?, ?)"; 

		try {
			con=DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ci_title"));
			pstmt.setString(2, request.getParameter("ci_content").replaceAll("/n/r","<br>"));;
			int ci_deptIdxCode = AdminUtils.sumCi_deptsValues(request.getParameterValues("ci_depts"));
			pstmt.setInt(3, ci_deptIdxCode == 11111 ? 1 : 0);
			pstmt.setInt(4, ci_deptIdxCode);
			if(pstmt.executeUpdate() > 0 ) {
				System.out.println("companyInformReg is success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, null);
		}
		
	}

	public static void getSessionDeptInforms(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CompanyInformDTO> companyInforms = new ArrayList<CompanyInformDTO>();
		String sql = "select * from (select * from companyInform where ci_broadcastidx=0 and mod(ci_deptidxcode / power(10,?), 10) = 1 order by ci_no desc) where rownum <6";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, AdminUtils.getSessionDeptIndex(request));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CompanyInformDTO tempCompanyInform = new CompanyInformDTO(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getDate(4), rs.getInt(5), rs.getInt(6));
				
				companyInforms.add(tempCompanyInform);
			}
			request.setAttribute("deptInforms", companyInforms);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
	}

}
