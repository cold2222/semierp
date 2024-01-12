package com.semi.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.adminpage.util.AdminUtils;
import com.semi.distribution.db.DBManger;
import com.semi.login.EmployeeDTO;

public class CompanyNoticeDAO {
	public static void regContractNotice(int c_type, int c_contract_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dept = "販売";
		String  url = "http://localhost:8080/semi_erp_project/DistributionDeliverySaleViewC?c_contract_no=" + c_contract_id + "&&page=List";
		if(c_type == 1) {
			dept = "受領";
			url = "http://localhost:8080/semi_erp_project/DistributionReceiptViewC?c_contract_no=" + c_contract_id + "&page=List";
		}
		String sql = "insert into CompanyNotice values(seq_companyNotice.nextval, '配車を待つ" + dept + "契約があります。', sysdate, 6, 20102, ?)";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, url);
			if(pstmt.executeUpdate() == 1) {
				System.out.println("regNotice success");
				String currvalQuery = "SELECT seq_companyNotice.CURRVAL FROM dual";
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(currvalQuery);
				if (rs.next()) {
					int currval = rs.getInt(1); // 시퀀스의 현재 값
					System.out.println("current notice_seq: " + currval);
					regCheckCompanyNoticeByDeptRank(currval, 201, "部長");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("regNotice error");
		} finally {
			DBManger.close(con, pstmt, rs);
		}		
		
	}	
	
	public static void regCheckCompanyNoticeByDeptNo(int seq_no, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO checkcompanynotice (ccn_e_no, ccn_cn_no, ccn_checked) SELECT e_no, ?, 0 FROM employee WHERE e_deptno = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq_no);
			pstmt.setInt(2, deptno);
			if(pstmt.executeUpdate() > 0) {
				System.out.println("regCheckCompanyNotice success");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("regCheckCompanyNotice error");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
	}
	
	public static void regCheckCompanyNoticeByDeptRank(int seq_no, int deptno, String rank) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO checkcompanynotice (ccn_e_no, ccn_cn_no, ccn_checked) SELECT e_no, ?, 0 FROM employee WHERE e_deptno = ? and e_rank = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq_no);
			pstmt.setInt(2, deptno);
			pstmt.setString(3, rank);
			if(pstmt.executeUpdate() > 0) {
				System.out.println("regCheckCompanyNotice success");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("regCheckCompanyNotice error");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
	}
	
	public static void getMainPageNotice(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CompanyNoticeDTO> noticeArr = new ArrayList<CompanyNoticeDTO>();
		String sql = "select * from companynotice join checkcompanynotice on ccn_cn_no = cn_no where ccn_e_no = ? and ccn_checked = 0";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			EmployeeDTO empInfo = (EmployeeDTO) request.getSession().getAttribute("empInfo");
			pstmt.setInt(1, Integer.parseInt(empInfo.getE_no()));
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CompanyNoticeDTO tempCN = new CompanyNoticeDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getDate(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9));
				noticeArr.add(tempCN);
				
					
			}
			
			request.setAttribute("notices", AdminUtils.setPaging(request, noticeArr, 10));
			System.out.println("getMainPageNotice success");	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("regCheckCompanyNotice error");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

}
