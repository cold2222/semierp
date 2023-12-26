package com.semi.adminpage.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.adminpage.util.AdminUtils;
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

	public static void getContractDeptInfo(HttpServletRequest request, int c_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContractDeptDTO contractDept = new ContractDeptDTO();
		int deptno = 100 + c_type;
		String attributeName = c_type == 1 ? "importDept" : "salesDept";
		String sql = "SELECT\r\n" + "    d_deptno as c_deptno,\r\n" + "    d_dept as c_dept,\r\n"
				+ "    COUNT(d_dept) AS c_count,\r\n" + "    (SELECT COUNT(DISTINCT c_contract_no)\r\n"
				+ "     FROM contract\r\n" + "     JOIN contract_items ON c_c_no = ci_c_contract_no\r\n"
				+ "     WHERE c_type = " + c_type
				+ " AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS c_contract_count,\r\n"
				+ "    (SELECT COUNT(c_contract_no)\r\n" + "     FROM contract\r\n"
				+ "     JOIN contract_items ON c_c_no = ci_c_contract_no\r\n" + "     WHERE c_type = " + c_type
				+ " AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS c_contract_items,\r\n"
				+ "    (SELECT SUM(ci_unit_price * ci_count)\r\n" + "     FROM contract\r\n"
				+ "     JOIN contract_items ON c_c_no = ci_c_contract_no\r\n" + "     WHERE c_type = " + c_type
				+ " AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS c_total_price,\r\n" + "     (SELECT COUNT(*)\r\n"
				+ "     FROM contract\r\n" + "     WHERE c_type = " + c_type
				+ " AND c_status = 4 AND TO_CHAR(c_created_date, 'YYYY-MM') LIKE ?) AS c_contract_completed,\r\n"
				+ "     (SELECT COUNT(*)\r\n" + "     FROM contract\r\n" + "     WHERE c_type = " + c_type
				+ " AND c_status < 4) AS c_awaiting_stock\r\n" + "FROM employee\r\n"
				+ "JOIN dept ON e_deptno = d_deptno\r\n" + "WHERE d_deptno = " + deptno + "\r\n"
				+ "GROUP BY d_deptno, d_dept";
		String currentYearMonth = AdminUtils.getCurrentYearMonth();

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentYearMonth);
			pstmt.setString(2, currentYearMonth);
			pstmt.setString(3, currentYearMonth);
			pstmt.setString(4, currentYearMonth);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				contractDept.setC_deptno(rs.getInt("c_deptno"));
				contractDept.setC_dept(rs.getString("c_dept"));
				contractDept.setC_count(rs.getInt("c_count"));
				contractDept.setC_contract_count(rs.getInt("c_contract_count"));
				contractDept.setC_contract_items(rs.getInt("c_contract_items"));
				contractDept.setC_total_price(rs.getInt("c_total_price"));
				contractDept.setC_contract_completed(rs.getInt("c_contract_completed"));
				contractDept.setC_awaiting_stock(rs.getInt("c_awaiting_stock"));

				request.setAttribute(attributeName, contractDept);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
}
