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
		String sql = "SELECT\n"
				+ "    product_test.p_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_type,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_si,\n"
				+ "    contract_items.ci_count,\n"
				+ "    contract.c_completed_date,\n"
				+ "    contract.c_status\n"
				+ "FROM\n"
				+ "    product_test,\n"
				+ "    contract_items,\n"
				+ "    contract\n"
				+ "WHERE\n"
				+ "    product_test.p_id = contract_items.ci_p_id\n"
				+ "    AND contract_items.ci_c_contract_no = contract.c_contract_no\n"
				+ "    AND contract.c_status = 2\n"
				+ "    and contract.c_type=2";

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
				int ci_count = rs.getInt("ci_count");
				String c_completed_date = rs.getString("c_completed_date");
				int c_status = rs.getInt("c_status");
				
				// p_id로 pk
				
				t = new ExWarehouseTestDTO();
				t.setC_completed_date(c_completed_date);
				t.setC_status(c_status);
				t.setCi_count(ci_count);
				t.setP_id(p_id);
				t.setP_name(p_name);
				t.setP_quantity(p_quantity);
				t.setP_si(p_si);
				t.setP_type(p_type);
				testExWarehouse.add(t);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(p_si);
				System.out.println(ci_count);
				System.out.println(c_completed_date);
				System.out.println(c_status);

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
	    
// 출고 status가 2에서 3으로 넘어가는 순간 날짜 스템프 추가 해줄것 
	    
	    String sql = "UPDATE contract\n"
	    		+ "SET c_status = 3\n"
	    		+ "WHERE c_contract_no IN (\n"
	    		+ "    SELECT contract.c_contract_no\n"
	    		+ "    FROM product_test\n"
	    		+ "    JOIN contract_items ON product_test.p_id = contract_items.ci_p_id\n"
	    		+ "    JOIN contract ON contract_items.ci_c_contract_no = contract.c_contract_no\n"
	    		+ "    WHERE product_test.p_id = ?\n"
	    		+ "      AND contract.c_status = 2\n"
	    		+ "      AND contract.c_type = 2\n"
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

	            System.out.println("Selected Product ID : " + productId + " - Status 3로 업데이트");
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

		    String sql = "INSERT INTO ex_warehouse VALUES (ex_warehouse_seq.NEXTVAL, ?, ?, ?, ?)";

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
				+ "    ex_warehouse.ex_warehouse_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_si,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_type,\n"
				+ "    ex_warehouse.ex_warehouse_date,\n"
				+ "    ex_warehouse.ex_warehouse_quantity,\n"
				+ "    warehouse_test.warehouse_name\n"
				+ "FROM\n"
				+ "    ex_warehouse\n"
				+ "JOIN\n"
				+ "    product_test ON ex_warehouse.p_id = product_test.p_id\n"
				+ "JOIN\n"
				+ "    warehouse_test ON ex_warehouse.warehouse_id = warehouse_test.warehouse_id";

		
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
	    		+ "    SELECT NVL(SUM(ex_warehouse.ex_warehouse_quantity), 0)\n"
	    		+ "    FROM ex_warehouse\n"
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
