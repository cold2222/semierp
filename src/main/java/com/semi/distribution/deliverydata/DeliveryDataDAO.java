package com.semi.distribution.deliverydata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;

public class DeliveryDataDAO {

	private static final DeliveryDataDAO DDAO = new DeliveryDataDAO();
	private ArrayList<DeliveryDataDTO> deliveryDataList;

	private DeliveryDataDAO() {
	}

	public static DeliveryDataDAO getDdao() {
		return DDAO;
	}

	public void paging(int pageNum, HttpServletRequest request) {
		int pageSize = 10; // 한 페이지당 보여줄 개수
		int totalData = deliveryDataList.size();
		int totalPage = (int) Math.ceil((double) totalData / pageSize);

		int startDataNum = totalData - (pageSize * (pageNum - 1));
		int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);

		ArrayList<DeliveryDataDTO> items = new ArrayList<DeliveryDataDTO>();
		if (deliveryDataList.size() > 0) {
			for (int i = startDataNum - 1; i > endDataNum; i--) {
				items.add(deliveryDataList.get(i));
			}
		}
		request.setAttribute("deliveryDataList", items);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);

	}

	public void getDeliveryList(HttpServletRequest request) {
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
		
		String sql = "SELECT a.c_contract_no, a.c_delivery_date, a.c_type, b.c_name, c.e_name " 
				+ "FROM contract a "
				+ "INNER JOIN company b ON a.c_c_no = b.c_no "
				+ "INNER JOIN shipping d ON a.c_contract_no = d.s_contract_no "
				+ "INNER JOIN employee c ON d.s_e_no = c.e_no " 
				+ "WHERE (a.c_type = 1 and a.c_status = 2) or (a.c_type = 2 and a.c_status = 2) or (a.c_type = 2 and a.c_status = 3) ";
				if (search.get("word") != null && !search.get("field").equals("all")) {
					sql += "and LOWER(" + search.get("field") + ") " + "like LOWER ('%" + search.get("word") + "%') ";
				}
		
				sql += "order by a.c_delivery_date desc";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			deliveryDataList = new ArrayList<DeliveryDataDTO>();
			DeliveryDataDTO deliveryData = null;
			while (rs.next()) {
				deliveryData = new DeliveryDataDTO();
				deliveryData.setC_contract_no(rs.getString("c_contract_no"));
				deliveryData.setC_delivery_date(rs.getDate("c_delivery_date"));
				deliveryData.setC_name(rs.getString("c_name"));
				deliveryData.setE_name(rs.getString("e_name"));
				if (rs.getString("c_type").equals("1")) {
					deliveryData.setC_type("購買");
				} else {
					deliveryData.setC_type("販売");
				}

				deliveryDataList.add(deliveryData);
			}
			System.out.println("배송정보 조회 성공");

		} catch (Exception e) {
			System.out.println("배송정보 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void getDelivery(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT a.c_contract_no, a.c_delivery_date, a.c_type, b.c_name, c.e_name " + "FROM contract a "
				+ "INNER JOIN company b ON a.c_c_no = b.c_no "
				+ "INNER JOIN shipping d ON a.c_contract_no = d.s_contract_no "
				+ "INNER JOIN employee c ON d.s_e_no = c.e_no " 
				+ "WHERE a.c_status = 2 and a.c_contract_no = ?"
				+ "order by a.c_delivery_date desc";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();

			DeliveryDataDTO deliveryData = null;
			if (rs.next()) {
				deliveryData = new DeliveryDataDTO();
				deliveryData.setC_contract_no(rs.getString("c_contract_no"));
				deliveryData.setC_delivery_date(rs.getDate("c_delivery_date"));
				deliveryData.setC_name(rs.getString("c_name"));
				deliveryData.setE_name(rs.getString("e_name"));
				deliveryData.setC_type(rs.getString("c_type"));
				if (rs.getString("c_type").equals("1")) {
					deliveryData.setC_type("購買");
				} else {
					deliveryData.setC_type("販売");
				}

				request.setAttribute("deliveryData", deliveryData);
			}
			System.out.println("배송view정보 조회 성공");

		} catch (Exception e) {
			System.out.println("배송view정보 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void updateDeliveryDate(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update contract set c_delivery_date = ? where c_contract_no = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_delivery_date"));
			pstmt.setString(2, request.getParameter("c_contract_no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("delivery_date 수정 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delivery_date 수정 실패");
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void updateShipping(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update shipping set s_e_no = ?, s_memo = ? where s_contract_no = ?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("e_no"));
			pstmt.setString(2, request.getParameter("s_memo"));
			pstmt.setString(3, request.getParameter("c_contract_no"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("배차수정 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("배차수정 실패");
		} finally {
			DBManger.close(con, pstmt, null);
		}

	}

}
