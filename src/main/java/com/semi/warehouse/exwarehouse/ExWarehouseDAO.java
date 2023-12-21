package com.semi.warehouse.exwarehouse;

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
	
	 
	 
	
	
	public void getExAll(HttpServletRequest request) {
//		String searchOption = request.getParameter("searchOption");
//        String searchWord = request.getParameter("word");
//		
//        
//        HashMap<String,String> search = new HashMap<String, String>();
//        
//        if (searchOption != null) {
//        	search.put("searchOption", searchOption);
//		}
//        if(searchWord != null) {
//        	search.put("searchWord", searchWord);	        	
//        }
//        
		
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select a.c_contract_no, a.c_created_date, b.c_name, c.e_name "
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "inner join employee c on a.c_e_id = c.e_no "
				+ "where a.c_type = 2 and a.c_status = 2";
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<ExWarehouseDTO> exWarehouses = new ArrayList<ExWarehouseDTO>();
			ExWarehouseDTO exWarehouse = null;

			while (rs.next()) {
			exWarehouse = new ExWarehouseDTO();
			exWarehouse.setC_contract_no(rs.getInt("c_contract_no"));
			exWarehouse.setC_created_date(rs.getString("c_created_date"));
			exWarehouse.setC_name(rs.getString("c_name"));
			exWarehouse.setE_name(rs.getString("e_name"));
			exWarehouses.add(exWarehouse);
			}
			request.setAttribute("exWarehouse", exWarehouses);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	
	public void getExDetail(HttpServletRequest request) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT\n"
				+ "    contract_items.ci_p_id,\n"
				+ "    product.p_name,\n"
				+ "    product.p_type,\n"
				+ "    product.p_quantity,\n"
				+ "    product.p_si,\n"
				+ "    contract_items.ci_count \n"
				+ "FROM\n"
				+ "    contract_items inner join\n"
				+ "    product on contract_items.ci_p_id = product.p_id\n"
				+ "WHERE\n"
				+ "    contract_items.ci_c_contract_no = ? ";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();
			
			ArrayList<ExWarehouseDTO> exWarehouses = new ArrayList<ExWarehouseDTO>();
			ExWarehouseDTO exWarehouse = null;

			while (rs.next()) {
			exWarehouse = new ExWarehouseDTO();
			
			exWarehouse.setCi_p_id(rs.getInt("ci_p_id"));
			exWarehouse.setP_name(rs.getString("p_name"));
			exWarehouse.setP_type(rs.getString("p_type"));
			exWarehouse.setP_quantity(rs.getInt("p_quantity"));
			exWarehouse.setP_si(rs.getString("p_si"));
			exWarehouse.setCi_count(rs.getInt("ci_count"));
			exWarehouses.add(exWarehouse);
			}
			request.setAttribute("exWarehouse", exWarehouses);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}
	
	
	
	
	
	
	
	public void updateExWareStatus(HttpServletRequest request) {
	//status를 3에서 4로 업테이트 해주는 구문

	    PreparedStatement pstmt = null;
	    
// 출고 status가 2에서 3으로 넘어가는 순간 날짜 스템프 추가 해줄것 
	    
	    String sql = "UPDATE contract\n"
	    		+ "SET c_status = 3,\n"
	    		+ "    c_completed_date = SYSDATE\n"
	    		+ "WHERE c_contract_no IN (\n"
	    		+ "    SELECT c.c_contract_no\n"
	    		+ "    FROM product p\n"
	    		+ "    JOIN contract_items ci ON p.p_id = ci.ci_p_id\n"
	    		+ "    JOIN contract c ON ci.ci_c_contract_no = c.c_contract_no\n"
	    		+ "    WHERE c.c_contract_no = ?\n"
	    		+ "      AND c.c_status = 2\n"
	    		+ "      AND c.c_type = 2\n"
	    		+ "		AND TRUNC(c_delivery_date) = TRUNC(SYSDATE)\n"
	    		+ ")";

	    
	    
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");

	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, Integer.parseInt(request.getParameter("c_contract_no")));
	            pstmt.executeUpdate();

	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(con, pstmt, null);
	    }
		
		
	}
		
	
	
	
	
	public void regExWare(HttpServletRequest request) {
			// 출고 테이블에 insert into 할 수 있게해주는 곳 
		
		
			String selectedIdsString = request.getParameter("ci_p_id");
	        String selectedRecordSalesCountsString = request.getParameter("ci_count");

	        String[] selectedIds = selectedIdsString.split(",");
	        String[] selectedRecordSalesCounts = selectedRecordSalesCountsString.split(",");
	        
		    PreparedStatement pstmt = null;

		    String sql = "INSERT INTO ex_warehouse VALUES (ex_warehouse_seq.NEXTVAL, ?, sysdate, ?, ?)";

		    try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");

		        con = DBManager.connect();
		        pstmt = con.prepareStatement(sql);
		        
		        
		        for (int i = 0; i < selectedIds.length; i++) {
		            pstmt.setInt(1, Integer.parseInt(selectedIds[i]));
		            pstmt.setInt(2, Integer.parseInt(selectedRecordSalesCounts[i]));
		            
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

		        System.out.println(" 추가 성공");
		        

		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("에러 ????");
		    } finally {
		        DBManager.close(con, pstmt, null);
		    }
		
		
		
		
		
		
	}


	public void upStock(HttpServletRequest request) {
		
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
