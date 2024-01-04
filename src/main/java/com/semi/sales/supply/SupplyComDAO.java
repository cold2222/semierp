package com.semi.sales.supply;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.distribution.db.DBManger;
import com.semi.sales.product.Product;

public class SupplyComDAO {
	private ArrayList<Company> cs;
	private ArrayList<Contract> cts;
	private ArrayList<ContractItems> ctis;

	private static final SupplyComDAO SDAO = new SupplyComDAO();

	private SupplyComDAO() {
	}

	public static SupplyComDAO getSdao() {
		return SDAO;
	}

	public void paging(int page, HttpServletRequest request) {

		int cnt = 10; // 한 페이지당 보여줄 개수
		int total = cs.size(); // 총 데이터 개수
		int pageCount = (int) Math.ceil((double) total / cnt); // 총 페이지 수

		if (page > pageCount) {
			page = pageCount;
		}

		// 시작과 끝 인덱스 계산
		int start = (page - 1) * cnt;
		int end = Math.min(start + cnt, total);

		ArrayList<Company> items = new ArrayList<Company>();
		if (total > 0 && start < total) {
			for (int i = start; i < end; i++) {
				items.add(cs.get(i));
			}
		}
		request.setAttribute("cs", items);
		request.setAttribute("pageNum", page);
		request.setAttribute("pageCount", pageCount);
	}
	
	public void pagingContract(int pageNum, HttpServletRequest request) {
		int pageSize = 4; // 한 페이지당 보여줄 개수
		int totalData = cts.size();
		int totalPage = (int) Math.ceil((double) totalData / pageSize);
		int startDataNum = totalData - (pageSize * (pageNum - 1));
		int endDataNum = (pageNum == totalPage) ? -1 : startDataNum - (pageSize + 1);

		ArrayList<Contract> items = new ArrayList<Contract>();
		if (cts.size() > 0) {
			for (int i = startDataNum - 1; i > endDataNum; i--) {
				items.add(cts.get(i));
			}
		}
		request.setAttribute("cts", items);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);

	}
	public void getAllCom(HttpServletRequest request) {
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

		String sql = "select * from company";
		if (search.get("word") != null && !search.get("field").equals("all") && search.get("word") != "") {
			sql += " where " + search.get("field") + " " + "like '%" + search.get("word") + "%'";
		}

		sql += " order by c_no desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Company c = null;
			cs = new ArrayList<Company>();
			while (rs.next()) {
				c = new Company();
				c.setC_no(rs.getInt("c_no"));
				c.setC_name(rs.getString("c_name"));
				c.setC_keeper(rs.getString("c_keeper"));
				c.setC_phone(rs.getString("c_phone"));
				c.setC_addr(rs.getString("c_addr"));
				c.setC_text(rs.getString("c_text"));
				cs.add(c);
			}
			request.setAttribute("field", field);
			request.setAttribute("word", word);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void regCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into company values(company_seq.nextval, ?, ?, ?, ?, ?)";
		try {

			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_name"));
			pstmt.setString(2, request.getParameter("c_keeper"));
			pstmt.setString(3, request.getParameter("c_phone"));
			pstmt.setString(4, request.getParameter("c_addr"));
			pstmt.setString(5, request.getParameter("c_text"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("거래처등록 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("거래처등록 실패");
		} finally {
			DBManger.close(con, pstmt, null);
		}

	}

	public void getCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from company where c_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("c_no")));
			rs = pstmt.executeQuery();
			Company c = null;
			if (rs.next()) {
				c = new Company();
				c.setC_no(rs.getInt("c_no"));
				c.setC_name(rs.getString("c_name"));
				c.setC_keeper(rs.getString("c_keeper"));
				c.setC_phone(rs.getString("c_phone"));
				c.setC_addr(rs.getString("c_addr"));
				c.setC_text(rs.getString("c_text"));
				request.setAttribute("c", c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void getAllCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectKey = "";
		if(request.getParameter("field") != null) {
			if (request.getParameter("field").equals("b.c_name")) {
				selectKey = "회사명";
			} else if (request.getParameter("field").equals("a.c_status")) {
				selectKey = "거래상태";
			} else if (request.getParameter("field").equals("a.c_type")) {
				selectKey = "계약서종류";
			}
		}
		HashMap<String, String> search = new HashMap<String, String>();
		String field = request.getParameter("field");
		if (!selectKey.equals("")) {
			String inputWord = request.getParameter("inputWord");
			if (selectKey.equals("회사명") && !inputWord.equals("")) {
				search.put("field", field);
				search.put("inputWord", request.getParameter("inputWord"));
			} else if (selectKey.equals("거래상태")) {
				String[] statusSelectVal = request.getParameter("statusWord").split(",");
				search.put("field", field);
				search.put("type", statusSelectVal[0]);
				search.put("statusWord", statusSelectVal[1]);
			} else if (selectKey.equals("계약서종류")) {
				search.put("field", field);
				search.put("typeWord", request.getParameter("typeWord"));
			}
		}

		String sql = "select a.c_contract_no,a.c_created_date, a.c_due_date, a.c_status, a.c_type, b.c_name, a.c_c_no "
				+ "from contract a inner join company b on a.c_c_no = b.c_no ";
		if (selectKey.equals("회사명")) {
			sql += "where " + search.get("field") + " " + "like '%" + search.get("inputWord") + "%' ";
		}
		if (selectKey.equals("거래상태")) {
			sql += "where a.c_type = "+ search.get("type") + " and " + 
				search.get("field") +" = "+search.get("statusWord");
		}
		if (selectKey.equals("계약서종류")) {
			sql += "where a.c_type = "+ search.get("typeWord") + " ";
		}
		sql += "order by a.c_contract_no";

		try {

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Contract ct = null;
			cts = new ArrayList<Contract>();
			while (rs.next()) {
				ct = new Contract();
				ct.setC_contract_no(rs.getInt("c_contract_no"));
				ct.setC_c_no(rs.getInt("c_c_no"));
				ct.setC_name(rs.getString("c_name"));
				ct.setC_created_date(rs.getDate("c_created_date"));
				ct.setC_due_date(rs.getDate("c_due_date"));
				ct.setC_status(rs.getInt("c_status"));
				ct.setC_type(rs.getInt("c_type"));
				cts.add(ct);
			}
				
				request.setAttribute("inputWord", search.get("inputWord"));
				request.setAttribute("statusWord", search.get("statusWord"));
				request.setAttribute("typeWord", search.get("typeWord"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
	

	public void regCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into contract values(contract_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_c_no"));
			pstmt.setString(2, request.getParameter("c_e_id"));
			pstmt.setString(3, request.getParameter("c_created_date"));
			pstmt.setString(4, request.getParameter("c_due_date"));
			pstmt.setString(5, request.getParameter("c_delivery_date"));
			pstmt.setString(6, request.getParameter("c_completed_date"));
			pstmt.setString(7, request.getParameter("c_status"));
			pstmt.setString(8, request.getParameter("c_type"));

			if (pstmt.executeUpdate() > 0) {
				// 시퀀스의 현재 값을 가져오는 쿼리 실행
				String currvalQuery = "SELECT contract_seq.CURRVAL FROM dual";
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(currvalQuery);

				if (rs.next()) {
					String currval = rs.getString(1); // 시퀀스의 현재 값
					System.out.println("현재 시퀀스 값: " + currval);
					request.setAttribute("val", currval);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getAllContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from contract_items order by ci_no desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManger.connect();

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ContractItems cti = null;
			ctis = new ArrayList<ContractItems>();
			while (rs.next()) {
				cti = new ContractItems();
				cti.setCi_no(rs.getInt("ci_no"));
				cti.setCi_c_contract_no(rs.getInt("ci_c_contract_no"));
				cti.setCi_p_id(rs.getInt("ci_p_id"));
				cti.setCi_count(rs.getInt("ci_count"));
				cti.setCi_unit_price(rs.getInt("ci_unit_price"));
				ctis.add(cti);
			}
			request.setAttribute("ctis", ctis);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public String regContents(HttpServletRequest request) {
		String value = request.getAttribute("val").toString();
		int val = 0;
		if (value != null) {
			val = Integer.parseInt(value);
		} else {
			return null;
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into contract_items values(contract_items_seq.nextval, ?, ?, ?, ?)";
			request.setCharacterEncoding("utf-8");
			String[] ci_p_ids = request.getParameterValues("ci_p_id");
			String[] ci_counts = request.getParameterValues("ci_count");
			String[] ci_unit_prices = request.getParameterValues("ci_unit_price");
			con = DBManger.connect();

			pstmt = con.prepareStatement(sql);
			for (int i = 1; i < ci_p_ids.length; i++) {
				pstmt.setInt(1, val);
				pstmt.setString(2, ci_p_ids[i]);
				pstmt.setString(3, ci_counts[i]);
				pstmt.setString(4, ci_unit_prices[i]);
				if (pstmt.executeUpdate() == 1) {
					System.out.println("등록 성공");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}
		return null;
	}

	public void searchCom(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "select * from company where c_name like ?";
			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + request.getParameter("search") + "%");
			rs = pstmt.executeQuery();

			Company c = null;
			cs = new ArrayList<Company>();
			while (rs.next()) {
				c = new Company();
				c.setC_no(rs.getInt("c_no"));
				c.setC_name(rs.getString("c_name"));
				c.setC_keeper(rs.getString("c_keeper"));
				c.setC_phone(rs.getString("c_phone"));
				c.setC_addr(rs.getString("c_addr"));
				c.setC_text(rs.getString("c_text"));
				cs.add(c);
			}
			Gson g = new Gson();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			response.getWriter().write(g.toJson(cs));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void updateCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update company set c_name=?, c_keeper=?, c_phone=?, c_addr=?, c_text=? where c_no = ?";
		try {

			request.setCharacterEncoding("utf-8");

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_name"));
			pstmt.setString(2, request.getParameter("c_keeper"));
			pstmt.setString(3, request.getParameter("c_phone"));
			pstmt.setString(4, request.getParameter("c_addr"));
			pstmt.setString(5, request.getParameter("c_text"));
			pstmt.setString(6, request.getParameter("c_no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
				request.setAttribute("isSuccess", "Success");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void getContract(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM contract WHERE c_contract_no = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("c_contract_no")));
			rs = pstmt.executeQuery();
			Contract ct = null;
			if (rs.next()) {
				ct = new Contract();
				ct.setC_contract_no(rs.getInt("c_contract_no"));
				ct.setC_c_no(rs.getInt("c_c_no"));
				ct.setC_e_id(rs.getInt("c_e_id"));
				ct.setC_created_date(rs.getDate("c_created_date"));
				ct.setC_due_date(rs.getDate("c_due_date"));
				ct.setC_delivery_date(rs.getDate("c_delivery_date"));
				ct.setC_completed_date(rs.getDate("c_completed_date"));
				ct.setC_status(rs.getInt("c_status"));
				ct.setC_type(rs.getInt("c_type"));
				request.setAttribute("contract", ct);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void updateCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update contract set c_c_no=?, c_e_id=?, c_created_date=?, c_due_date=?, c_delivery_date=?, c_completed_date=?, c_status=?, c_type=? where c_contract_no = ?";
		try {

			request.setCharacterEncoding("utf-8");
			System.out.println(request.getParameter("c_contract_no"));
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_c_no"));
			pstmt.setString(2, request.getParameter("c_e_id"));
			pstmt.setString(3, request.getParameter("c_created_date"));
			pstmt.setString(4, request.getParameter("c_due_date"));
			pstmt.setString(5, request.getParameter("c_delivery_date"));
			pstmt.setString(6, request.getParameter("c_completed_date"));
			pstmt.setString(7, request.getParameter("c_status"));
			pstmt.setString(8, request.getParameter("c_type"));
			pstmt.setString(9, request.getParameter("c_contract_no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("계약서수정 성공");
				request.setAttribute("isSuccess", "Success");
			}

		} catch (Exception e) {
			System.out.println("계약서수정 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}
	
	public void updateContractItems(HttpServletRequest request) {
		
		String sql = "MERGE INTO contract_items ci "
		+ "USING ( "
		+ "    SELECT ? AS ci_no, "
		+ "           ? AS ci_c_contract_no, "
		+ "           ? AS ci_p_id, "
		+ "           ? AS ci_count, "
		+ "           ? AS ci_unit_price "
		+ "    FROM dual "
		+ ") s "
		+ "ON (ci.ci_no = s.ci_no) "
		+ "WHEN MATCHED THEN "
		+ "    UPDATE SET "
		+ "        ci.ci_p_id = s.ci_p_id, "
		+ "        ci.ci_count = s.ci_count, "
		+ "        ci.ci_unit_price = s.ci_unit_price "
		+ "WHEN NOT MATCHED THEN "
		+ "    INSERT (ci_no, ci_c_contract_no, ci_p_id, ci_count, ci_unit_price) "
		+ "    VALUES (contract_items_seq.nextval, s.ci_c_contract_no, s.ci_p_id, s.ci_count, s.ci_unit_price)";
		
		String ci_p_id[] = request.getParameterValues("ci_p_id");
		String ci_count[] = request.getParameterValues("ci_count");
		String ci_unit_price[] = request.getParameterValues("ci_unit_price");
		String ci_no[] = request.getParameterValues("ci_no");
		String c_contract_no = request.getParameter("c_contract_no");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			request.setCharacterEncoding("utf-8");
			
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < ci_no.length; i++) {
				if(i == 0) {
					continue;
				}
				pstmt.setString(1, ci_no[i]);
				pstmt.setString(2, c_contract_no);
				pstmt.setString(3, ci_p_id[i]);
				pstmt.setString(4, ci_count[i]);
				pstmt.setString(5, ci_unit_price[i]);
				
				if (pstmt.executeUpdate() == 1) {
					System.out.println("계약서 아이템수정/등록 성공");
				}
			}

		} catch (Exception e) {
			System.out.println("계약서 아이템수정/등록 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public ArrayList<ContractItems> getContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from contract_items where ci_c_contract_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("c_contract_no")));
			rs = pstmt.executeQuery();
			ContractItems cti = null;
			ArrayList<ContractItems> items = new ArrayList<ContractItems>();
			while (rs.next()) {
				cti = new ContractItems();
				cti.setCi_no(rs.getInt("ci_no"));
				cti.setCi_c_contract_no(rs.getInt("ci_c_contract_no"));
				cti.setCi_p_id(rs.getInt("ci_p_id"));
				cti.setCi_count(rs.getInt("ci_count"));
				cti.setCi_unit_price(rs.getInt("ci_unit_price"));
				items.add(cti);
			}
			request.setAttribute("items", items);
			return items;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
		return null;

	}

	public void updateContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update contract_items set ci_c_contract_no=?, ci_p_id=?, ci_count=?, ci_unit_price=? where ci_no = ?";
		try {

			request.setCharacterEncoding("utf-8");

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ci_c_contract_no"));
			pstmt.setString(2, request.getParameter("ci_p_id"));
			pstmt.setString(3, request.getParameter("ci_count"));
			pstmt.setString(4, request.getParameter("ci_unit_price"));
			pstmt.setString(5, request.getParameter("num"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
				request.setAttribute("isSuccess", "Success");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void searchCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM company INNER JOIN contract ON company.c_no = contract.s_c_no WHERE c_name like ?";
		try {

			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + request.getParameter("search") + "%");
			rs = pstmt.executeQuery();

			Contract ct = null;
			cts = new ArrayList<Contract>();
			while (rs.next()) {
				ct = new Contract();
				ct.setC_contract_no(rs.getInt("c_contract_no"));
				ct.setC_c_no(rs.getInt("c_c_no"));
				ct.setC_e_id(rs.getInt("c_e_id"));
				ct.setC_created_date(rs.getDate("c_created_date"));
				ct.setC_due_date(rs.getDate("c_due_date"));
				ct.setC_delivery_date(rs.getDate("c_delivery_date"));
				ct.setC_completed_date(rs.getDate("c_completed_date"));
				ct.setC_status(rs.getInt("c_status"));
				ct.setC_type(rs.getInt("c_type"));
				cts.add(ct);
			}

			request.setAttribute("cts", ct);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void searchContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + "FROM (SELECT * FROM company "
				+ "INNER JOIN contract ON company.c_no = contract.s_c_no) InnerJoinTable INNER JOIN contract_items ON InnerJoinTable.c_contract_no = contract_items.ci_c_contract_no "
				+ "WHERE InnerJoinTable.c_name LIKE ?";
		try {

			request.setCharacterEncoding("utf-8");
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + request.getParameter("search") + "%");
			rs = pstmt.executeQuery();

			ContractItems cti = null;
			ctis = new ArrayList<ContractItems>();
			while (rs.next()) {
				cti = new ContractItems();
				cti.setCi_no(rs.getInt("ci_no"));
				cti.setCi_c_contract_no(rs.getInt("ci_c_contract_no"));
				cti.setCi_p_id(rs.getInt("ci_p_id"));
				cti.setCi_count(rs.getInt("ci_count"));
				cti.setCi_unit_price(rs.getInt("ci_unit_price"));
				ctis.add(cti);
			}
			request.setAttribute("ctis", ctis);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}
	}

	public void getContractDetail(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String no = request.getParameter("no");
		System.out.println(no);
		String sql = "select a.*, b.e_name, b.e_deptno, b.e_rank, c.c_name from contract a inner join employee "
				+ "b on a.c_e_id = b.e_no inner join company c "
				+ "on a.c_c_no = c.c_no where a.c_contract_no = ?";
		try {

			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			Contract contract = null;
			if (rs.next()) {
				contract = new Contract();
				contract.setC_contract_no(rs.getInt("c_contract_no"));
				contract.setC_c_no(rs.getInt("c_c_no"));
				contract.setC_e_id(rs.getInt("c_e_id"));
				contract.setC_created_date(rs.getDate("c_created_date"));
				contract.setC_due_date(rs.getDate("c_due_date"));
				contract.setC_delivery_date(rs.getDate("c_delivery_date"));
				contract.setC_completed_date(rs.getDate("c_completed_date"));
				contract.setC_status(rs.getInt("c_status"));
				contract.setC_type(rs.getInt("c_type"));
				contract.setC_name(rs.getString("c_name"));
				contract.setE_name(rs.getString("e_name"));
				contract.setE_deptno(rs.getInt("e_deptno"));
				contract.setE_rank(rs.getString("e_rank"));
				pstmt.close();
				rs.close();
				sql = "select ci.*, p.p_name, p.p_type, p.p_si, p.p_quantity from contract_items ci, product p "
						+ "where p.p_id = ci.ci_p_id and ci_c_contract_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, no);
				rs = pstmt.executeQuery();
				ContractItems item = null;
				ArrayList<ContractItems> items = new ArrayList<ContractItems>();
				while (rs.next()) {
					item = new ContractItems();
					item.setCi_no(rs.getInt("ci_no"));
					item.setCi_c_contract_no(rs.getInt("ci_c_contract_no"));
					item.setCi_p_id(rs.getInt("ci_p_id"));
					item.setCi_count(rs.getInt("ci_count"));
					item.setCi_unit_price(rs.getInt("ci_unit_price"));
					item.setP_name(rs.getString("p_name"));
					item.setP_quantity(rs.getString("p_quantity"));
					item.setP_type(rs.getString("p_type"));
					item.setP_si(rs.getString("p_si"));
					items.add(item);
				}
				contract.setItems(items);
			}
			request.setAttribute("contract", contract);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, rs);
		}

	}

	public void deleteCompany(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "delete company where c_no = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_no"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("회사 삭제 성공");
			}

		} catch (Exception e) {
			System.out.println("회사 삭제 실패");
			e.printStackTrace();
		} finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void deleteContract(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete contract where c_contract_no = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_contract_no"));
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("계약서 삭제 성공");
			}
			
		} catch (Exception e) {
			System.out.println("계약서 삭제 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, null);
		}
	}

	public void deleteContractItem(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete contract_items where ci_no = ?";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ci_no"));
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("계약서아이템 삭제 성공");
			}
			
		} catch (Exception e) {
			System.out.println("계약서아이템 삭제 실패");
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, null);
		}
		
	}

}
