package com.semi.adminpage.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.adminpage.util.AdminUtils;
import com.semi.distribution.db.DBManger;
import com.semi.login.Encrypt;

public class StaffDAO {
	// 직원 일관 페이지 직원'들' 정보
	public static void getStaffsInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StaffDTO> staffsInfo = new ArrayList<StaffDTO>();
		String sql = "select e_deptno, d_dept as e_dept, e_no, e_name, e_rank, e_tel, e_email, e_joined_company\r\n"
				+ "from employee\r\n" + "join dept\r\n" + "on e_deptno = d_deptno\r\n" + "order by e_deptno, e_no";
		
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
	        request.setAttribute("staffsInfo", AdminUtils.setPaging(request, staffsInfo, 15));
			
	        
	        	

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}
	// 직원 개인 정보
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

	// 직원 정보 수정
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
	
	// 직원의 로그인 비밀번호를 아이디와 같은 값으로 초기화
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
	
	// 직원 등록(로그인 비밀 번호 자신의 아이디 값으로 초기화)
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
	
	// 계약관련 부서 스테프들의 정보 반환 c_type값 1일 경우 수입, 2일 경우 수출
	// input 파라미터로 날자값 받아서 월을 기준으로 통계값 반환
	public static void getContractStaffsInfo(HttpServletRequest request, int c_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ContractStaffDTO> contractStaffsInfo = new ArrayList<ContractStaffDTO>();
		int deptno = 100 + c_type;
		String sql = "select cs_no, cs_name, cs_rank, cs_tel, cs_email, count(distinct c_contract_no) as cs_thisMonthContract, count(distinct p_id) as cs_thisMonthProduct, (sum(nvl(ci_count, 0) * nvl(ci_unit_price,0))) as cs_thisMonthTotalPrice\r\n"
				+ "from\r\n"
				+ "(select e_no as cs_no, e_name as cs_name, e_rank as cs_rank, e_tel as cs_tel, e_email as cs_email  from employee where e_deptno=" + deptno + ")\r\n"
				+ "left outer join \r\n"
				+ "(select * \r\n"
				+ "from contract\r\n"
				+ "join contract_items \r\n"
				+ "on c_contract_no = ci_c_contract_no \r\n"
				+ "join product on ci_p_id = p_id\r\n"
				+ "where c_type = " + c_type +" and to_char(c_created_date,'yyyy-MM') = ?)\r\n"
				+ "on cs_no = c_e_id\r\n"
				+ "group by  cs_no, cs_name, cs_rank, cs_tel, cs_email";
		String currentYearMonth = AdminUtils.getParamYearMonth(request);
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentYearMonth);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ContractStaffDTO tempContractStaffInfo = new ContractStaffDTO();
				tempContractStaffInfo.setCs_no(rs.getInt(1));
				tempContractStaffInfo.setCs_name(rs.getString(2));
				tempContractStaffInfo.setCs_rank(rs.getString(3));
				tempContractStaffInfo.setCs_tel(rs.getString(4));
				tempContractStaffInfo.setCs_email(rs.getString(5));
				tempContractStaffInfo.setCs_thisMonthContract(rs.getInt(6));
				tempContractStaffInfo.setCs_thisMonthProduct(rs.getInt(7));
				tempContractStaffInfo.setCs_thisMonthTotalPrice(rs.getInt(8));
				
				contractStaffsInfo.add(tempContractStaffInfo);
			}
			
			
			
			request.setAttribute("contractStaffsInfo", AdminUtils.setPaging(request, contractStaffsInfo, 10));
			System.out.println("getContractStaffsInfo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	
		
		
		
	}
	public static void getDistributionStaffsInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
