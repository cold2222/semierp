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
	public static void getDepts(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DeptDTO> deptsInfo = new ArrayList<DeptDTO>();
		String sql = "select * from dept order by d_deptno";
		
		
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("d_deptno") == 999)
					continue;
				DeptDTO dept = new DeptDTO();
				dept.setD_deptNo(rs.getInt(1));
				dept.setD_dept(rs.getString(2));
				deptsInfo.add(dept);
			}

			request.setAttribute("deptsInfo", deptsInfo);
			System.out.println("getDepts");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public static void getDeptsInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DeptDTO> deptsInfo = new ArrayList<DeptDTO>();
		String sql = "select d_deptno, d_dept, count(d_dept) as d_count\r\n" + "from employee\r\n" + "right outer join dept\r\n"
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
				+ "    (SELECT count(*) FROM contract WHERE c_status < 3 AND ((TRUNC(c_due_date) < TRUNC(SYSDATE) AND c_type = 2) OR TRUNC(c_delivery_date) < TRUNC(SYSDATE))) as d_expired,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE c_status < 3 AND ((TRUNC(c_due_date) = TRUNC(SYSDATE) AND c_type = 2) OR TRUNC(c_delivery_date) = TRUNC(SYSDATE))) as d_dueDate,\r\n"
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
	
	public static void getWarehouseDeptInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WarehouseDeptDTO warehouseDept = new WarehouseDeptDTO();
		String sql = "select d_deptno as w_deptno, d_dept as w_dept, count(*) as w_count,\r\n"
				+ "    (select count(distinct p_id) from stock) as w_products,\r\n"
				+ "    (select sum(stock * p_unitcost)\r\n"
				+ "from product join \r\n"
				+ "(select p_id, sum(rm_stock) as stock from stock group by p_id) b\r\n"
				+ "on product.p_id = b.p_id) as w_value,\r\n"
				+ "(select count(*)\r\n"
				+ "from (select product.p_id, product.p_minstock, product.p_maxstock, nvl(sum_stock,0) as stocks from product left outer join (select p_id, sum(rm_stock) as sum_stock from stock group by p_id) b on b.p_id = product.p_id)\r\n"
				+ "where p_minstock > stocks) as w_underMinStock,\r\n"
				+ "(select count(*)\r\n"
				+ "from (select product.p_id, product.p_minstock, product.p_maxstock, nvl(sum_stock,0) as stocks from product left outer join (select p_id, sum(rm_stock) as sum_stock from stock group by p_id) b on b.p_id = product.p_id)\r\n"
				+ "where p_maxstock < stocks) as w_overMaxStock,\r\n"
				+ "(select count(*) from contract where c_type = 1 and c_status=4 and to_char(c_completed_date,'yyyy-mm')=?) as w_stockInCompletedThisMonth,\r\n"
				+ "(select count(*) from contract where c_type = 1 and (c_status=2 or c_status=3) and to_char(c_delivery_date,'yyyy-mm')=?) as w_watingStockInThisMonth,\r\n"
				+ "(select count(*) from contract where c_type = 1 and c_status=4 AND TRUNC(c_completed_date) = TRUNC(SYSDATE)) as w_stockInToday,\r\n"
				+ "(select count(*) from contract where c_type = 1 and (c_status=2 or c_status=3) AND TRUNC(c_delivery_date) = TRUNC(SYSDATE)) as w_stockInCompletedToday,\r\n"
				+ "(select count(*) from contract where c_type = 2 and c_status=4 and to_char(c_completed_date,'yyyy-mm-dd')=?) as w_stockOutCompletedThisMonth,\r\n"
				+ "(select count(*) from contract where c_type = 2 and (c_status=2 or c_status=3) and to_char(c_delivery_date,'yyyy-mm')=?) as w_watingStockOutThisMonth,\r\n"
				+ "(select count(*) from contract where c_type = 2 and c_status=4 AND TRUNC(c_completed_date) = TRUNC(SYSDATE)) as w_stockOutToday,\r\n"
				+ "(select count(*) from contract where c_type = 2 and (c_status=2 or c_status=3) AND TRUNC(c_delivery_date) = TRUNC(SYSDATE)) as w_stockOutCompletedToday\r\n"
				+ "from dept\r\n"
				+ "join employee\r\n"
				+ "on d_deptno = e_deptno\r\n"
				+ "where e_deptno = 202\r\n"
				+ "group by d_dept, d_deptno";
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
				warehouseDept.setW_deptno(rs.getInt(1));
				warehouseDept.setW_dept(rs.getString(2));
				warehouseDept.setW_count(rs.getInt(3));
				warehouseDept.setW_products(rs.getInt(4));
				warehouseDept.setW_value(rs.getLong(5));
				warehouseDept.setW_underMinStock(6);
				warehouseDept.setW_overMaxStock(7);
				warehouseDept.setW_stockInCompletedThisMonth(8);
				warehouseDept.setW_watingStockInThisMonth(9);
				warehouseDept.setW_stockInToday(10);
				warehouseDept.setW_stockInCompletedToday(11);
				warehouseDept.setW_stockOutCompletedThisMonth(12);
				warehouseDept.setW_stockOutToday(14);
				warehouseDept.setW_stockOutCompletedToday(15);
				request.setAttribute("warehouseDept", warehouseDept);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
}
