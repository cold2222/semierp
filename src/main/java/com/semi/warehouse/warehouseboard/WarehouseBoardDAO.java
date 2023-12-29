package com.semi.warehouse.warehouseboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
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
				int pageSize = 15; // 한 페이지당 보여줄 개수
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
		String sql = "";
		if(!operationType.equals("all")) {
			sql = "SELECT\n"
					+ "    product.p_id,\n"
					+ "    product.p_name,\n"
					+ "    product.p_quantity,\n"
					+ "    product.p_si,\n"
					+ "    product.p_type,\n"
					+ "    product.p_unitcost,\n"
					+ "    warehouse.warehouse_name,\n"
					+ "    product.p_manufacturer,\n"
					+ "    stock.rm_stock,\n"
					+ "    employee.e_name\n"
					+ "FROM\n"
					+ "    product\n"
					+ "INNER JOIN\n"
					+ "    stock ON product.p_id = stock.p_id\n"
					+ "INNER JOIN\n"
					+ "    warehouse ON stock.warehouse_id = warehouse.warehouse_id\n"
					+ "INNER JOIN\n"
					+ "    employee ON warehouse.employee_id = employee.e_no\n"
					+ "WHERE stock.rm_stock > 0 ";	
			if (!"x".equals(search.get("searchOption")) && search.get("searchWord") != null && !search.get("searchWord").equals("")) {
				sql += "and LOWER(product." + search.get("searchOption") + ") LIKE LOWER('%" + search.get("searchWord") + "%') ";
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
			}
		}else {
			sql = "SELECT p.p_id, p.p_name, p.p_type, p.p_quantity, p.p_si, p.p_unitcost, p.p_manufacturer, SUM(s.rm_stock) AS rm_stock "
					+ "FROM stock s "
					+ "INNER JOIN product p ON s.p_id = p.p_id ";
					if (!"x".equals(search.get("searchOption")) && search.get("searchWord") != null && !search.get("searchWord").equals("")) {
						sql += "having LOWER(p." + search.get("searchOption") + ") LIKE LOWER('%" + search.get("searchWord") + "%') ";
					}
					sql+= "GROUP BY p.p_id, p.p_name, p.p_type, p.p_quantity, p.p_si, p.p_unitcost, p.p_manufacturer order by p_type, p_name";
		}

		try {

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			warehouseBoard = new ArrayList<WarehouseBoardDTO>();
			WarehouseBoardDTO wb = null;

			while (rs.next()) {
				int p_id = rs.getInt("p_id");
				String p_name = rs.getString("p_name");
				String p_type = rs.getString("p_type");
				String p_quantity = rs.getString("p_quantity");
				String p_si = rs.getString("p_si");
				int p_unicost = rs.getInt("p_unitcost");
				String manufacture_name = rs.getString("p_manufacturer");
				int stock = rs.getInt("rm_stock");
				
				wb = new WarehouseBoardDTO();
				wb.setP_id(p_id);
				wb.setP_name(p_name);
				wb.setP_quantity(p_quantity);
				wb.setP_si(p_si);
				wb.setP_type(p_type);
				wb.setP_unicost(p_unicost);
				wb.setManufacture_name(manufacture_name);
				wb.setStock(stock);
				
				if(!operationType.equals("all")) {
					wb.setE_name(rs.getString("e_name"));
					wb.setWarehouse_name(rs.getString("warehouse_name"));
				}
				warehouseBoard.add(wb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		
	
		
		
		
		
	}

	public void calcStock(HttpServletRequest request, String operationType) {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT\n"
	    		+ "    COALESCE(SUM(stock.rm_stock * product.p_unitcost), 0) AS total_stock\n"
	    		+ "FROM\n"
	    		+ "    warehouse\n"
	    		+ "LEFT JOIN\n"
	    		+ "    stock ON warehouse.warehouse_id = stock.warehouse_id\n"
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
	        con = DBManger.connect();
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
	        DBManger.close(con, pstmt, rs);
	    }

        
        
        
	}
		
		
	

}







