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
				contractDept.setC_deptno(rs.getInt(1));
				contractDept.setC_dept(rs.getString(2));
				contractDept.setC_count(rs.getInt(3));
				contractDept.setC_contract_count(rs.getInt(4));
				contractDept.setC_contract_items(rs.getInt(5));
				contractDept.setC_total_price(rs.getInt(6));
				contractDept.setC_contract_completed(rs.getInt(7));
				contractDept.setC_awaiting_stock(rs.getInt(8));

				request.setAttribute(attributeName, contractDept);
				
				System.out.println("getContractDeptInfo : " + attributeName);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
	public static void getDistributionDeptInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DistributionDeptDTO distributionDept = new DistributionDeptDTO();
		String sql = "SELECT\r\n"
				+ "    d.d_deptno,\r\n"
				+ "    d.d_dept,\r\n"
				+ "    (SELECT COUNT(*) FROM employee e\r\n"
				+ "     JOIN dept d ON e.e_deptno = d.d_deptno\r\n"
				+ "     WHERE d.d_deptno = 201) AS d_count,\r\n"
				+ "    (SELECT COUNT(*) FROM contract WHERE c_status = 1) AS d_waiting,\r\n"
				+ "    (SELECT COUNT(*) FROM contract WHERE c_status = 2) AS d_allocated,\r\n"
				+ "    (SELECT COUNT(*) FROM contract WHERE c_status = 2 AND TO_CHAR(c_delivery_date, 'yyyy-mm') = ?) AS d_allocatedThisMonth,\r\n"
				+ "    (SELECT COUNT(*) FROM contract WHERE (c_status = 3 OR c_status=4) AND TO_CHAR(c_delivery_date, 'yyyy-mm') = ?) AS d_completedThisMonth,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE c_status < 3 AND (TRUNC(c_due_date) < TRUNC(SYSDATE) OR TRUNC(c_delivery_date) < TRUNC(SYSDATE))) as d_expired,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE c_status < 3 AND (TRUNC(c_due_date) = TRUNC(SYSDATE) OR TRUNC(c_delivery_date) = TRUNC(SYSDATE))) as d_dueDate,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE TRUNC(c_delivery_date) = TRUNC(SYSDATE)) as d_todayDelivery,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE (c_status = 3 OR c_status = 4) AND TRUNC(c_delivery_date) = TRUNC(SYSDATE)) as d_todayCompleted\r\n"
				+ "FROM dept d\r\n"
				+ "WHERE d.d_deptno = 201";
		String currentYearMonth = AdminUtils.getCurrentYearMonth();

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentYearMonth);
			pstmt.setString(2, currentYearMonth);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				distributionDept.setD_deptno(rs.getInt(1));
				distributionDept.setD_dept(rs.getString(2));
				distributionDept.setD_count(rs.getInt(3));
				distributionDept.setD_waiting(4);
				distributionDept.setD_allocated(5);
				distributionDept.setD_allocatedThisMonth(6);
				distributionDept.setD_completedThisMonth(7);
				distributionDept.setD_expired(rs.getInt(8));
				distributionDept.setD_dueDate(rs.getInt(9));
				distributionDept.setD_todayDelivery(10);
				distributionDept.setD_todayCompleted(11);
				
				request.setAttribute("distributionDept", distributionDept);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
}
