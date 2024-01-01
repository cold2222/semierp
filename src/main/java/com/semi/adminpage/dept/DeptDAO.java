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
	// 부서 테이블 정보(부서 코드, 부서 이름)
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
	
	// 부서 정보(부서 코드, 부서이름, 소속 인원수)
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
	
	// 계약관련 부서 정보(c_type = 1 수입부서 정보 c_type = 2 판매 부서 정보 날짜(월)에 따른 통계 데이터 함)
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
		String currentYearMonth = AdminUtils.getParamYearMonth(request);
		System.out.println(currentYearMonth);

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
	
	// 유통(운송) 부서정보 월과 일별 통계 데이터 포함
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
				+ "    (SELECT count(*) FROM contract WHERE c_status < 3 AND ((to_char(c_due_date ,'YYYY-MM-DD') < ? AND c_type = 2) OR to_char(c_delivery_date ,'YYYY-MM-DD') < ?)) as d_expired,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE c_status < 3 AND ((to_char(c_due_date, 'YYYY-MM-DD') = ? AND c_type = 2) OR to_char(c_delivery_date, 'YYYY-MM-DD') = ?)) as d_dueDate,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE to_char(c_delivery_date,'YYYY-MM-DD') = ?) as d_todayDelivery,\r\n"
				+ "    (SELECT count(*) FROM contract WHERE (c_status = 3 OR c_status = 4) AND to_char(c_delivery_date, 'YYYY-MM-DD') = ?) as d_todayCompleted\r\n"
				+ "FROM dept d\r\n"
				+ "WHERE d.d_deptno = 201";
		String currentYearMonth = AdminUtils.getParamYearMonth(request);
		String currentDate = AdminUtils.getParamDate(request);

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentYearMonth);
			pstmt.setString(2, currentYearMonth);
			pstmt.setString(3, currentDate);
			pstmt.setString(4, currentDate);
			pstmt.setString(5, currentDate);
			pstmt.setString(6, currentDate);
			pstmt.setString(7, currentDate);
			pstmt.setString(8, currentDate);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				distributionDept.setD_deptno(rs.getInt(1));
				distributionDept.setD_dept(rs.getString(2));
				distributionDept.setD_count(rs.getInt(3));
				distributionDept.setD_waiting(rs.getInt(4));
				distributionDept.setD_allocated(rs.getInt(5));
				distributionDept.setD_allocatedThisMonth(rs.getInt(6));
				distributionDept.setD_completedThisMonth(rs.getInt(7));
				distributionDept.setD_expired(rs.getInt(8));
				distributionDept.setD_dueDate(rs.getInt(9));
				distributionDept.setD_todayDelivery(rs.getInt(10));
				distributionDept.setD_todayCompleted(rs.getInt(11));
				
				request.setAttribute("distributionDept", distributionDept);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "DBFail");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
	// 창고부서 정보 In, Out 각각을 월 일 데이터로 
	public static void getWarehouseDeptInfo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WarehouseDeptDTO warehouseDept = new WarehouseDeptDTO();
		String sql = "SELECT\r\n"
				+ "				d_deptno AS w_deptno,\r\n"
				+ "				    d_dept AS w_dept,\r\n"
				+ "				    COUNT(*) AS w_count,\r\n"
				+ "				    (SELECT COUNT(DISTINCT p_id) FROM stock) AS w_products,\r\n"
				+ "				    (SELECT SUM(stock * p_unitcost)\r\n"
				+ "				     FROM product\r\n"
				+ "				     JOIN (\r\n"
				+ "				         SELECT p_id, SUM(rm_stock) AS stock\r\n"
				+ "				         FROM stock\r\n"
				+ "				         GROUP BY p_id\r\n"
				+ "				     ) b ON product.p_id = b.p_id) AS w_value,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM (\r\n"
				+ "				         SELECT\r\n"
				+ "				             product.p_id,\r\n"
				+ "				             product.p_minstock,\r\n"
				+ "				             product.p_maxstock,\r\n"
				+ "				             NVL(sum_stock, 0) AS stocks\r\n"
				+ "				         FROM\r\n"
				+ "				             product\r\n"
				+ "				             LEFT OUTER JOIN (\r\n"
				+ "				                 SELECT p_id, SUM(rm_stock) AS sum_stock\r\n"
				+ "				                 FROM stock\r\n"
				+ "				                 GROUP BY p_id\r\n"
				+ "				             ) b ON b.p_id = product.p_id\r\n"
				+ "				     )\r\n"
				+ "				     WHERE p_minstock > stocks) AS w_underMinStock,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM (\r\n"
				+ "				         SELECT\r\n"
				+ "				             product.p_id,\r\n"
				+ "				             product.p_minstock,\r\n"
				+ "				             product.p_maxstock,\r\n"
				+ "				             NVL(sum_stock, 0) AS stocks\r\n"
				+ "				         FROM\r\n"
				+ "				             product\r\n"
				+ "				             LEFT OUTER JOIN (\r\n"
				+ "				                 SELECT p_id, SUM(rm_stock) AS sum_stock\r\n"
				+ "				                 FROM stock\r\n"
				+ "				                 GROUP BY p_id\r\n"
				+ "				             ) b ON b.p_id = product.p_id\r\n"
				+ "				     )\r\n"
				+ "				     WHERE p_maxstock < stocks) AS w_overMaxStock,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 1 AND c_status = 4 AND TO_CHAR(c_completed_date, 'YYYY-MM') = ?) AS w_stockInCompletedThisMonth,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 1 AND (c_status = 2 OR c_status = 3) AND TO_CHAR(c_delivery_date, 'YYYY-MM') = ?) AS w_watingStockInThisMonth,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 1 AND c_status = 4 AND TO_CHAR(c_completed_date, 'YYYY-MM-DD') = ?) AS w_stockInToday,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 1 AND (c_status = 2 OR c_status = 3) AND TO_CHAR(c_delivery_date, 'YYYY-MM-DD') = ?) AS w_stockInCompletedToday,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 2 AND c_status = 4 AND TO_CHAR(c_completed_date, 'YYYY-MM') = ?) AS w_stockOutCompletedThisMonth,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 2 AND (c_status = 2 OR c_status = 3) AND TO_CHAR(c_delivery_date, 'YYYY-MM') = ?) AS w_watingStockOutThisMonth,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 2 AND TO_CHAR(c_completed_date, 'YYYY-MM-DD') = ?) AS w_stockOutToday,\r\n"
				+ "				    (SELECT COUNT(*)\r\n"
				+ "				     FROM contract\r\n"
				+ "				     WHERE c_type = 2 AND c_status = 4 AND TO_CHAR(c_delivery_date, 'YYYY-MM-DD') = ?) AS w_stockOutCompletedToday\r\n"
				+ "				FROM\r\n"
				+ "				    dept\r\n"
				+ "				    JOIN employee ON d_deptno = e_deptno\r\n"
				+ "				WHERE\r\n"
				+ "				    e_deptno = 202\r\n"
				+ "				GROUP BY\r\n"
				+ "				    d_dept, d_deptno";
		String currentYearMonth = AdminUtils.getParamYearMonth(request);
		String currentDate = AdminUtils.getParamDate(request);

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentYearMonth);
			pstmt.setString(2, currentYearMonth);
			pstmt.setString(3, currentDate);
			pstmt.setString(4, currentDate);
			pstmt.setString(5, currentYearMonth);
			pstmt.setString(6, currentYearMonth);
			pstmt.setString(7, currentDate);
			pstmt.setString(8, currentDate);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				warehouseDept.setW_deptno(rs.getInt(1));
				warehouseDept.setW_dept(rs.getString(2));
				warehouseDept.setW_count(rs.getInt(3));
				warehouseDept.setW_products(rs.getInt(4));
				warehouseDept.setW_value(rs.getLong(5));
				warehouseDept.setW_underMinStock(rs.getInt(6));
				warehouseDept.setW_overMaxStock(rs.getInt(7));
				warehouseDept.setW_stockInCompletedThisMonth(8);
				warehouseDept.setW_watingStockInThisMonth(rs.getInt(9));
				warehouseDept.setW_stockInCompletedThisMonth(rs.getInt(10));
				warehouseDept.setW_stockInToday(rs.getInt(11));
				warehouseDept.setW_stockInCompletedToday(rs.getInt(12));
				warehouseDept.setW_stockOutCompletedThisMonth(rs.getInt(13));
				warehouseDept.setW_stockOutToday(rs.getInt(14));
				warehouseDept.setW_stockOutCompletedToday(rs.getInt(15));
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
