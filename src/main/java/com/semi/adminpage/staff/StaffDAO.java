package com.semi.adminpage.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.login.Encrypt;

public class StaffDAO {

	public static void getStaffsInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StaffDTO> staffsInfo = new ArrayList<StaffDTO>();
		String sql = "select e_deptno, d_dept as e_dept, e_no, e_name, e_rank, e_tel, e_email, e_joined_company\r\n"
				+ "from employee\r\n" + "join dept\r\n" + "on e_deptno = d_deptno\r\n" + "order by e_deptno, e_no";
		int[] indexList = new int[5];
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

			//request.setAttribute("staffsInfo", staffsInfo);
			System.out.println("getStaffsInfo");
			
			// 페이징
	        int totalItems = staffsInfo.size();
	        int itemsPerPage = 15;
	        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
	        int currentPage = 1;
	        String pageNoParam = request.getParameter("pageNo");
	        if (pageNoParam != null && !pageNoParam.isEmpty())
	        	currentPage = Integer.parseInt(pageNoParam);
	        
	        int startIndex = (currentPage - 1) * itemsPerPage;
	        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("lastPage", totalPages);
	        request.setAttribute("staffsInfo", staffsInfo.subList(startIndex, endIndex));
	        
	        int startPageIndex = 1;
	        int count = 0;
	        if(currentPage > 3 && totalPages > 5) {
	        	startPageIndex = currentPage-2;
	        	for(int i = startPageIndex; i <= currentPage + 2; i++)
	        		indexList[count++] = i;
	        } else {
	        	for(int i = startPageIndex; i <= totalPages; i++)
	        		indexList[count++] = i;
	        }
	        request.setAttribute("indexList", indexList);
	        
	        	

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
			System.out.println("getStaffInfo : " + request.getParameter("e_no"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		

	}

	public static void modifyStaff(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update employee set e_deptno=?, e_rank=?, e_tel=?, e_email=? where e_no=?";
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(request.getParameter("d_deptno")));
			pstmt.setString(2, request.getParameter("e_rank"));
			pstmt.setString(3, request.getParameter("e_tel"));
			pstmt.setString(4, request.getParameter("e_email"));
			pstmt.setInt(5, Integer.parseInt(request.getParameter("e_no")));
			
			if(pstmt.executeUpdate()>0)
				System.out.println("modifyStaff : "+ request.getParameter("e_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
		
		
		
	}

	public static void resetPW(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update employee set e_pw=? where e_no=?";
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			String strE_no = request.getParameter("e_no");
			
			pstmt.setString(1, Encrypt.getPW(strE_no, strE_no));
			pstmt.setInt(2, Integer.parseInt(strE_no));
			
			if(pstmt.executeUpdate()>0)
				System.out.println("resetPW : " + strE_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		}
	}

	public static void staffReg(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?,  to_date(?, 'yyyy-mm-dd'))";
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(request.getParameter("e_no")));
			pstmt.setString(2, Encrypt.getPW(request.getParameter("e_no"), request.getParameter("e_no")));
			pstmt.setInt(3, Integer.parseInt(request.getParameter("d_deptno")));
			pstmt.setString(4, request.getParameter("e_name"));
			pstmt.setString(5, request.getParameter("e_rank"));
			pstmt.setString(6, request.getParameter("e_tel"));
			pstmt.setString(7, request.getParameter("e_email"));
			pstmt.setString(8, request.getParameter("e_joined_company"));
			
			if(pstmt.executeUpdate()>0) {
				System.out.println("StaffReg : " + request.getParameter("e_no"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		}
		
	}

}
