package com.semi.adminpage.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class DeptDAO {

	public static void getDeptsInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DeptDTO> deptsInfo = new ArrayList<DeptDTO>();
		String sql = "select d_deptno, d_dept, count(d_dept) as d_count\r\n" + "from employee\r\n" + "join dept\r\n"
				+ "on e_deptno = d_deptno\r\n" + "group by d_deptno, d_dept\r\n" + "order by d_deptno";
		int staffSum = 0;
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("d_deptno") == 999)
					continue;
				staffSum += rs.getInt("d_count");
				deptsInfo.add(new DeptDTO(rs.getString("d_dept"), rs.getInt("d_deptno"), rs.getInt("d_count")));
			}

			request.setAttribute("deptsInfo", deptsInfo);
			request.setAttribute("staffSum", staffSum);
			System.out.println("getDeptsInfo");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public static void getImportDeptInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ImportDeptDTO importDept = new ImportDeptDTO();
		String sql = "SELECT\r\n" + "    d_deptno as i_deptno,\r\n" + "    d_dept as i_dept,\r\n"
				+ "    COUNT(d_dept) AS i_count,\r\n" + "    (SELECT COUNT(DISTINCT c_contract_no)\r\n"
				+ "     FROM contract\r\n" + "     JOIN contract_items ON c_c_no = ci_c_contract_no\r\n"
				+ "     WHERE c_type = 1 AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS i_contract_count,\r\n"
				+ "    (SELECT COUNT(c_contract_no)\r\n" + "     FROM contract\r\n"
				+ "     JOIN contract_items ON c_c_no = ci_c_contract_no\r\n"
				+ "     WHERE c_type = 1 AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS i_contract_items,\r\n"
				+ "    (SELECT SUM(ci_unit_price * ci_count)\r\n" + "     FROM contract\r\n"
				+ "     JOIN contract_items ON c_c_no = ci_c_contract_no\r\n"
				+ "     WHERE c_type = 1 AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS i_total_price,\r\n"
				+ "     (SELECT COUNT(*)\r\n" + "     FROM contract\r\n"
				+ "     WHERE c_type = 1 AND c_status = 4 AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS I_contract_completed,\r\n"
				+ "     (SELECT COUNT(*)\r\n" + "     FROM contract\r\n"
				+ "     WHERE c_type = 1 AND c_status < 4) AS i_awaiting_stock\r\n" + "FROM employee\r\n"
				+ "JOIN dept ON e_deptno = d_deptno\r\n" + "WHERE d_deptno = 101\r\n" + "GROUP BY d_deptno, d_dept";

		// 현재 날짜 정보 가져오기
		LocalDate currentDate = LocalDate.now();

		// 형식화된 년월 가져오기 (YY-MM 형식)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		String currentYearMonth = currentDate.format(formatter);
		request.setAttribute("currentYearMonth", currentYearMonth);
		
		try {
			con=DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentYearMonth);
			pstmt.setString(2, currentYearMonth);
			pstmt.setString(3, currentYearMonth);
			pstmt.setString(4, currentYearMonth);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				importDept.setI_deptno(rs.getInt("i_deptno"));
				importDept.setI_dept(rs.getString("i_dept"));
				importDept.setI_count(rs.getInt("i_count"));
				importDept.setI_contract_count(rs.getInt("i_contract_count"));
				importDept.setI_contract_items(rs.getInt("i_contract_items"));
				importDept.setI_total_price(rs.getInt("i_total_price"));
				importDept.setI_contract_completed(rs.getInt("i_contract_completed"));
				importDept.setI_awaiting_stock(rs.getInt("i_awaiting_stock"));
				
				request.setAttribute("importDept", importDept);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
}
