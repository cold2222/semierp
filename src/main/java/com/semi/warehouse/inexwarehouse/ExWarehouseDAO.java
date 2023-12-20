package com.semi.warehouse.inexwarehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class ExWarehouseDAO {

	private ArrayList<ExWarehouseDTO> exWarehouse;
	
	private static final ExWarehouseDAO EWDAO = new ExWarehouseDAO();
	
	private ExWarehouseDAO() {}
	
	 public static ExWarehouseDAO getEwdao() {
	        return EWDAO;
	    }
	 private Connection con = null;
	 
	 // DTO에 맞춰서 바꿔야하기 때문에 하나 더 만들어야함 
	 public void paging(int pageNum, HttpServletRequest request) {
			int pageSize = 10; // 한 페이지당 보여줄 개수
			int totalData = exWarehouse.size();
			int totalPage = (int)Math.ceil((double)totalData / pageSize);
			
			int startDataNum = totalData - (pageSize * (pageNum - 1));
			int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
			
			ArrayList<ExWarehouseDTO> items = new ArrayList<ExWarehouseDTO>();
			if(exWarehouse.size() > 0) {
				for (int i = startDataNum-1; i > endDataNum; i--) {
					items.add(exWarehouse.get(i));
				}
			}
			request.setAttribute("exWarehouse", items);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("totalPage", totalPage);
			
		}
	
	
	
	
	public void getExAllTest(HttpServletRequest request) {
		String searchOption = request.getParameter("searchOption");
        String searchWord = request.getParameter("word");
		
        
        HashMap<String,String> search = new HashMap<String, String>();
        
        if (searchOption != null) {
        	search.put("searchOption", searchOption);
		}
        if(searchWord != null) {
        	search.put("searchWord", searchWord);	        	
        }
        
		
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product.p_id,\n"
				+ "    product.p_name,\n"
				+ "    product.p_type,\n"
				+ "    product.p_quantity,\n"
				+ "    product.p_si,\n"
				+ "    contract_items.ci_count,\n"
				+ "    contract.c_completed_date,\n"
				+ "    contract.c_status\n"
				+ "FROM\n"
				+ "    product,\n"
				+ "    contract_items,\n"
				+ "    contract\n"
				+ "WHERE\n"
				+ "    product.p_id = contract_items.ci_p_id\n"
				+ "    AND contract_items.ci_c_contract_no = contract.c_contract_no\n"
				+ "    AND contract.c_status = 2\n"
				+ "    and contract.c_type=2 \n";
			if (!"x".equals(search.get("searchOption")) && search.get("searchWord") != null) {
		        sql += "and product." + search.get("searchOption") + " LIKE '%" + search.get("searchWord") + "%' ";
		    }
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<ExWarehouseDTO> exWarehouse = new ArrayList<ExWarehouseDTO>();
			ExWarehouseDTO t = null;

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
				
				t = new ExWarehouseDTO();
				t.setC_completed_date(c_completed_date);
				t.setC_status(c_status);
				t.setCi_count(ci_count);
				t.setP_id(p_id);
				t.setP_name(p_name);
				t.setP_quantity(p_quantity);
				t.setP_si(p_si);
				t.setP_type(p_type);
				exWarehouse.add(t);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(p_si);
				System.out.println(ci_count);
				System.out.println(c_completed_date);
				System.out.println(c_status);

			}
			request.setAttribute("exWarehouse", exWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void updateExWareStatus(HttpServletRequest request) {
	//status를 3에서 4로 업테이트 해주는 구문
		
	    String selectedIdsString = request.getParameter("selectedIds");

	    // 콤마로 스플릿 
	    String[] selectedIds = selectedIdsString.split(",");

	    PreparedStatement pstmt = null;
	    
// 출고 status가 2에서 3으로 넘어가는 순간 날짜 스템프 추가 해줄것 
	    
	    String sql = "UPDATE contract\n"
	    		+ "SET c_status = 3\n"
	    		+ "WHERE c_contract_no IN (\n"
	    		+ "    SELECT contract.c_contract_no\n"
	    		+ "    FROM product\n"
	    		+ "    JOIN contract_items ON product.p_id = contract_items.ci_p_id\n"
	    		+ "    JOIN contract ON contract_items.ci_c_contract_no = contract.c_contract_no\n"
	    		+ "    WHERE product.p_id = ?\n"
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


	
	
	
	public void regExWareTest(HttpServletRequest request) {
			// 출고 테이블에 insert into 할 수 있게해주는 곳 
			String selectedIdsString = request.getParameter("selectedIds");
	        String selectedRecordSalesCountsString = request.getParameter("selectedRecordSalesCounts");
	        String selectedSellDatesString = request.getParameter("selectedSellDates");

	        String[] selectedIds = selectedIdsString.split(",");
	        String[] selectedRecordSalesCounts = selectedRecordSalesCountsString.split(",");
	        String[] selectedSellDates = selectedSellDatesString.split(",");
	        
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


	public void upStockMTest(HttpServletRequest request) {
		
		String selectedIdsString = request.getParameter("selectedIds");
		
	    // 받아온 것을 콤마로 스플릿 후 배열  
	    String[] selectedIds = selectedIdsString.split(",");
	    
	    PreparedStatement pstmt = null;
	    // quantity를 늘려주는 sql 문 p_id 와 warehouse_id가 가지고 있는 quantity 값을 - 해줌 
	    String sql = "UPDATE stock\n"
	    		+ "SET rm_stock = rm_stock - (\n"
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
