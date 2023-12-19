package com.semi.sales.supply;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.google.gson.Gson;
import com.semi.sales.bbs.DBManager;

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
	    
	    
	    int cnt = 30; // 한 페이지당 보여줄 개수
	    int total = cs.size(); // 총 데이터 개수
	    int pageCount = (int) Math.ceil((double) total / cnt); // 총 페이지 수

	    if (page > pageCount) {
	        page = pageCount;
	    }

	    // 시작과 끝 인덱스 계산
	    int start = (page - 1) * cnt;
	    int end = Math.min(start + cnt, total);

	    ArrayList<Company> items = new ArrayList<>();
	    if (total > 0 && start < total) {
	        for (int i = start; i < end; i++) {
	            items.add(cs.get(i));
	        }
	    }
	    request.setAttribute("cs", items);
		request.setAttribute("pageNum", page);
		request.setAttribute("pageCount", pageCount);
	}



	public void getAllCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String,String> search = new HashMap<String, String>();
		String field = request.getParameter("field");
		String word = request.getParameter("word");
		if(word != null) {
			search.put("field", field);
			search.put("word", word);
		}
		
		String sql = "select * from company";
		if(search.get("word") != null && !search.get("field").equals("all")) {
			sql += " where " + search.get("field") + " " + "like '%" + search.get("word") +"%'";
		}
		
		sql += " order by c_no desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
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
			DBManager.close(con, pstmt, rs);
		}
		
		
	}
	
	public void regCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into company values(company_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_name"));
			pstmt.setString(2, request.getParameter("c_keeper"));
			pstmt.setString(3, request.getParameter("c_phone"));
			pstmt.setString(4, request.getParameter("c_addr"));
			pstmt.setString(5, request.getParameter("c_text"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		
	}

	public void getCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from company where c_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
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
			DBManager.close(con, pstmt, rs);
		}
		
		
	}

	public void getAllCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select contract.*, company.c_name from contract, company where contract.s_c_no = company.c_no order by c_status asc, c_created_date asc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Contract ct = null;
			cts = new ArrayList<Contract>();
			while (rs.next()) {
				ct = new Contract();
				ct.setC_contract_no(rs.getInt("c_contract_no"));
				ct.setS_c_no(rs.getInt("s_c_no"));
				ct.setC_e_id(rs.getInt("c_e_id"));
				ct.setC_created_date(rs.getDate("c_created_date"));
				ct.setC_due_date(rs.getDate("c_due_date"));
				ct.setC_delivery_date(rs.getDate("c_delivery_date"));
				ct.setC_completed_date(rs.getDate("c_completed_date"));
				ct.setC_status(rs.getInt("c_status"));
				ct.setC_type(rs.getInt("c_type"));
				ct.setC_name(rs.getString("c_name"));
				cts.add(ct);
			}
			request.setAttribute("cts", cts);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	
	public void regCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into contract values(contract_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			System.out.println(request.getParameter("s_c_no"));
			System.out.println(request.getParameter("c_e_id"));
			System.out.println(request.getParameter("c_created_date"));
			System.out.println(request.getParameter("c_due_date"));
			System.out.println(request.getParameter("c_delivery_date"));
			System.out.println(request.getParameter("c_completed_date"));
			System.out.println(request.getParameter("c_status"));
			System.out.println(request.getParameter("c_type"));
			
		pstmt.setString(1, request.getParameter("s_c_no"));
		pstmt.setString(2, request.getParameter("c_e_id"));
		pstmt.setString(3, request.getParameter("c_created_date"));
		pstmt.setString(4, request.getParameter("c_due_date"));
		pstmt.setString(5, request.getParameter("c_delivery_date"));
		pstmt.setString(6, request.getParameter("c_completed_date"));
		pstmt.setString(7, request.getParameter("c_status"));
		pstmt.setString(8, request.getParameter("c_type"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public void getAllContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from contract_items order by ci_no desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
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
			DBManager.close(con, pstmt, rs);
		}
	}

	public void regContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;
		try {
			System.out.println(Statement.RETURN_GENERATED_KEYS);
			String sql = "insert into contract_items values(contract_items_seq.nextval, ?, ?, ?, ?)";
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(2, request.getParameter("ci_p_id"));
			pstmt.setString(3, request.getParameter("ci_count"));
			pstmt.setString(4, request.getParameter("ci_unit_price"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void searchCom(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql = "select * from company where c_name like ?";
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
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

			request.setAttribute("cs", cs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public void updateCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update company set c_e_id=?, c_name=?, c_keeper=?, c_phone=?, c_addr=?, c_text=? where c_no = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("c_e_id"));
			pstmt.setString(2, request.getParameter("c_name"));
			pstmt.setString(3, request.getParameter("c_keeper"));
			pstmt.setString(4, request.getParameter("c_phone"));
			pstmt.setString(5, request.getParameter("c_addr"));
			pstmt.setString(6, request.getParameter("c_text"));
			pstmt.setString(7, request.getParameter("num"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
				request.setAttribute("isSuccess", "Success");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	

	

	

	public void getCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from contract where c_contract_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
			Contract ct = null;
			if (rs.next()) {
				ct = new Contract();
				ct.setC_contract_no(rs.getInt("c_contract_no"));
				ct.setS_c_no(rs.getInt("s_c_no"));
				ct.setC_e_id(rs.getInt("c_e_id"));
				ct.setC_created_date(rs.getDate("c_created_date"));
				ct.setC_due_date(rs.getDate("c_due_date"));
				ct.setC_delivery_date(rs.getDate("c_delivery_date"));
				ct.setC_completed_date(rs.getDate("c_completed_date"));
				ct.setC_status(rs.getInt("c_status"));
				ct.setC_type(rs.getInt("c_type"));
				request.setAttribute("ct", ct);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void updateCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update contract set s_c_no=?, c_e_id=?, c_created_date=?, c_due_date=?, c_delivery_date=?, c_completed_date=?, c_status=?, c_type=? where c_contract_no = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("s_c_no"));
			pstmt.setString(2, request.getParameter("c_e_id"));
			pstmt.setString(3, request.getParameter("c_created_date"));
			pstmt.setString(4, request.getParameter("c_due_date"));
			pstmt.setString(5, request.getParameter("c_delivery_date"));
			pstmt.setString(6, request.getParameter("c_completed_date"));
			pstmt.setString(7, request.getParameter("c_status"));
			pstmt.setString(8, request.getParameter("c_type"));
			pstmt.setString(9, request.getParameter("num"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
				request.setAttribute("isSuccess", "Success");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void getContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from contract_items where ci_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
			ContractItems cti = null;
			if (rs.next()) {
				cti = new ContractItems();
				cti.setCi_no(rs.getInt("ci_no"));
				cti.setCi_c_contract_no(rs.getInt("ci_c_contract_no"));
				cti.setCi_p_id(rs.getInt("ci_p_id"));
				cti.setCi_count(rs.getInt("ci_count"));
				cti.setCi_unit_price(rs.getInt("ci_unit_price"));
			}
			request.setAttribute("cti", cti);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public void updateContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update contract_items set ci_c_contract_no=?, ci_p_id=?, ci_count=?, ci_unit_price=? where ci_no = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
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
			DBManager.close(con, pstmt, null);
		}
	}

	

	

	

	


	


	


	


	


	


	


	

	


	public void searchCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM company INNER JOIN contract ON company.c_no = contract.s_c_no WHERE c_name like ?";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
			rs = pstmt.executeQuery();
			
			Contract ct = null;
			cts = new ArrayList<Contract>();
			while (rs.next()) {
				ct = new Contract();
				ct.setC_contract_no(rs.getInt("c_contract_no"));
				ct.setS_c_no(rs.getInt("s_c_no"));
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
			DBManager.close(con, pstmt, rs);
		}
		
	}


	public void searchContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ "FROM (SELECT * FROM company "
				+ "INNER JOIN contract ON company.c_no = contract.s_c_no) InnerJoinTable INNER JOIN contract_items ON InnerJoinTable.c_contract_no = contract_items.ci_c_contract_no "
				+ "WHERE InnerJoinTable.c_name LIKE ?";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
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
			DBManager.close(con, pstmt, rs);
		}
	}


	



	

}
