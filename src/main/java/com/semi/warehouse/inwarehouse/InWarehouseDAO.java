package com.semi.warehouse.inwarehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class InWarehouseDAO {

	private ArrayList<InWarehouseDTO> inWarehouse;

	private static final InWarehouseDAO TWDAO = new InWarehouseDAO();

	private InWarehouseDAO() {
	}

	public static InWarehouseDAO getTwdao() {
		return TWDAO;
	}

	public void paging(int pageNum, HttpServletRequest request) {
		int pageSize = 10; // 한 페이지당 보여줄 개수
		int totalData = inWarehouse.size();
		int totalPage = (int) Math.ceil((double) totalData / pageSize);

		int startDataNum = totalData - (pageSize * (pageNum - 1));
		int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);

		ArrayList<InWarehouseDTO> items = new ArrayList<InWarehouseDTO>();
		if (inWarehouse.size() > 0) {
			for (int i = startDataNum - 1; i > endDataNum; i--) {
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

		HashMap<String, String> search = new HashMap<String, String>();
		String field = request.getParameter("field");
		String word = request.getParameter("word");
		if (word != null) {
			search.put("field", field);
			search.put("word", word);
		}
		String sql = "SELECT a.c_contract_no, a.c_created_date, b.c_name, c.e_name\n" + "FROM contract a\n"
				+ "INNER JOIN company b ON a.c_c_no = b.c_no\n" + "INNER JOIN employee c ON a.c_e_id = c.e_no\n"
				+ "WHERE a.c_type = 1 AND a.c_status = 3 ";

		if (search.get("word") != null && !search.get("field").equals("all") && !search.get("word").equals("")) {
			sql += "and LOWER(" + search.get("field") + ") " + "like LOWER ('%" + search.get("word") + "%') ";
		}

		try {

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			inWarehouse = new ArrayList<InWarehouseDTO>();
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void getInDetail(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "    contract_items.ci_p_id, "
				+ "    product.p_name, "
				+ "    product.p_type, "
				+ "    product.p_quantity, "
				+ "    product.p_si, "
				+ "    contract_items.ci_count "
				+ "FROM "
				+ "    contract_items "
				+ "INNER JOIN "
				+ "    product ON contract_items.ci_p_id = product.p_id "
				+ "WHERE "
				+ "    contract_items.ci_c_contract_no = ?";

		try {

			con = DBManger.connect();
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
			request.setAttribute("c_contract_no", request.getParameter("c_contract_no"));
			request.setAttribute("inWarehouse", inWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void updateContractStatus4(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update contract set c_status = 4, c_completed_date = sysdate where c_contract_no = ?";
		try {

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, request.getParameter("c_contract_no"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("입고 스테이터스4, 컴플리트데이트 등록 완료");
			}

		} catch (Exception e) {
			System.out.println("입고 스테이터스4, 컴플리트데이트 등록 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}

	}

	public void regInWare(HttpServletRequest request) {

		String[] p_id = request.getParameterValues("ci_p_id");
		String[] ci_count = request.getParameterValues("ci_count");
		String[] warehouse_id = request.getParameterValues("warehouse_id");

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO in_warehouse VALUES (in_warehouse_seq.NEXTVAL, ?, sysdate, ?, ?)";

		try {

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < p_id.length; i++) {
				pstmt.setString(1, p_id[i]);
				pstmt.setString(2, ci_count[i]);
				pstmt.setString(3, warehouse_id[i]);

				if (pstmt.executeUpdate() == 1) {
					System.out.println(i + "번째 입고 등록 완료");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("입고 등록 실패");
		} finally {
			DBManger.close(con, pstmt, null);
		}

	}

	public void regStock(HttpServletRequest request) {

		String[] p_id = request.getParameterValues("ci_p_id");
		String[] warehouse_id = request.getParameterValues("warehouse_id");

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "MERGE INTO stock st " 
				+ "USING ( " 
				+ "    SELECT " 
				+ "        0 AS in_warehouse_quantity, "
				+ "        p_id, " 
				+ "        warehouse_id " 
				+ "    FROM " 
				+ "        in_warehouse " 
				+ "    WHERE "
				+ "        p_id = ? AND warehouse_id = ? "
				+ ") src ON (st.p_id = src.p_id AND st.warehouse_id = src.warehouse_id) " + "WHEN NOT MATCHED THEN "
				+ "    INSERT (rm_stock, p_id, warehouse_id) "
				+ "    VALUES (src.in_warehouse_quantity, src.p_id, src.warehouse_id)";

		try {

			con = DBManger.connect();

			for (int i = 0; i < p_id.length; i++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, p_id[i]);
				pstmt.setString(2, warehouse_id[i]);
				if (pstmt.executeUpdate() == 1) {
					System.out.println(i + "번째데이터 창고 스톡완료 ");
				}
			}
		} catch (Exception e) {
			System.out.println("창고 스톡실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}

	}

	public void upStock(HttpServletRequest request) {

		String[] p_id = request.getParameterValues("ci_p_id");
		String[] warehouse_id = request.getParameterValues("warehouse_id");
		String[] ci_count = request.getParameterValues("ci_count");

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE stock SET rm_stock = rm_stock + ? WHERE p_id = ? AND warehouse_id = ?";

		try {
			con = DBManger.connect();

			for (int i = 0; i < p_id.length; i++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(ci_count[i]));
				pstmt.setInt(2, Integer.parseInt(p_id[i]));
				pstmt.setInt(3, Integer.parseInt(warehouse_id[i]));

				if (pstmt.executeUpdate() == 1) {
					System.out.println(i + "번째 stock 추가 완료");
				}
			}
		} catch (Exception e) {
			System.out.println("stock 추가 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}

	}

	public void getSelectedContract(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT a.c_contract_no, a.c_created_date, b.c_name, c.e_name " 
				+ "FROM contract a "
				+ "INNER JOIN company b ON a.c_c_no = b.c_no " 
				+ "INNER JOIN employee c ON a.c_e_id = c.e_no "
				+ "WHERE a.c_type = 1 AND a.c_status = 3 and a.c_contract_no = ?";

		try {

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();

			InWarehouseDTO t = null;

			if (rs.next()) {
				int c_contract_no = rs.getInt("c_contract_no");
				String c_created_date = rs.getString("c_created_date");
				String c_name = rs.getString("c_name");
				String e_name = rs.getString("e_name");

				t = new InWarehouseDTO();
				t.setC_contract_no(c_contract_no);
				t.setC_created_date(c_created_date);
				t.setC_name(c_name);
				t.setE_name(e_name);
				
			}
			request.setAttribute("contract", t);
			System.out.println("입고 뷰 페이지 계약서조회 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("입고 뷰 페이지 계약서조회 실패");
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

}
