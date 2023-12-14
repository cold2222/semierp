package com.semi.warehouse.testcheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ExWarehouseTestDAO {

	public static void getExAllTest(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT \n"
				+ "    product_test_ksj.p_id,\n"
				+ "    product_test_ksj.p_name,\n"
				+ "    product_test_ksj.p_type,\n"
				+ "    product_test_ksj.p_quantity,\n"
				+ "    product_test_ksj.p_si,\n"
				+ "    sales_contract_items.record_sales_count,\n"
				+ "    sales_contract_ksj.sell_date,\n"
				+ "    sales_contract_ksj.status\n"
				+ "FROM \n"
				+ "    product_test_ksj, \n"
				+ "    sales_contract_items, \n"
				+ "    sales_contract_ksj \n"
				+ "WHERE \n"
				+ "    product_test_ksj.p_id = sales_contract_items.p_id\n"
				+ "    AND sales_contract_items.recordall_sell_num = sales_contract_ksj.recordall_sell_num\n"
				+ "    AND sales_contract_ksj.status = 3";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<ExWarehouseTestDTO> testExWarehouse = new ArrayList<ExWarehouseTestDTO>();
			ExWarehouseTestDTO t = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_type = rs.getString("p_type");
				int p_quantity = rs.getInt("p_quantity");
				String p_si = rs.getString("p_si");
				int record_sales_count = rs.getInt("record_sales_count");
				String sell_date = rs.getString("sell_date");
				int status = rs.getInt("status");
				
				// p_id로 pk
				
				t = new ExWarehouseTestDTO(p_id, p_name, p_type, p_quantity, p_si, record_sales_count,sell_date, status);
				testExWarehouse.add(t);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(p_si);
				System.out.println(record_sales_count);
				System.out.println(sell_date);
				System.out.println(status);

			}
			request.setAttribute("testExWarehouse", testExWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void updateExWareTest(HttpServletRequest request) {
	//status를 3에서 4로 업테이트 해주는 구문
		
	    String selectedIdsString = request.getParameter("selectedIds");

	    // 콤마로 스플릿 
	    String[] selectedIds = selectedIdsString.split(",");

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
// 나중에 status가 3에서 4로 넘어갈떄 날짜 스템프 추가 해줄것 
	    
	    String sql = "UPDATE sales_contract_ksj\n"
	    		+ "SET status = 4\n"
	    		+ "WHERE recordall_sell_num IN (\n"
	    		+ "    SELECT sales_contract_ksj.recordall_sell_num\n"
	    		+ "    FROM product_test_ksj\n"
	    		+ "    JOIN sales_contract_items ON product_test_ksj.p_id = sales_contract_items.p_id\n"
	    		+ "    JOIN sales_contract_ksj ON sales_contract_items.recordall_sell_num = sales_contract_ksj.recordall_sell_num\n"
	    		+ "    WHERE product_test_ksj.p_id = ?\n"
	    		+ "      AND sales_contract_ksj.status = 3\n"
	    		+ ")";

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");

	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);
	        // for 문으로돌리기  
	        for (String id : selectedIds) {
	            int productId = Integer.parseInt(id);

	            pstmt.setInt(1, productId);
	            pstmt.executeUpdate();

	            System.out.println("Selected Product ID : " + productId + " - Status 4로 업데이트");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(con, pstmt, null);
	    }
		
		
		
		
	}


	
	
	
	public static void regExWareTest(HttpServletRequest request) {
			// 출고 테이블에 insert into 할 수 있게해주는 곳 
			String selectedIdsString = request.getParameter("selectedIds");
	        String selectedRecordSalesCountsString = request.getParameter("selectedRecordSalesCounts");
	        String selectedSellDatesString = request.getParameter("selectedSellDates");

	        String[] selectedIds = selectedIdsString.split(",");
	        String[] selectedRecordSalesCounts = selectedRecordSalesCountsString.split(",");
	        String[] selectedSellDates = selectedSellDatesString.split(",");
	        
			Connection con = null;
		    PreparedStatement pstmt = null;

		    String sql = "INSERT INTO ex_warehouse_test VALUES (ex_warehouse_test_seq.NEXTVAL, ?, ?, ?, ?)";

		    try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");

		        con = DBManager.connect();
		        pstmt = con.prepareStatement(sql);
		        
		        
		        for (int i = 0; i < selectedIds.length; i++) {
		            pstmt.setInt(1, Integer.parseInt(selectedIds[i]));
		            pstmt.setString(2, selectedSellDates[i].split(" ")[0]);
		            System.out.println(selectedSellDates[i]);
		            pstmt.setInt(3, Integer.parseInt(selectedRecordSalesCounts[i]));
		            
		            // 선택한 창고 값을 받아오기 위해서 만듬 
		            String warehouseIdParameterName = "warehouse_id_" + selectedIds[i];
		            String selectedWarehouseIdString = request.getParameter(warehouseIdParameterName);
		            int selectedWarehouseId = Integer.parseInt(selectedWarehouseIdString);
		            pstmt.setInt(4, selectedWarehouseId);
		            System.out.println(selectedWarehouseId);
		            
		            //  이게 pstmt.executeUpdate(); 한번에 처리 할 수 있는 문장
		            pstmt.addBatch();
		        }

		        // 일괄 처리 가능 
		        pstmt.executeBatch();

		        System.out.println(" 추가 성공");
		        

		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("에러 ????");
		    } finally {
		        DBManager.close(con, pstmt, null);
		    }
		
		
		
		
		
		
	}


	public static void getExWareTest(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    ex_warehouse_test.ex_warehouse_id,\n"
				+ "    product_test_ksj.p_name,\n"
				+ "    product_test_ksj.p_si,\n"
				+ "    product_test_ksj.p_quantity,\n"
				+ "    product_test_ksj.p_type,\n"
				+ "    ex_warehouse_test.ex_warehouse_date,\n"
				+ "    ex_warehouse_test.ex_warehouse_quantity,\n"
				+ "    warehouse_test.warehouse_name\n"
				+ "FROM\n"
				+ "    ex_warehouse_test\n"
				+ "JOIN\n"
				+ "    product_test_ksj ON ex_warehouse_test.p_id = product_test_ksj.p_id\n"
				+ "JOIN\n"
				+ "    warehouse_test ON ex_warehouse_test.warehouse_id = warehouse_test.warehouse_id";

		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ExWarehouseDTO> exWarehouse = new ArrayList<ExWarehouseDTO>();
			ExWarehouseDTO ex = null;
			
			
			while (rs.next()) {
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_quantity = rs.getInt("p_quantity");
				String ex_warehouse_date = rs.getString("ex_warehouse_date").split(" ")[0];
				int ex_warehouse_id = rs.getInt("ex_warehouse_id");
				int ex_warehouse_quantity = rs.getInt("ex_warehouse_quantity");
				String warehouse_name = rs.getString("warehouse_name");
				// p_id로 pk
								
				
				ex = new ExWarehouseDTO(p_name, p_si, p_type,p_quantity, ex_warehouse_date, ex_warehouse_id, ex_warehouse_quantity, warehouse_name);
				exWarehouse.add(ex);

				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(ex_warehouse_id);
				System.out.println(ex_warehouse_quantity);
				System.out.println(ex_warehouse_date);
				System.out.println(warehouse_name);

			}
			request.setAttribute("exWarehouse", exWarehouse);
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
	}

	public static void upStockMTest(HttpServletRequest request) {
		
		String selectedIdsString = request.getParameter("selectedIds");
		
	    // 받아온 것을 콤마로 스플릿 후 배열  
	    String[] selectedIds = selectedIdsString.split(",");
	    
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    // quantity를 늘려주는 sql 문 p_id 와 warehouse_id가 가지고 있는 quantity 값을 - 해줌 
	    String sql = "UPDATE stock_test\n"
	    		+ "SET stock = stock - (\n"
	    		+ "    SELECT NVL(SUM(ex_warehouse_test.ex_warehouse_quantity), 0)\n"
	    		+ "    FROM ex_warehouse_test\n"
	    		+ "    WHERE p_id = ? AND warehouse_id = ?\n"
	    		+ ")\n"
	    		+ "WHERE p_id = ? AND warehouse_id = ?";

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");

	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);
	        // for 문으로돌리기  

	        for (int i = 0; i < selectedIds.length; i++) {
	            pstmt.setInt(1, Integer.parseInt(selectedIds[i]));
	            pstmt.setInt(3, Integer.parseInt(selectedIds[i]));

	            
	            // 선택한 창고 값을 받아오기 위해서 만듬 
	            String warehouseIdParameterName = "warehouse_id_" + selectedIds[i];
	            String selectedWarehouseIdString = request.getParameter(warehouseIdParameterName);
	            int selectedWarehouseId = Integer.parseInt(selectedWarehouseIdString);
	            pstmt.setInt(2, selectedWarehouseId);
	            pstmt.setInt(4, selectedWarehouseId);
	            System.out.println(selectedWarehouseId);
	            
	            //  이게 pstmt.executeUpdate(); 한번에 처리 할 수 있는 문장
	            pstmt.addBatch();
	        }
	        // 일괄 처리 가능 
	        pstmt.executeBatch();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(con, pstmt, null);
	    }
		
	
	
		
		
		
	}

}
