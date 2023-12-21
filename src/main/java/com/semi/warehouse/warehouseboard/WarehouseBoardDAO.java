package com.semi.warehouse.warehouseboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.warehouse.inexwarehouse.InExWarehouseDAO;
import com.semi.warehouse.inexwarehouse.InExWarehouseDTO;


public class WarehouseBoardDAO {

		private ArrayList<WarehouseBoardDTO> warehouseBoard;
		
		private static final WarehouseBoardDAO WBTDAO = new WarehouseBoardDAO();
		
		private WarehouseBoardDAO() {}
		
		 public static WarehouseBoardDAO getWbtdao() {
		        return WBTDAO;
		    }
		 
		 public void paging(int pageNum, HttpServletRequest request) {
				int pageSize = 10; // 한 페이지당 보여줄 개수
				int totalData = warehouseBoard.size();
				int totalPage = (int)Math.ceil((double)totalData / pageSize);
				
				int startDataNum = totalData - (pageSize * (pageNum - 1));
				int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
				
				ArrayList<WarehouseBoardDTO> items = new ArrayList<WarehouseBoardDTO>();
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
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product.p_id,\n"
				+ "    product.p_name,\n"
				+ "    product.p_quantity,\n"
				+ "    product.p_si,\n"
				+ "    product.p_type,\n"
				+ "    product.p_unicost,\n"
				+ "    warehouse.warehouse_name,\n"
				+ "    manufacture.manufacture_name,\n"
				+ "    stock.rm_stock,\n"
				+ "    sysdate AS today_date,\n"
				+ "    employee.e_name\n"
				+ "FROM\n"
				+ "    product\n"
				+ "JOIN\n"
				+ "    stock ON product.p_id = stock.p_id\n"
				+ "JOIN\n"
				+ "    warehouse ON stock.warehouse_id = warehouse.warehouse_id\n"
				+ "JOIN\n"
				+ "    manufacture ON product.p_manufacturer = manufacture.p_manufacturer\n"
				+ "JOIN\n"
				+ "    employee ON warehouse.e_employee_id = employee.e_employee_id\n";	
		 if (!"x".equals(search.get("searchOption")) && search.get("searchWord") != null) {
		        if ("manufacture_name".equals(search.get("searchOption"))) {
		            sql += "WHERE LOWER(manufacture." + search.get("searchOption") + ") LIKE LOWER('%" + search.get("searchWord") + "%') ";
		        } else {
		            sql += "WHERE LOWER(product." + search.get("searchOption") + ") LIKE LOWER('%" + search.get("searchWord") + "%') ";
		        }
		    }
		 
		 
		if ("one".equals(operationType)) {
            sql += " and warehouse.warehouse_id = 1 "
            		+ "ORDER BY\n"
            		+ "    product.p_type ASC,\n"
            		+ "    product.p_name ASC,\n"
            		+ "    product.p_quantity ASC";
        } else if ("two".equals(operationType)) {
            sql += " and warehouse.warehouse_id = 2"
            		+ "ORDER BY\n"
            		+ "    product.p_type ASC,\n"
            		+ "    product.p_name ASC,\n"
            		+ "    product.p_quantity ASC";
        } else if ("three".equals(operationType)) {
            sql += " and warehouse.warehouse_id = 3"
            		+ "ORDER BY\n"
            		+ "    product.p_type ASC,\n"
                    + "    product.p_name ASC,\n"
            		+ "    product.p_quantity ASC";
        } else {
			sql += "ORDER BY\n"
					+ "    product.p_type ASC,\n"
					+ "    product.p_name ASC,\n"
					+ "    product.p_quantity ASC";
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			warehouseBoard = new ArrayList<WarehouseBoardDTO>();
			WarehouseBoardDTO wb = null;

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
				
				wb = new WarehouseBoardDTO();
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
	    String sql = "SELECT\n"
	    		+ "    COALESCE(SUM(stock.rm_stock * product.p_unicost), 0) AS total_stock\n"
	    		+ "FROM\n"
	    		+ "    warehouse_test\n"
	    		+ "LEFT JOIN\n"
	    		+ "    stock ON warehouse_test.warehouse_id = stock.warehouse_id\n"
	    		+ "LEFT JOIN\n"
	    		+ "    product ON stock.p_id = product.p_id \n";

	    
	    if ("one".equals(operationType)) {
	        sql +=  "WHERE warehouse.warehouse_id = 1\n";
	        
        } else if ("two".equals(operationType)) {
	        sql +=  "WHERE warehouse.warehouse_id = 2\n";
	        
        } else if ("three".equals(operationType)) {
	        sql +=  "WHERE warehouse.warehouse_id = 3\n";
	        
        } else {
	        sql += "";
		}

	    
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);


	        rs = pstmt.executeQuery();

	        ArrayList<WarehouseBoardDTO> totalStockList = new ArrayList<>();

	        while (rs.next()) {
	            int total_stock = rs.getInt("total_stock");

	            WarehouseBoardDTO ts = new WarehouseBoardDTO();
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







