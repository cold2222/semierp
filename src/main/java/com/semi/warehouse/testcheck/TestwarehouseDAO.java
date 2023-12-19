package com.semi.warehouse.testcheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class TestwarehouseDAO {

	private ArrayList<TestwarehouseDTO> testWarehouse;
	
	private static final TestwarehouseDAO TWDAO = new TestwarehouseDAO();
	
	private TestwarehouseDAO() {}
	
	 public static TestwarehouseDAO getTwdao() {
	        return TWDAO;
	    }
	
	 public void paging(int pageNum, HttpServletRequest request) {
			int pageSize = 10; // 한 페이지당 보여줄 개수
			int totalData = testWarehouse.size();
			int totalPage = (int)Math.ceil((double)totalData / pageSize);
			
			int startDataNum = totalData - (pageSize * (pageNum - 1));
			int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
			
			ArrayList<TestwarehouseDTO> items = new ArrayList<TestwarehouseDTO>();
			if(testWarehouse.size() > 0) {
				for (int i = startDataNum-1; i > endDataNum; i--) {
					items.add(testWarehouse.get(i));
				}
			}
			request.setAttribute("testWarehouse", items);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("totalPage", totalPage);
			
		}
	 
	 
	 
	
	
	public void getAllTest(HttpServletRequest request) {
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
				+ "    AND contract.c_status = 3\n"
				+ "    and contract.c_type=1";
		if (!"x".equals(search.get("searchOption")) && search.get("searchWord") != null) {
	        sql += "and product_test." + search.get("searchOption") + " LIKE '%" + search.get("searchWord") + "%' ";
	    }
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			testWarehouse = new ArrayList<TestwarehouseDTO>();
			TestwarehouseDTO t = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int ci_count = rs.getInt("ci_count");
				int p_quantity = rs.getInt("p_quantity");
				String c_completed_date = rs.getString("c_completed_date");
				int c_status = rs.getInt("c_status");
				// p_id로 pk
				
				t = new TestwarehouseDTO();
				t.setP_id(p_id);
				t.setP_name(p_name);
				t.setP_si(p_si);
				t.setP_quantity(p_quantity);
				t.setP_type(p_type);
				t.setCi_count(ci_count);
				t.setC_completed_date(c_completed_date);
				t.setC_status(c_status);
				testWarehouse.add(t);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(ci_count);
				System.out.println(c_completed_date);
				System.out.println(c_status);

			}
			request.setAttribute("testWarehouse", testWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void updateInWareTest(HttpServletRequest request) {
	//status를 3에서 4로 업테이트 해주는 구문
		
	    String selectedIdsString = request.getParameter("selectedIds");

	    // 콤마로 스플릿 
	    String[] selectedIds = selectedIdsString.split(",");

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
// 나중에 status가 3에서 4로 넘어갈떄 날짜 스템프 추가 해줄것 
	    
	    String sql = "UPDATE contract\n"
	    		+ "SET c_status = 4\n"
	    		+ "WHERE c_contract_no IN (\n"
	    		+ "    SELECT contract.c_contract_no\n"
	    		+ "    FROM product_test\n"
	    		+ "    JOIN contract_items ON product_test.p_id = contract_items.ci_p_id\n"
	    		+ "    JOIN contract ON contract_items.ci_c_contract_no = contract.c_contract_no\n"
	    		+ "    WHERE product_test.p_id = ?\n"
	    		+ "      AND contract.c_status = 3\n"
	    		+ "      AND contract.c_type = 1\n"
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


	
	
	
	public void regInWareTest(HttpServletRequest request) {

			String selectedIdsString = request.getParameter("selectedIds");
	        String selectedRecordCountsString = request.getParameter("selectedRecordCounts");
	        String selectedInWarehouseDatesString = request.getParameter("selectedInWarehouseDates");

	        String[] selectedIds = selectedIdsString.split(",");
	        String[] selectedRecordCounts = selectedRecordCountsString.split(",");
	        String[] selectedInWarehouseDates = selectedInWarehouseDatesString.split(",");
	        
			Connection con = null;
		    PreparedStatement pstmt = null;

		    String sql = "INSERT INTO in_warehouse VALUES (in_warehouse_seq.NEXTVAL, ?, ?, ?, ?)";

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


	public void getInWareTest(HttpServletRequest request) {
		
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
				+ "    in_warehouse.in_warehouse_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_si,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_type,\n"
				+ "    in_warehouse.in_warehouse_date,\n"
				+ "    in_warehouse.in_warehouse_quantity,\n"
				+ "    warehouse_test.warehouse_name\n"
				+ "FROM\n"
				+ "    in_warehouse\n"
				+ "JOIN\n"
				+ "    product_test ON in_warehouse.p_id = product_test.p_id\n"
				+ "JOIN\n"
				+ "    warehouse_test ON in_warehouse.warehouse_id = warehouse_test.warehouse_id \n";
			if (!"x".equals(search.get("searchOption")) && search.get("searchWord") != null) {
				sql += "where product_test." + search.get("searchOption") + " LIKE '%" + search.get("searchWord") + "%' ";
			}
				
			System.out.println(searchWord);
			
			
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

	public void regStockTest(HttpServletRequest request) {
		
		String selectedIdsString = request.getParameter("selectedIds");
		String selectedInWarehouseDatesString = request.getParameter("selectedInWarehouseDates");

	    // 받아온 것을 콤마로 스플릿 후 배열  
	    String[] selectedIds = selectedIdsString.split(",");
	    String[] selectedInWarehouseDates = selectedInWarehouseDatesString.split(",");

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    // 받아온 것의 p_id와 warehouse_id를 가지고 온 후 stock에 등록하는 sql 문 quantity는 늘리지 않음.
	    String sql = "MERGE INTO stock_test st\n"
	    		+ "USING (\n"
	    		+ "    SELECT\n"
	    		+ "        0 AS in_warehouse_quantity, \n"
	    		+ "        p_id,\n"
	    		+ "        warehouse_id\n"
	    		+ "    FROM\n"
	    		+ "        in_warehouse\n"
	    		+ "    WHERE\n"
	    		+ "        p_id = ? AND warehouse_id = ?\n"
	    		+ ") src ON (st.p_id = src.p_id AND st.warehouse_id = src.warehouse_id)\n"
	    		+ "WHEN NOT MATCHED THEN\n"
	    		+ "    INSERT (stock, p_id, warehouse_id)\n"
	    		+ "    VALUES (src.in_warehouse_quantity, src.p_id, src.warehouse_id)";

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");

	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);
	        // for 문으로돌리기  

	        for (int i = 0; i < selectedIds.length; i++) {
	            pstmt.setInt(1, Integer.parseInt(selectedIds[i]));
	            
	            // 선택한 창고 값을 받아오기 위해서 만듬 
	            String warehouseIdParameterName = "warehouse_id_" + selectedIds[i];
	            String selectedWarehouseIdString = request.getParameter(warehouseIdParameterName);
	            int selectedWarehouseId = Integer.parseInt(selectedWarehouseIdString);
	            pstmt.setInt(2, selectedWarehouseId);
	            
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

	public void upStockTest(HttpServletRequest request) {
		String selectedIdsString = request.getParameter("selectedIds");
		String selectedInWarehouseDatesString = request.getParameter("selectedInWarehouseDates");

	    // 받아온 것을 콤마로 스플릿 후 배열  
	    String[] selectedIds = selectedIdsString.split(",");
	    String[] selectedInWarehouseDates = selectedInWarehouseDatesString.split(",");
	    
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    // quantity를 늘려주는 sql 문 p_id 와 warehouse_id가 가지고 있는 quantity 값을 + 해줌 
	    String sql = "UPDATE stock_test\n"
	    		+ "SET stock = stock + (\n"
	    		+ "    SELECT NVL(SUM(in_warehouse.in_warehouse_quantity), 0)\n"
	    		+ "    FROM in_warehouse\n"
	    		+ "    WHERE p_id = ? AND warehouse_id = ?\n"
	    		+ ") \n"
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
