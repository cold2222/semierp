package com.semi.warehouse.warehouseboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.warehouse.testcheck.InExWarehouseDAO;
import com.semi.warehouse.testcheck.allInExDTO;


public class WarehouseBoardTestDAO {

		private ArrayList<WarehouseBoardTestDTO> warehouseBoard;
		
		private static final WarehouseBoardTestDAO WBTDAO = new WarehouseBoardTestDAO();
		
		private WarehouseBoardTestDAO() {}
		
		 public static WarehouseBoardTestDAO getWbtdao() {
		        return WBTDAO;
		    }
		 public void paging(int pageNum, HttpServletRequest request) {
				int pageSize = 10; // 한 페이지당 보여줄 개수
				int totalData = warehouseBoard.size();
				int totalPage = (int)Math.ceil((double)totalData / pageSize);
				
				int startDataNum = totalData - (pageSize * (pageNum - 1));
				int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
				
				ArrayList<WarehouseBoardTestDTO> items = new ArrayList<WarehouseBoardTestDTO>();
				if(warehouseBoard.size() > 0) {
					for (int i = startDataNum-1; i > endDataNum; i--) {
						items.add(warehouseBoard.get(i));
					}
				}
				request.setAttribute("warehouseBoard", items);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("totalPage", totalPage);
				
			}
	
	public void getWBTest(HttpServletRequest request, String operationType) {
		String searchOption = request.getParameter("searchOption");
        String searchWord = request.getParameter("word");

        HashMap<String,String> search = new HashMap<String, String>();
        if (searchOption != null) {
        	search.put("searchOption", searchOption);
		}
        if(searchWord != null) {
        	search.put("searchWord", searchWord);	        	
        }
//        HashMap<String, String> search = new HashMap<>();
//        search.put("searchOption", (searchOption != null) ? searchOption : "x");
//        search.put("searchWord", (searchWord != null) ? searchWord : "");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product_test.p_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_si,\n"
				+ "    product_test.p_type,\n"
				+ "    product_test.p_unicost,\n"
				+ "    warehouse_test.warehouse_name,\n"
				+ "    manufacture_test.manufacture_name,\n"
				+ "    stock_test.stock,\n"
				+ "    sysdate AS today_date,\n"
				+ "    employee.e_name\n"
				+ "FROM\n"
				+ "    product_test\n"
				+ "JOIN\n"
				+ "    stock_test ON product_test.p_id = stock_test.p_id\n"
				+ "JOIN\n"
				+ "    warehouse_test ON stock_test.warehouse_id = warehouse_test.warehouse_id\n"
				+ "JOIN\n"
				+ "    manufacture_test ON product_test.p_manufacturer = manufacture_test.p_manufacturer\n"
				+ "JOIN\n"
				+ "    employee ON warehouse_test.e_employee_id = employee.e_employee_id\n";	
		 if (!"x".equals(search.get("searchOption")) && search.get("searchWord") != null) {
		        if ("manufacture_name".equals(search.get("searchOption"))) {
		            sql += "WHERE LOWER(manufacture_test." + search.get("searchOption") + ") LIKE LOWER('%" + search.get("searchWord") + "%') ";
		        } else {
		            sql += "WHERE LOWER(product_test." + search.get("searchOption") + ") LIKE LOWER('%" + search.get("searchWord") + "%') ";
		        }
		    }
		 
		 
		if ("one".equals(operationType)) {
            sql += " and warehouse_test.warehouse_id = 1 "
            		+ "ORDER BY\n"
            		+ "    product_test.p_type ASC,\n"
            		+ "    product_test.p_name ASC,\n"
            		+ "    product_test.p_quantity ASC";
        } else if ("two".equals(operationType)) {
            sql += " and warehouse_test.warehouse_id = 2"
            		+ "ORDER BY\n"
            		+ "    product_test.p_type ASC,\n"
            		+ "    product_test.p_name ASC,\n"
            		+ "    product_test.p_quantity ASC";
        } else if ("three".equals(operationType)) {
            sql += " and warehouse_test.warehouse_id = 3"
            		+ "ORDER BY\n"
            		+ "    product_test.p_type ASC,\n"
                    + "    product_test.p_name ASC,\n"
            		+ "    product_test.p_quantity ASC";
        } else {
			sql += "ORDER BY\n"
					+ "    product_test.p_type ASC,\n"
					+ "    product_test.p_name ASC,\n"
					+ "    product_test.p_quantity ASC";
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			warehouseBoard = new ArrayList<WarehouseBoardTestDTO>();
			WarehouseBoardTestDTO wb = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				int p_quantity = rs.getInt("p_quantity");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_unicost = rs.getInt("p_unicost");
				String warehouse_name = rs.getString("warehouse_name");
				String manufacture_name = rs.getString("manufacture_name");
				int stock = rs.getInt("stock");
				String today_date = rs.getString("today_date");
				String e_name = rs.getString("e_name");
				
				// p_id로 pk
				
				wb = new WarehouseBoardTestDTO();
				wb.setP_id(p_id);
				wb.setP_name(p_name);
				wb.setP_quantity(p_quantity);
				wb.setP_si(p_si);
				wb.setP_type(p_type);
				wb.setP_unicost(p_unicost);
				wb.setWarehouse_name(warehouse_name);
				wb.setManufacture_name(manufacture_name);
				wb.setStock(stock);
				wb.setToday_date(today_date);
				wb.setE_name(e_name);
				
				
				warehouseBoard.add(wb);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_quantity);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_unicost);
				System.out.println(warehouse_name);
				System.out.println(manufacture_name);
				System.out.println(stock);
				System.out.println(today_date);

			}
			request.setAttribute("warehouseBoard", warehouseBoard);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	
		
		
		
		
	}

	public void calcStock(HttpServletRequest request, String operationType) {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "";

	    
	    if ("one".equals(operationType)) {
	        sql = "SELECT\n"
	        		+ "    COALESCE(SUM(stock_test.stock * product_test.p_unicost), 0) AS total_stock\n"
	        		+ "FROM\n"
	        		+ "    warehouse_test\n"
	        		+ "LEFT JOIN\n"
	        		+ "    stock_test ON warehouse_test.warehouse_id = stock_test.warehouse_id\n"
	        		+ "LEFT JOIN\n"
	        		+ "    product_test ON stock_test.p_id = product_test.p_id\n"
	        		+ "WHERE warehouse_test.warehouse_id = 1\n";
        } else if ("two".equals(operationType)) {
	        sql = "SELECT\n"
	        		+ "    COALESCE(SUM(stock_test.stock * product_test.p_unicost), 0) AS total_stock\n"
	        		+ "FROM\n"
	        		+ "    warehouse_test\n"
	        		+ "LEFT JOIN\n"
	        		+ "    stock_test ON warehouse_test.warehouse_id = stock_test.warehouse_id\n"
	        		+ "LEFT JOIN\n"
	        		+ "    product_test ON stock_test.p_id = product_test.p_id\n"
	        		+ "WHERE warehouse_test.warehouse_id = 2\n";
        } else if ("three".equals(operationType)) {
	        sql = "SELECT\n"
	        		+ "    COALESCE(SUM(stock_test.stock * product_test.p_unicost), 0) AS total_stock\n"
	        		+ "FROM\n"
	        		+ "    warehouse_test\n"
	        		+ "LEFT JOIN\n"
	        		+ "    stock_test ON warehouse_test.warehouse_id = stock_test.warehouse_id\n"
	        		+ "LEFT JOIN\n"
	        		+ "    product_test ON stock_test.p_id = product_test.p_id\n"
	        		+ "WHERE warehouse_test.warehouse_id = 3\n";
        } else {
	        sql = "SELECT\n"
	        		+ "    COALESCE(SUM(stock_test.stock * product_test.p_unicost), 0) AS total_stock\n"
	        		+ "FROM\n"
	        		+ "    warehouse_test\n"
	        		+ "LEFT JOIN\n"
	        		+ "    stock_test ON warehouse_test.warehouse_id = stock_test.warehouse_id\n"
	        		+ "LEFT JOIN\n"
	        		+ "    product_test ON stock_test.p_id = product_test.p_id";
		}

	    
//	    // "all"이거나 빈 문자열 또는 null일 경우 모든 창고에 대한 총 재고 계산
//	    if ("all".equals(operationType) || operationType == null || operationType.isEmpty()) {
//	        sql = "SELECT warehouse_test.warehouse_id, warehouse_test.warehouse_name, COALESCE(SUM(stock_test.stock * product_test_ksj.p_unicost), 0) AS total_stock " +
//	              "FROM warehouse_test " +
//	              "LEFT JOIN stock_test ON warehouse_test.warehouse_id = stock_test.warehouse_id " +
//	              "LEFT JOIN product_test_ksj ON stock_test.p_id = product_test_ksj.p_id " +
//	              "GROUP BY warehouse_test.warehouse_id, warehouse_test.warehouse_name " +
//	              "ORDER BY warehouse_test.warehouse_id";
//	    } else {
//	        // 특정 창고에 대한 총 재고 계산 쿼리
//	        sql = "SELECT warehouse_test.warehouse_id, warehouse_test.warehouse_name, COALESCE(SUM(stock_test.stock * product_test_ksj.p_unicost), 0) AS total_stock " +
//	              "FROM warehouse_test " +
//	              "LEFT JOIN stock_test ON warehouse_test.warehouse_id = stock_test.warehouse_id " +
//	              "LEFT JOIN product_test_ksj ON stock_test.p_id = product_test_ksj.p_id " +
//	              "WHERE warehouse_test.warehouse_id = ? " +
//	              "GROUP BY warehouse_test.warehouse_id, warehouse_test.warehouse_name " +
//	              "ORDER BY warehouse_test.warehouse_id";
//	    }

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);

//	        if (!"all".equals(operationType) && operationType != null && !operationType.isEmpty()) {
//	            pstmt.setInt(1, Integer.parseInt(operationType));
//	        }

	        rs = pstmt.executeQuery();

	        ArrayList<WarehouseBoardTestDTO> totalStockList = new ArrayList<>();

	        while (rs.next()) {
	           // int warehouse_id = rs.getInt("warehouse_id");
	           // String warehouse_name = rs.getString("warehouse_name");
	            int total_stock = rs.getInt("total_stock");

	            WarehouseBoardTestDTO ts = new WarehouseBoardTestDTO();
	            ts.setTotal_stock(total_stock);

	            totalStockList.add(ts);
	        }

	        // 요청 속성으로 결과 저장
	        request.setAttribute("totalStockList", totalStockList);

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(con, pstmt, rs);
	    }

        
        
        
	}
		
		
	

}
