package com.semi.distribution.receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.db.DBManger;
import com.semi.distribution.employee.EmployeeDTO;
import com.semi.distribution.notice.NoticeDTO;
import com.semi.distribution.shift.ShiftDTO;

public class ReceiptDAO {

	private static final ReceiptDAO RDAO = new ReceiptDAO();
	private ArrayList<ReceiptDTO> receiptList;

	private ReceiptDAO() {

	}

	public static ReceiptDAO getRdao() {
		return RDAO;
	}

	public void paging(int pageNum, HttpServletRequest request) {
		int pageSize = 10; // 한 페이지당 보여줄 개수
		int totalData = receiptList.size();
		int totalPage = (int) Math.ceil((double) totalData / pageSize);

		int startDataNum = totalData - (pageSize * (pageNum - 1));
		int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);

		ArrayList<ReceiptDTO> items = new ArrayList<ReceiptDTO>();
		if (receiptList.size() > 0) {
			for (int i = startDataNum - 1; i > endDataNum; i--) {
				items.add(receiptList.get(i));
			}
		}
		request.setAttribute("receiptList", items);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);

	}

	public void getReceiptList(HttpServletRequest request) {
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

		String sql = "select a.c_contract_no, a.c_c_no, a.c_e_id, a.c_created_date, a.c_due_date, a.c_status, "
				+ "b.c_name, c.e_name " 
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "inner join employee c on  a.c_e_id = c.e_no "
				+ "where a.c_status = 1 and a.c_type = 1 ";
				
				if (search.get("word") != null && !search.get("field").equals("all")) {
					sql += "and LOWER(" + search.get("field") + ") " + "like LOWER ('%" + search.get("word") + "%') ";
				}
				sql += "order by a.c_due_date desc";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			receiptList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setC_contract_no(rs.getString("c_contract_no"));
				rec.setC_c_no(rs.getString("c_c_no"));
				rec.setC_e_id(rs.getString("c_e_id"));
				rec.setC_created_date(rs.getDate("c_created_date"));
				rec.setC_due_date(rs.getDate("c_due_date"));
				rec.setC_status(rs.getString("c_status"));
				rec.setC_name(rs.getString("c_name"));
				rec.setE_name(rs.getString("e_name"));
				receiptList.add(rec);

			}
			System.out.println("수령 조회 성공");

		} catch (Exception e) {
			System.out.println("수령목록 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void getReceiptData(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select a.c_status, a.c_contract_no,a.c_created_date,a.c_due_date,b.c_name,b.c_keeper,b.c_phone "
				+ "from contract a inner join company b on a.c_c_no = b.c_no "
				+ "where a.c_contract_no = ? order by c_due_date";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();

			ReceiptDTO rec = null;
			if (rs.next()) {
				rec = new ReceiptDTO();
				rec.setC_contract_no(rs.getString("c_contract_no"));
				rec.setC_status(rs.getString("c_status"));
				rec.setC_created_date(rs.getDate("c_created_date"));
				rec.setC_due_date(rs.getDate("c_due_date"));
				rec.setC_name(rs.getString("c_name"));
				rec.setC_keeper(rs.getString("c_keeper"));
				rec.setC_phone(rs.getString("c_phone"));
				request.setAttribute("rec", rec);

			}
			System.out.println("수령상세 조회 성공");

		} catch (Exception e) {
			System.out.println("수령상세 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void getReceiptItemList(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select p_name,p_quantity,p_si,p_type,ci_count,ci_unit_price "
				+ "from contract a inner join contract_items b on a.c_contract_no = b.ci_c_contract_no "
				+ "inner join product c on b.ci_p_id = c.p_id " + "where a.c_contract_no = ? order by p_name";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			rs = pstmt.executeQuery();

			ArrayList<ReceiptDTO> itemList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setP_name(rs.getString("p_name"));
				rec.setP_quantity(rs.getString("p_quantity"));
				rec.setP_si(rs.getString("p_si"));
				rec.setP_type(rs.getString("p_type"));
				rec.setCi_count(rs.getString("ci_count"));
				rec.setCi_unit_price(rs.getString("ci_unit_price"));

				itemList.add(rec);

			}
			request.setAttribute("itemList", itemList);
			System.out.println("수령상세아이템 조회 성공");

		} catch (Exception e) {
			System.out.println("수령상세아이템 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void statusLevelUp2(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update contract set c_status = 2 where c_contract_no = ?";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("스테이터스2 성공");
			}

		} catch (Exception e) {
			System.out.println("스테이터스2 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void insertShipping(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "insert into shipping values(shipping_seq.nextval,?,?,?)";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("e_no"));
			pstmt.setString(2, request.getParameter("c_contract_no"));
			pstmt.setString(3, request.getParameter("s_memo"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("배차등록 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("배차등록 실패");
		} finally {
			DBManger.close(con, pstmt, null);
		}

	}

	public void getClearList(HttpServletRequest request) {

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

		String sql = "SELECT a.c_contract_no, c.e_name, " + "a.c_delivery_date, a.c_due_date, a.c_status, d.c_name "
				+ "FROM contract a " + "INNER JOIN shipping b ON a.c_contract_no = b.s_contract_no "
				+ "INNER JOIN employee c ON b.s_e_no = c.e_no " + "INNER JOIN company d ON a.c_c_no = d.c_no "
				+ "WHERE a.c_type = 1 " + "AND a.c_status = 2 " + "AND a.c_delivery_date <= SYSDATE ";
		if (search.get("word") != null && !search.get("field").equals("all")) {
			sql += " and LOWER(" + search.get("field") + ") " + "like LOWER ('%" + search.get("word") + "%') ";
		}
		sql += "ORDER BY a.c_delivery_date ";

		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			receiptList = new ArrayList<ReceiptDTO>();
			ReceiptDTO rec = null;
			while (rs.next()) {
				rec = new ReceiptDTO();
				rec.setC_contract_no(rs.getString("c_contract_no"));
				rec.setC_name(rs.getString("c_name"));
				rec.setE_name(rs.getString("e_name"));
				rec.setC_delivery_date(rs.getDate("c_delivery_date"));
				rec.setC_due_date(rs.getDate("c_due_date"));
				rec.setC_status(rs.getString("c_status"));

				receiptList.add(rec);

			}
			System.out.println("clearList 조회 성공");

		} catch (Exception e) {
			System.out.println("clearList 조회 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void statusLevelUp3(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update contract set c_status = 3 where c_contract_no = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("status 3 업데이트 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("status 3 업데이트 실패");
		} finally {
			DBManger.close(con, pstmt, null);
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
				System.out.println("delivery_date 업데이트 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delivery_date 업데이트 실패");
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

}
