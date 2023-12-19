package com.semi.warehouse.testcheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.specialnote.SpecialNoteDAO;
import com.semi.distribution.specialnote.SpecialNoteDTO;

public class InExWarehouseDAO {

	private ArrayList<allInExDTO> allInExWarehouse;
	
	private static final InExWarehouseDAO IEDAO = new InExWarehouseDAO();
	
	private InExWarehouseDAO() {}
	
	 public static InExWarehouseDAO getIedao() {
	        return IEDAO;
	    }

	 public void paging(int pageNum, HttpServletRequest request) {
			int pageSize = 10; // 한 페이지당 보여줄 개수
			int totalData = allInExWarehouse.size();
			int totalPage = (int)Math.ceil((double)totalData / pageSize);
			
			int startDataNum = totalData - (pageSize * (pageNum - 1));
			int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);
			
			ArrayList<allInExDTO> items = new ArrayList<allInExDTO>();
			if(allInExWarehouse.size() > 0) {
				for (int i = startDataNum-1; i > endDataNum; i--) {
					items.add(allInExWarehouse.get(i));
				}
			}
			request.setAttribute("allInExWarehouse", items);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("totalPage", totalPage);
			
		}
	
	public void getallWare(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product_test.p_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_type,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_si,\n"
				+ "    in_warehouse.in_warehouse_quantity AS quantity,\n"
				+ "    in_warehouse.in_warehouse_date AS warehouse_date,\n"
				+ "    '입고' AS warehouse_type,\n"
				+ "    in_warehouse.warehouse_id,\n"
				+ "    warehouse_test.warehouse_name AS warehouse_name\n"
				+ "FROM\n"
				+ "    product_test\n"
				+ "LEFT JOIN\n"
				+ "    in_warehouse ON product_test.p_id = in_warehouse.p_id\n"
				+ "LEFT JOIN\n"
				+ "    warehouse_test ON in_warehouse.warehouse_id = warehouse_test.warehouse_id\n"
				+ "WHERE\n"
				+ "    in_warehouse.in_warehouse_id IS NOT NULL\n"
				+ "\n"
				+ "UNION\n"
				+ "\n"
				+ "SELECT\n"
				+ "    product_test.p_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_type,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_si,\n"
				+ "    ex_warehouse.ex_warehouse_quantity AS quantity,\n"
				+ "    ex_warehouse.ex_warehouse_date AS warehouse_date,\n"
				+ "    '출고' AS warehouse_type,\n"
				+ "    ex_warehouse.warehouse_id,\n"
				+ "    warehouse_test.warehouse_name AS warehouse_name\n"
				+ "FROM\n"
				+ "    product_test\n"
				+ "LEFT JOIN\n"
				+ "    ex_warehouse ON product_test.p_id = ex_warehouse.p_id\n"
				+ "LEFT JOIN\n"
				+ "    warehouse_test ON ex_warehouse.warehouse_id = warehouse_test.warehouse_id\n"
				+ "WHERE\n"
				+ "    ex_warehouse.ex_warehouse_id IS NOT NULL\n"
				+ "ORDER BY\n"
				+ "    warehouse_date DESC";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			allInExWarehouse = new ArrayList<allInExDTO>();
			allInExDTO all = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_quantity = rs.getInt("p_quantity");
				int quantity = rs.getInt("quantity");
				String warehouse_date = rs.getString("warehouse_date");
				String warehouse_type = rs.getString("warehouse_type");
				int warehouse_id = rs.getInt("warehouse_id");
				String warehouse_name = rs.getString("warehouse_name");
				
				// p_id로 pk
				
				all = new allInExDTO(p_id, p_name, p_si, p_type, p_quantity, quantity, warehouse_date,warehouse_type, warehouse_id, warehouse_name);
				allInExWarehouse.add(all);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(quantity);
				System.out.println(warehouse_date);
				System.out.println(warehouse_type);
				System.out.println(warehouse_id);
				System.out.println(warehouse_name);

			}
			request.setAttribute("allInExWarehouse", allInExWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
			
			
			
	}

	public void getExWare(HttpServletRequest request) {
		String searchOption = request.getParameter("searchOption");
        String searchWord = request.getParameter("word");
        String operationType = request.getParameter("operationType");
		
        
        HashMap<String,String> search = new HashMap<String, String>();
        search.put("searchOption", searchOption);
        if(searchWord != null) {
        	search.put("searchWord", searchWord);	        	
        }
        search.put("operationType", operationType);
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product_test.p_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_type,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_si,\n"
				+ "    ex_warehouse.ex_warehouse_quantity AS quantity,\n"
				+ "    ex_warehouse.ex_warehouse_date AS warehouse_date,\n"
				+ "    '출고' AS warehouse_type,\n"
				+ "    ex_warehouse.warehouse_id,\n"
				+ "    warehouse_test.warehouse_name AS warehouse_name\n"
				+ "FROM\n"
				+ "    product_test\n"
				+ "LEFT JOIN\n"
				+ "    ex_warehouse ON product_test.p_id = ex_warehouse.p_id\n"
				+ "LEFT JOIN\n"
				+ "    warehouse_test ON ex_warehouse.warehouse_id = warehouse_test.warehouse_id ";
				if(!search.get("searchOption").equals("x") && search.get("searchWord") != null) {
        			sql += "where product_test."+search.get("searchOption")+" like '%"+ search.get("searchWord")+"%' ";
        		} sql += "ORDER BY\n"
				+ "    warehouse_date DESC";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			allInExWarehouse = new ArrayList<allInExDTO>();
			allInExDTO all = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_quantity = rs.getInt("p_quantity");
				int quantity = rs.getInt("quantity");
				String warehouse_date = rs.getString("warehouse_date");
				String warehouse_type = rs.getString("warehouse_type");
				int warehouse_id = rs.getInt("warehouse_id");
				String warehouse_name = rs.getString("warehouse_name");
				
				// p_id로 pk
				
				all = new allInExDTO(p_id, p_name, p_si, p_type, p_quantity, quantity, warehouse_date,warehouse_type, warehouse_id, warehouse_name);
				allInExWarehouse.add(all);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(quantity);
				System.out.println(warehouse_date);
				System.out.println(warehouse_type);
				System.out.println(warehouse_id);
				System.out.println(warehouse_name);

			}
			request.setAttribute("allInExWarehouse", allInExWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
			
		
	}

	public void getInWare(HttpServletRequest request) {
		
		String searchOption = request.getParameter("searchOption");
        String searchWord = request.getParameter("word");
        String operationType = request.getParameter("operationType");
		
        
        HashMap<String,String> search = new HashMap<String, String>();
        search.put("searchOption", searchOption);
        if(searchWord != null) {
        	search.put("searchWord", searchWord);	        	
        }
        search.put("operationType", operationType);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT\n"
				+ "    product_test.p_id,\n"
				+ "    product_test.p_name,\n"
				+ "    product_test.p_type,\n"
				+ "    product_test.p_quantity,\n"
				+ "    product_test.p_si,\n"
				+ "    in_warehouse.in_warehouse_quantity AS quantity,\n"
				+ "    in_warehouse.in_warehouse_date AS warehouse_date,\n"
				+ "    '입고' AS warehouse_type,\n"
				+ "    in_warehouse.warehouse_id,\n"
				+ "    warehouse_test.warehouse_name AS warehouse_name\n"
				+ "FROM\n"
				+ "    product_test\n"
				+ "LEFT JOIN\n"
				+ "    in_warehouse ON product_test.p_id = in_warehouse.p_id\n"
				+ "LEFT JOIN\n"
				+ "    warehouse_test ON in_warehouse.warehouse_id = warehouse_test.warehouse_id ";
				if(!search.get("searchOption").equals("x") && search.get("searchWord") != null) {
        			sql += "where product_test."+search.get("searchOption")+" like '%"+ search.get("searchWord")+"%' ";
        		} sql += "ORDER BY\n"
				+ "    warehouse_date DESC";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			allInExWarehouse = new ArrayList<allInExDTO>();
			allInExDTO all = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_si = rs.getString("p_si");
				String p_type = rs.getString("p_type");
				int p_quantity = rs.getInt("p_quantity");
				int quantity = rs.getInt("quantity");
				String warehouse_date = rs.getString("warehouse_date");
				String warehouse_type = rs.getString("warehouse_type");
				int warehouse_id = rs.getInt("warehouse_id");
				String warehouse_name = rs.getString("warehouse_name");
				
				// p_id로 pk
				
				all = new allInExDTO(p_id, p_name, p_si, p_type, p_quantity, quantity, warehouse_date,warehouse_type, warehouse_id, warehouse_name);
				allInExWarehouse.add(all);

				System.out.println(p_id);
				System.out.println(p_name);
				System.out.println(p_si);
				System.out.println(p_type);
				System.out.println(p_quantity);
				System.out.println(quantity);
				System.out.println(warehouse_date);
				System.out.println(warehouse_type);
				System.out.println(warehouse_id);
				System.out.println(warehouse_name);

			}
			request.setAttribute("allInExWarehouse", allInExWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
			
			
		
	}

	public void getSearchedWare(HttpServletRequest request) {
		
		
		
			String searchOption = request.getParameter("searchOption");
	        String searchWord = request.getParameter("word");
	        String operationType = request.getParameter("operationType");

	        HashMap<String,String> search = new HashMap<String, String>();
	        search.put("searchOption", searchOption);
	        if(searchWord != null) {
	        	search.put("searchWord", searchWord);	        	
	        }
	        search.put("operationType", operationType);
	        
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        
	        String sql = "SELECT a.p_id,a.p_name,a.p_type,a.p_quantity,a.p_si, "
	        		+ "b.in_warehouse_quantity AS quantity, b.in_warehouse_date AS warehouse_date, "
	        		+ "'입고' AS warehouse_type,c.warehouse_id,c.warehouse_name AS warehouse_name "
	        		+ "FROM product_test a "
	        		+ "LEFT JOIN in_warehouse b ON a.p_id = b.p_id "
	        		+ "LEFT JOIN warehouse_test c ON b.warehouse_id = c.warehouse_id ";
	        		if(!search.get("searchOption").equals("x") && search.get("searchWord") != null) {
	        			sql += "where a."+search.get("searchOption")+" like '%"+ search.get("searchWord")+"%' ";
	        		}
	        		
	        		sql += "UNION "
	        		+ "SELECT d.p_id, d.p_name, d.p_type, d.p_quantity, d.p_si, "
	        		+ "e.ex_warehouse_quantity AS quantity, e.ex_warehouse_date AS warehouse_date, "
	        		+ "'출고' AS warehouse_type, e.warehouse_id, f.warehouse_name AS warehouse_name "
	        		+ "FROM product_test d "
	        		+ "LEFT JOIN ex_warehouse e ON d.p_id = e.p_id "
	        		+ "LEFT JOIN warehouse_test f ON e.warehouse_id = f.warehouse_id ";
	        		if(!search.get("searchOption").equals("x") && search.get("searchWord") != null) {
	        			sql += "where d."+search.get("searchOption")+" like '%"+ search.get("searchWord")+"%' ";
	        		}
	        		sql += "ORDER BY warehouse_date DESC";


	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DBManager.connect();
	            pstmt = con.prepareStatement(sql);
	            rs = pstmt.executeQuery();

	            allInExWarehouse = new ArrayList<>();
	            allInExDTO all = null;

	            while (rs.next()) {
	                
	                int p_id = rs.getInt("p_id");
	                String p_name = rs.getString("p_name");
	                String p_si = rs.getString("p_si");
	                String p_type = rs.getString("p_type");
	                int p_quantity = rs.getInt("p_quantity");
	                int quantity = rs.getInt("quantity");
	                String warehouse_date = rs.getString("warehouse_date");
	                String warehouse_type = rs.getString("warehouse_type");
	                int warehouse_id = rs.getInt("warehouse_id");
	                String warehouse_name = rs.getString("warehouse_name");

	                all = new allInExDTO(p_id, p_name, p_si, p_type, p_quantity, quantity, warehouse_date, warehouse_type, warehouse_id, warehouse_name);
	                allInExWarehouse.add(all);
	            }

	            request.setAttribute("allInExWarehouse", allInExWarehouse);

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DBManager.close(con, pstmt, rs);
	        }
	    }
		
		
		
		
		
	

	
	
}
