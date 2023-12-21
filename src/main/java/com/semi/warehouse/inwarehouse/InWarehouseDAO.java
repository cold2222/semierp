package com.semi.warehouse.inwarehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.warehouse.exwarehouse.ExWarehouseDTO;

public class InWarehouseDAO {

	private ArrayList<InWarehouseDTO> inWarehouse;
	
	private static final InWarehouseDAO TWDAO = new InWarehouseDAO();
	
	private InWarehouseDAO() {}
	
	 public static InWarehouseDAO getTwdao() {
	        return TWDAO;
	    }
	
	 public void paging(int pageNum, HttpServletRequest request) {
			int pageSize = 10; // 한 페이지당 보여줄 개수
			int totalData = inWarehouse.size();
			int totalPage = (int)Math.ceil((double)totalData / pageSize);
			
			int startDataNum = totalData - (pageSize * (pageNum - 1));
			int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
			
			ArrayList<InWarehouseDTO> items = new ArrayList<InWarehouseDTO>();
			if(inWarehouse.size() > 0) {
				for (int i = startDataNum-1; i > endDataNum; i--) {
					items.add(inWarehouse.get(i));
				}
			}
			request.setAttribute("inWarehouse", items);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("totalPage", totalPage);
			
		}
	 
	 
	 
	
	
	public void getAll(HttpServletRequest request) {
				
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.c_contract_no, a.c_created_date, b.c_name, c.e_name\n"
				+ "FROM contract a\n"
				+ "INNER JOIN company b ON a.c_c_no = b.c_no\n"
				+ "INNER JOIN employee c ON a.c_e_id = c.e_no\n"
				+ "WHERE a.c_type = 1 AND a.c_status = 3";

		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<InWarehouseDTO> inWarehouse = new ArrayList<InWarehouseDTO>();
			InWarehouseDTO t = null;

			while (rs.next()) {
				int c_contract_no = rs.getInt("c_contract_no");
				String c_created_date = rs.getString("c_created_date");
				String c_name = rs.getString("c_name");
				String e_name = rs.getString("e_name");
				
				t = new InWarehouseDTO();
				t.setC_contract_no(c_contract_no);
				t.setC_created_date(c_created_date);
				t.setC_name(c_name);
				t.setE_name(e_name);
				inWarehouse.add(t);
				
				System.out.println(c_contract_no);
				System.out.println(c_created_date);
				System.out.println(c_name);
				System.out.println(e_name);

			}
			request.setAttribute("inWarehouse", inWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	
public void getInDetail(HttpServletRequest request) {
				
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    contract_items.ci_p_id,\n"
				+ "    product.p_name,\n"
				+ "    product.p_type,\n"
				+ "    product.p_quantity,\n"
				+ "    product.p_si,\n"
				+ "    contract_items.ci_count\n"
				+ "FROM\n"
				+ "    contract_items\n"
				+ "INNER JOIN\n"
				+ "    product ON contract_items.ci_p_id = product.p_id\n"
				+ "WHERE\n"
				+ "    contract_items.ci_c_contract_no = ? \n";

		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();

			ArrayList<InWarehouseDTO> inWarehouse = new ArrayList<InWarehouseDTO>();
			InWarehouseDTO t = null;

			while (rs.next()) {
				
				t = new InWarehouseDTO();
				t.setCi_p_id(rs.getInt("ci_p_id"));
				t.setP_name(rs.getString("p_name"));
				t.setP_type(rs.getString("p_type"));
				t.setP_quantity(rs.getInt("p_quantity"));
				t.setP_si(rs.getString("p_si"));
				t.setCi_count(rs.getInt("ci_count"));
				inWarehouse.add(t);
				

			}
			request.setAttribute("inWarehouse", inWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}
	
	
	public void updateInWareStatus(HttpServletRequest request) {
	//status를 3에서 4로 업테이트 해주는 구문
		
	  //  String selectedIdsString = request.getParameter("selectedIds");

	    // 콤마로 스플릿 
	 //   String[] selectedIds = selectedIdsString.split(",");

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
// 나중에 status가 3에서 4로 넘어갈떄 날짜 스템프 추가 해줄것 
	    
	    String sql = "UPDATE contract\n"
	    		+ "SET c_status = 4,\n"
	    		+ " c_completed_date = SYSDATE \n"
	    		+ "WHERE c_contract_no IN (\n"
	    		+ "    SELECT contract.c_contract_no\n"
	    		+ "    FROM product\n"
	    		+ "    JOIN contract_items ON product.p_id = contract_items.ci_p_id\n"
	    		+ "    JOIN contract ON contract_items.ci_c_contract_no = contract.c_contract_no\n"
	    		+ "    WHERE c.c_contract_no = ?\n"
	    		+ "      AND contract.c_status = 3\n"
	    		+ "      AND contract.c_type = 1\n"
	    		+ "      AND TRUNC(c_delivery_date) = TRUNC(SYSDATE)\n"
	    		+ ")";
	    
	    
	    
	    
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");

	        con = DBManager.connect();
	        pstmt = con.prepareStatement(sql);

	        pstmt.setInt(1, Integer.parseInt(request.getParameter("c_contract_no")));
            pstmt.executeUpdate();
	        
	        // for 문으로돌리기  
//	        for (String id : selectedIds) {
//	            int productId = Integer.parseInt(id);
//
//	            pstmt.setInt(1, productId);
//	            pstmt.executeUpdate();
//
//	            System.out.println("Selected Product ID : " + productId + " - Status 4로 업데이트");
//	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(con, pstmt, null);
	    }
		
		
		
		
	}


	
	
	
	public void regInWare(HttpServletRequest request) {

	        String selectedIdsString = request.getParameter("ci_p_id");
	        String selectedRecordCountsString = request.getParameter("ci_count");

	        String[] selectedIds = selectedIdsString.split(",");
	        String[] selectedRecordCounts = selectedRecordCountsString.split(",");
	        
	        
			Connection con = null;
		    PreparedStatement pstmt = null;

		    String sql = "INSERT INTO in_warehouse VALUES (in_warehouse_seq.NEXTVAL, ?, sysdate, ?, ?)";

		    try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");

		        con = DBManager.connect();
		        pstmt = con.prepareStatement(sql);
		        
		        
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

		        System.out.println(" 추가 성공");
		        

		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("에러 ????");
		    } finally {
		        DBManager.close(con, pstmt, null);
		    }
		
		
		
		
		
		
	}

	public void regStock(HttpServletRequest request) {
		
		String selectedIdsString = request.getParameter("selectedIds");
		String selectedInWarehouseDatesString = request.getParameter("selectedInWarehouseDates");

	    // 받아온 것을 콤마로 스플릿 후 배열  
	    String[] selectedIds = selectedIdsString.split(",");
	    String[] selectedInWarehouseDates = selectedInWarehouseDatesString.split(",");

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    // 받아온 것의 p_id와 warehouse_id를 가지고 온 후 stock에 등록하는 sql 문 quantity는 늘리지 않음.
	    String sql = "MERGE INTO stock st\n"
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
	    		+ "    INSERT (rm_stock, p_id, warehouse_id)\n"
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

	public void upStock(HttpServletRequest request) {
		String selectedIdsString = request.getParameter("selectedIds");
		String selectedInWarehouseDatesString = request.getParameter("selectedInWarehouseDates");

	    // 받아온 것을 콤마로 스플릿 후 배열  
	    String[] selectedIds = selectedIdsString.split(",");
	    String[] selectedInWarehouseDates = selectedInWarehouseDatesString.split(",");
	    
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    // quantity를 늘려주는 sql 문 p_id 와 warehouse_id가 가지고 있는 quantity 값을 + 해줌 
	    String sql = "UPDATE stock\n"
	    		+ "SET rm_stock = rm_stock + (\n"
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
