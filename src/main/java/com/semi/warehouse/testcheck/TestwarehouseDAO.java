package com.semi.warehouse.testcheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class TestwarehouseDAO {

	public static void getAllTest(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT \n" + "    product_test_ksj.p_id,\n" + "    product_test_ksj.p_name,\n"
				+ "    product_test_ksj.p_si,\n" + "    product_test_ksj.p_type,\n"
				+ "    Purchase_record_ksj.record_count,\n" + "    Purchase_recordall_ksj.in_warehouse_date,\n"
				+ "    Purchase_recordall_ksj.status\n" + "FROM \n" + "    product_test_ksj, \n"
				+ "    Purchase_record_ksj, \n" + "    Purchase_recordall_ksj\n" + "WHERE \n"
				+ "    product_test_ksj.p_id = Purchase_record_ksj.p_id\n"
				+ "    AND Purchase_record_ksj.recordall_num = Purchase_recordall_ksj.recordall_num\n"
				+ "    AND Purchase_recordall_ksj.status = 3";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<TestwarehouseDTO> testWarehouse = new ArrayList<TestwarehouseDTO>();
			TestwarehouseDTO t = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int record_count = rs.getInt("record_count");
				String in_warehouse_date = rs.getString("in_warehouse_date");
				int status = rs.getInt("status");
//				int warehouse_id = rs.getInt("warehouse_id");
//				int in_warehouse_quantity = rs.getInt("in_warehouse_quantity");
//				String warehouse_name = rs.getString("warehouse_name");
				// p_id로 pk
				
				t = new TestwarehouseDTO(p_id, p_name, p_si, p_type, record_count, in_warehouse_date, status);
				testWarehouse.add(t);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(record_count);
				System.out.println(in_warehouse_date);
				System.out.println(status);

			}
			request.setAttribute("testWarehouse", testWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void updateInWareTest(HttpServletRequest request) {
	//status를 3에서 4로 업테이트 해주는 구문
		
	    String selectedIdsString = request.getParameter("selectedIds");

	    // 콤마로 스플릿 
	    String[] selectedIds = selectedIdsString.split(",");

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
// 나중에 status가 3에서 4로 넘어갈떄 날짜 스템프 추가 해줄것 
	    
	    String sql = "UPDATE Purchase_recordall_ksj " +
	            "SET status = 4 " +
	            "WHERE recordall_num IN (" +
	            "    SELECT Purchase_recordall_ksj.recordall_num " +
	            "    FROM product_test_ksj " +
	            "    JOIN Purchase_record_ksj ON product_test_ksj.p_id = Purchase_record_ksj.p_id " +
	            "    JOIN Purchase_recordall_ksj ON Purchase_record_ksj.recordall_num = Purchase_recordall_ksj.recordall_num " +
	            "    WHERE product_test_ksj.p_id = ? " +
	            "      AND Purchase_recordall_ksj.status = 3 " +
	            ")";

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


	
	
	
	public static void regInWareTest(HttpServletRequest request) {

			String selectedIdsString = request.getParameter("selectedIds");
	        String selectedRecordCountsString = request.getParameter("selectedRecordCounts");
	        String selectedInWarehouseDatesString = request.getParameter("selectedInWarehouseDates");
//	        String selectedWarehouseIdsString = request.getParameter("selectedWarehouseIds");

	        String[] selectedIds = selectedIdsString.split(",");
	        String[] selectedRecordCounts = selectedRecordCountsString.split(",");
	        String[] selectedInWarehouseDates = selectedInWarehouseDatesString.split(",");
//	        String[] selectedWarehouseIds = selectedWarehouseIdsString.split(",");
	        
			Connection con = null;
		    PreparedStatement pstmt = null;

		    String sql = "INSERT INTO in_warehouse_test VALUES (in_warehouse_test_seq.NEXTVAL, ?, ?, ?, ?)";

		    try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");

		        con = DBManager.connect();
		        pstmt = con.prepareStatement(sql);
		        
		        
		        for (int i = 0; i < selectedIds.length; i++) {
		            pstmt.setInt(1, Integer.parseInt(selectedIds[i]));
		            pstmt.setString(2, selectedInWarehouseDates[i].split(" ")[0]);
		            System.out.println(selectedInWarehouseDates[i]);
		            pstmt.setInt(3, Integer.parseInt(selectedRecordCounts[i]));
		            
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


	public static void getInWareTest(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    in_warehouse_test.in_warehouse_id,\n"
				+ "    product_test_ksj.p_name,\n"
				+ "    product_test_ksj.p_si,\n"
				+ "    product_test_ksj.p_quantity,\n"
				+ "    product_test_ksj.p_type,\n"
				+ "    in_warehouse_test.in_warehouse_date,\n"
				+ "    in_warehouse_test.in_warehouse_quantity,\n"
				+ "    warehouse_test.warehouse_name\n"
				+ "FROM\n"
				+ "    in_warehouse_test\n"
				+ "JOIN\n"
				+ "    product_test_ksj ON in_warehouse_test.p_id = product_test_ksj.p_id\n"
				+ "JOIN\n"
				+ "    warehouse_test ON in_warehouse_test.warehouse_id = warehouse_test.warehouse_id";

		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<InWarehouseDTO> inWarehouse = new ArrayList<InWarehouseDTO>();
			InWarehouseDTO in = null;
			
			
			while (rs.next()) {
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_quantity = rs.getInt("p_quantity");
				String in_warehouse_date = rs.getString("in_warehouse_date").split(" ")[0];
				int in_warehouse_id = rs.getInt("in_warehouse_id");
				int in_warehouse_quantity = rs.getInt("in_warehouse_quantity");
				String warehouse_name = rs.getString("warehouse_name");
				// p_id로 pk
								
				
				in = new InWarehouseDTO(p_name, p_si, p_type,p_quantity, in_warehouse_date, in_warehouse_id, in_warehouse_quantity, warehouse_name);
				inWarehouse.add(in);

				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(in_warehouse_id);
				System.out.println(in_warehouse_quantity);
				System.out.println(in_warehouse_date);
				System.out.println(warehouse_name);

			}
			request.setAttribute("inWarehouse", inWarehouse);
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
	}

	public static void regStockTest(HttpServletRequest request) {

		String selectedIdsString = request.getParameter("selectedIds");
		String selectedRecordCountsString = request.getParameter("selectedRecordCounts");

	    // 받아온 것을 콤마로 스플릿 후 배열  
	    String[] selectedIds = selectedIdsString.split(",");
	    String[] selectedRecordCounts = selectedRecordCountsString.split(",");

	    Connection con = null;
	    PreparedStatement pstmt = null;
	
	    String sql = "INSERT INTO stock_test VALUES (?, ?, ?);";

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");

	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);
	        // for 문으로돌리기  

	        for (int i = 0; i < selectedIds.length; i++) {
	            pstmt.setInt(1, Integer.parseInt(selectedIds[i]));
	            pstmt.setInt(2, Integer.parseInt(selectedRecordCounts[i]));
	            
	            // 선택한 창고 값을 받아오기 위해서 만듬 
	            String warehouseIdParameterName = "warehouse_id_" + selectedIds[i];
	            String selectedWarehouseIdString = request.getParameter(warehouseIdParameterName);
	            int selectedWarehouseId = Integer.parseInt(selectedWarehouseIdString);
	            pstmt.setInt(3, selectedWarehouseId);
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

	public static void upStockTest(HttpServletRequest request) {
		
		
	}

}
