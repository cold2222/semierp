package com.semi.sales.supply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.sales.bbs.DBManager;
import com.semi.sales.product.Product;

public class SupplyComDAO {
	private ArrayList<ImportCompany> ics;
	private ArrayList<BuyContract> bcs;
	private ArrayList<BuyItem> bis;
	
	private static final SupplyComDAO SDAO = new SupplyComDAO();
	
	private SupplyComDAO() {
	}


	public static SupplyComDAO getSdao() {
		return SDAO;
	}
	
	public void paging(int page, HttpServletRequest request) {
	    request.setAttribute("curPageNo", page);
	    int cnt = 30; // 한 페이지당 보여줄 개수
	    int total = ics.size(); // 총 데이터 개수
	    int pageCount = (int) Math.ceil((double) total / cnt); // 총 페이지 수
	    request.setAttribute("pageCount", pageCount);

	    int start = (page - 1) * cnt; // 시작 인덱스 계산
	    int end = Math.min(start + cnt, total); // 종료 인덱스 계산

	    ArrayList<ImportCompany> items = new ArrayList<>();
	    for (int i = start; i < end; i++) {
	        items.add(ics.get(i));
	    }
	    request.setAttribute("ics", items);
	}



	public void getAllCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from import_company order by ic_no desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ImportCompany ic = null;
			ics = new ArrayList<ImportCompany>();
			while (rs.next()) {
				ic = new ImportCompany();
				ic.setIc_no(rs.getInt("ic_no"));
				ic.setIc_e_id(rs.getInt("ic_e_id"));
				ic.setIc_name(rs.getString("ic_name"));
				ic.setIc_keeper(rs.getString("ic_keeper"));
				ic.setIc_phone(rs.getString("ic_phone"));
				ic.setIc_addr(rs.getString("ic_addr"));
				ic.setIc_text(rs.getString("ic_text"));
				ics.add(ic);
			}
			request.setAttribute("ics", ics);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}
	
	public void regCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into import_company values(ic_no_seq.nextval, ?, ?, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ic_e_id"));
			pstmt.setString(2, request.getParameter("ic_name"));
			pstmt.setString(3, request.getParameter("ic_keeper"));
			pstmt.setString(4, request.getParameter("ic_phone"));
			pstmt.setString(5, request.getParameter("ic_addr"));
			pstmt.setString(6, request.getParameter("ic_text"));
			
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
		String sql = "select * from import_company where ic_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			ImportCompany ic = null;
			if (rs.next()) {
				ic = new ImportCompany();
				ic.setIc_no(rs.getInt("ic_no"));
				ic.setIc_e_id(rs.getInt("ic_e_id"));
				ic.setIc_name(rs.getString("ic_name"));
				ic.setIc_keeper(rs.getString("ic_keeper"));
				ic.setIc_phone(rs.getString("ic_phone"));
				ic.setIc_addr(rs.getString("ic_addr"));
				ic.setIc_text(rs.getString("ic_text"));
				request.setAttribute("ic", ic);
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
		String sql = "select b.*, i.ic_name from buy_contract b, import_company i\r\n"
				+ " where b.b_ic_no = i.ic_no "
				+ " order by b_status asc, b_contract asc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			BuyContract bc = null;
			bcs = new ArrayList<BuyContract>();
			while (rs.next()) {
				bc = new BuyContract();
				bc.setB_contract_no(rs.getInt("b_contract_no"));
				bc.setB_ic_no(rs.getInt("b_ic_no"));
				bc.setB_e_id(rs.getInt("b_e_id"));
				bc.setB_contract(rs.getDate("b_contract"));
				bc.setB_arrival_date(rs.getDate("b_arrival_date"));
				bc.setB_delivery_date(rs.getDate("b_delivery_date"));
				bc.setB_date(rs.getDate("b_date"));
				bc.setB_status(rs.getInt("b_status"));
				bc.setIc_name(rs.getString("ic_name"));
				bcs.add(bc);
			}
			request.setAttribute("bcs", bcs);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void regCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into buy_contract values(buy_contract_seq.nextval, ?, ?, ?, ?, ?, ?, 1)";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("b_ic_no"));
			pstmt.setString(2, request.getParameter("b_e_id"));
			pstmt.setString(3, request.getParameter("b_contract"));
			pstmt.setString(4, request.getParameter("b_arrival_date"));
			pstmt.setString(5, request.getParameter("b_delivery_date"));
			pstmt.setString(6, request.getParameter("b_date"));
			pstmt.setString(7, request.getParameter("b_status"));
			
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
		String sql = "select * from buy_item order by bi_no desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			BuyItem bi = null;
			bis = new ArrayList<BuyItem>();
			while (rs.next()) {
				bi = new BuyItem();
				bi.setBi_no(rs.getInt("bi_no"));
				bi.setBi_b_contract_no(rs.getInt("bi_b_contract_no"));
				bi.setBi_p_id(rs.getInt("bi_p_id"));
				bi.setBi_buy_count(rs.getInt("bi_buy_count"));
				bi.setBi_unit_price(rs.getInt("bi_unit_price"));
				bis.add(bi);
			}
			request.setAttribute("bis", bis);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void regContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into buy_item values(buy_item_seq.nextval, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("bi_b_contract_no"));
			pstmt.setString(2, request.getParameter("bi_p_id"));
			pstmt.setString(3, request.getParameter("bi_buy_count"));
			pstmt.setString(4, request.getParameter("bi_unit_price"));
			
			if (pstmt.executeUpdate() >= 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void searchCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql = "select * from import_company where ic_name like ?";
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
			rs = pstmt.executeQuery();
			
			ImportCompany ic = null;
			ics = new ArrayList<ImportCompany>();
			while (rs.next()) {
				ic = new ImportCompany();
				ic.setIc_no(rs.getInt("ic_no"));
				ic.setIc_e_id(rs.getInt("ic_e_id"));
				ic.setIc_name(rs.getString("ic_name"));
				ic.setIc_keeper(rs.getString("ic_keeper"));
				ic.setIc_phone(rs.getString("ic_phone"));
				ic.setIc_addr(rs.getString("ic_addr"));
				ic.setIc_text(rs.getString("ic_text"));
				ics.add(ic);
			}
			request.setAttribute("ics", ics);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public void updateCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update import_company set ic_e_id=?, ic_name=?, ic_keeper=?, ic_phone=?, ic_addr=?, ic_text=? where ic_no = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ic_e_id"));
			pstmt.setString(2, request.getParameter("ic_name"));
			pstmt.setString(3, request.getParameter("ic_keeper"));
			pstmt.setString(4, request.getParameter("ic_phone"));
			pstmt.setString(4, request.getParameter("ic_addr"));
			pstmt.setString(5, request.getParameter("ic_text"));
			pstmt.setString(6, request.getParameter("num"));
			
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
		String sql = "select * from buy_contract where b_contract_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			BuyContract bc = null;
			if (rs.next()) {
				bc = new BuyContract();
				bc.setB_contract_no(rs.getInt("b_contract_no"));
				bc.setB_ic_no(rs.getInt("b_ic_no"));
				bc.setB_e_id(rs.getInt("b_e_id"));
				bc.setB_contract(rs.getDate("b_contract"));
				bc.setB_arrival_date(rs.getDate("b_arrival_date"));
				bc.setB_delivery_date(rs.getDate("b_delivery_date"));
				bc.setB_date(rs.getDate("b_date"));
				bc.setB_status(rs.getInt("b_status"));
				request.setAttribute("bc", bc);
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
		String sql = "update buy_contract set b_ic_no=?, b_e_id=?, b_contract=?, b_arrival_date=?, b_delivery_date=?, b_date=?, b_status=? where b_contract_no = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("b_ic_no"));
			pstmt.setString(2, request.getParameter("b_e_id"));
			pstmt.setString(3, request.getParameter("b_contract"));
			pstmt.setString(4, request.getParameter("b_arrival_date"));
			pstmt.setString(5, request.getParameter("b_delivery_date"));
			pstmt.setString(6, request.getParameter("b_date"));
			pstmt.setString(7, request.getParameter("b_status"));
			
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
		String sql = "select * from buy_item where bi_no=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			BuyItem bi = null;
			if (rs.next()) {
				bi = new BuyItem();
				bi.setBi_no(rs.getInt("bi_no"));
				bi.setBi_b_contract_no(rs.getInt("bi_b_contarct_no"));
				bi.setBi_p_id(rs.getInt("b_p_id"));
				bi.setBi_buy_count(rs.getInt("bi_buy_count"));
				bi.setBi_unit_price(rs.getInt("bi_unit_price"));
			}
			request.setAttribute("bi", bi);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public void updateContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update buy_item set bi_b_contract_no=?, bi_p_id=?, bi_buy_count=?, bi_unit_price=? where bi_no = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("bi_b_contract_no"));
			pstmt.setString(2, request.getParameter("bi_p_id"));
			pstmt.setString(3, request.getParameter("bi_buy_count"));
			pstmt.setString(4, request.getParameter("bi_unit_price"));
			pstmt.setString(5, request.getParameter("num"));
			
			if (pstmt.executeUpdate() >= 1) {
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
		String sql = "SELECT * FROM import_company INNER JOIN buy_contract ON import_company.ic_no = buy_contract.b_ic_no WHERE ic_name like ?";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
			rs = pstmt.executeQuery();
			
			BuyContract bc = null;
			bcs = new ArrayList<BuyContract>();
			while (rs.next()) {
				bc = new BuyContract();
				bc.setB_contract_no(rs.getInt("b_contract_no"));
				bc.setB_ic_no(rs.getInt("b_ic_no"));
				bc.setB_e_id(rs.getInt("b_e_id"));
				bc.setB_contract(rs.getDate("b_contract"));
				bc.setB_arrival_date(rs.getDate("b_arrival_date"));
				bc.setB_delivery_date(rs.getDate("b_delivery_date"));
				bc.setB_date(rs.getDate("b_date"));
				bc.setB_status(rs.getInt("b_status"));
				bcs.add(bc);
			}
			request.setAttribute("bcs", bc);
			
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
				+ "FROM (SELECT * FROM import_company "
				+ "INNER JOIN buy_contract ON import_company.ic_no = buy_contract.b_ic_no) InnerJoinTable INNER JOIN buy_item ON InnerJoinTable.b_contract_no = buy_item.bi_b_contract_no "
				+ "WHERE InnerJoinTable.ic_name LIKE ?";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
			rs = pstmt.executeQuery();
			
			BuyItem bi = null;
			bis = new ArrayList<BuyItem>();
			while (rs.next()) {
				bi = new BuyItem();
				bi.setBi_no(rs.getInt("bi_no"));
				bi.setBi_b_contract_no(rs.getInt("bi_b_contract_no"));
				bi.setBi_p_id(rs.getInt("bi_p_id"));
				bi.setBi_buy_count(rs.getInt("bi_buy_count"));
				bi.setBi_unit_price(rs.getInt("bi_unit_price"));
				bis.add(bi);
			}
			request.setAttribute("bi", bi);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}


	



	

}
