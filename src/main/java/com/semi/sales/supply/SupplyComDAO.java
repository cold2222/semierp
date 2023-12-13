package com.semi.sales.supply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.sales.bbs.DBManager;
import com.semi.sales.product.Product;

public class SupplyComDAO {
	private ArrayList<SupplyCompany> scs;
	private static final SupplyComDAO SDAO = new SupplyComDAO();
	
	private SupplyComDAO() {
	}


	public static SupplyComDAO getSdao() {
		return SDAO;
	}
	
	public void paging(int page, HttpServletRequest request) {
		request.setAttribute("curPageNo", page);
		int cnt = 30;	// 한페이지당 보여줄 개수
		int total = scs.size();	// 총 데이터 개수
		// 총페이지수
		int pageCount = (int)Math.ceil((double)total / cnt);
		request.setAttribute("pageCount", pageCount);
		
		int start = total - (cnt * (page - 1));
		
		int end = (page == pageCount) ? - 1 : start - (cnt + 1);
		
		ArrayList<SupplyCompany> items = new ArrayList<SupplyCompany>();
		for (int i = start - 1; i > end; i--) {
			items.add(scs.get(i));
		}
		request.setAttribute("scs", items);
		
		
		
	}
	



	public void getAllCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from supply_status order by supply_num desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SupplyCompany sc = null;
			scs = new ArrayList<SupplyCompany>();
			while (rs.next()) {
				sc = new SupplyCompany();
				sc.setSupply_num(rs.getInt("supply_num"));
				sc.setSupply_company(rs.getString("supply_company"));
				sc.setSupply_name(rs.getString("supply_name"));
				sc.setSupplied_name(rs.getString("supplied_name"));
				sc.setSupply_addr(rs.getString("supply_addr"));
				sc.setPurchase_text(rs.getString("purchase_text"));
				scs.add(sc);
			}
			request.setAttribute("scs", scs);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}
	
	public void regCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into supply_status values(supply_status_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("supply_company"));
			pstmt.setString(2, request.getParameter("supply_name"));
			pstmt.setString(3, request.getParameter("supplied_name"));
			pstmt.setString(4, request.getParameter("supply_addr"));
			pstmt.setString(5, request.getParameter("purchase_text"));
			
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
		String sql = "select * from supply_status where supply_num=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			SupplyCompany sc = null;
			if (rs.next()) {
				sc = new SupplyCompany();
				sc.setSupply_num(rs.getInt("supply_num"));
				sc.setSupply_company(rs.getString("supply_company"));
				sc.setSupply_name(rs.getString("supply_name"));
				sc.setSupplied_name(rs.getString("supplied_name"));
				sc.setSupply_addr(rs.getString("supply_addr"));
				sc.setPurchase_text(rs.getString("purchase_text"));
				request.setAttribute("sc", sc);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}

	public static void getAllCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from purchase_buy_recordall order by status asc, purchase_date asc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SupplyContract st = null;
			ArrayList<SupplyContract> sts = new ArrayList<SupplyContract>();
			while (rs.next()) {
				st = new SupplyContract();
				st.setRecordall_buy_num(rs.getInt("recordall_buy_num"));
				st.setSupply_num(rs.getInt("supply_num"));
				st.setPurchase_date(rs.getDate("purchase_date"));
				st.setTransaction_date(rs.getDate("transaction_date"));
				st.setIn_warehouse_date(rs.getDate("in_warehouse_date"));
				st.setStatus(rs.getInt("status"));
				sts.add(st);
			}
			request.setAttribute("sts", sts);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void regCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into purchase_buy_recordall values(purchase_buy_recordall_seq.nextval, ?, ?, ?, ?, 1)";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("supply_num"));
			pstmt.setString(2, request.getParameter("purchase_date"));
			pstmt.setString(3, request.getParameter("transaction_date"));
			pstmt.setString(4, request.getParameter("in_warehouse_date"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	
	
		
		
		
		
		
	

	public static void regContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into purchase_buy_record values(purchase_buy_record_seq.nextval, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("recordall_buy_num"));
			pstmt.setString(2, request.getParameter("p_id"));
			pstmt.setString(3, request.getParameter("record_count"));
			pstmt.setString(4, request.getParameter("record_price"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getAllContents(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from purchase_buy_record order by record_buy_num desc";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SupplyContents content = null;
			ArrayList<SupplyContents> contents = new ArrayList<SupplyContents>();
			while (rs.next()) {
				content = new SupplyContents();
				content.setRecord_buy_num(rs.getInt("record_buy_num"));
				content.setRecordall_buy_num(rs.getInt("recordall_buy_num"));
				content.setP_id(rs.getInt("p_id"));
				content.setRecord_count(rs.getInt("record_count"));
				content.setRecord_price(rs.getInt("record_price"));
				contents.add(content);
			}
			request.setAttribute("contents", contents);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	

	public void searchCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from supply_status where supply_company like ?";
		try {
			
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
			rs = pstmt.executeQuery();
			
			SupplyCompany sc = null;
			ArrayList<SupplyCompany> scs = new ArrayList<SupplyCompany>();
			while (rs.next()) {
				sc = new SupplyCompany();
				sc.setSupply_num(rs.getInt("supply_num"));
				sc.setSupply_company(rs.getString("supply_company"));
				sc.setSupply_name(rs.getString("supply_name"));
				sc.setSupplied_name(rs.getString("supplied_name"));
				sc.setSupply_addr(rs.getString("supply_addr"));
				sc.setPurchase_text(rs.getString("purchase_text"));
				scs.add(sc);
			}
			request.setAttribute("scs", scs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public void updateCom(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update supply_status set supply_company=?, supply_name=?, supplied_name=?, supply_addr=?, purchase_text=? where supply_num = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("supply_company"));
			pstmt.setString(2, request.getParameter("supply_name"));
			pstmt.setString(3, request.getParameter("supplied_name"));
			pstmt.setString(4, request.getParameter("supply_addr"));
			pstmt.setString(5, request.getParameter("purchase_text"));
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

	

	public static void getAllProduct(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Product p = null;
			ArrayList<Product> ps = new ArrayList<Product>();
			while (rs.next()) {
				p = new Product();
				p.setP_id(rs.getInt("p_id"));
				p.setP_si(rs.getString("p_si"));
				p.setP_type(rs.getString("p_type"));
				p.setP_quantity(rs.getInt("p_quantity"));
				p.setP_name(rs.getString("p_name"));
				p.setP_unitCost(rs.getString("p_unitCost"));
				p.setP_minStock(rs.getString("p_minStock"));
				p.setP_maxStock(rs.getString("p_maxStock"));
				p.setP_manufacturer(rs.getInt("p_manufacturer"));
				ps.add(p);
			}
			request.setAttribute("ps", ps);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void getAllUnit(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from unit";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Product p = null;
			ArrayList<Product> ps = new ArrayList<Product>();
			while (rs.next()) {
				p = new Product();
				p.setP_id(rs.getInt("p_id"));
				p.setP_si(rs.getString("p_si"));
				p.setP_type(rs.getString("p_type"));
				p.setP_quantity(rs.getInt("p_quantity"));
				p.setP_name(rs.getString("p_name"));
				p.setP_unitCost(rs.getString("p_unitCost"));
				p.setP_minStock(rs.getString("p_minStock"));
				p.setP_maxStock(rs.getString("p_maxStock"));
				p.setP_manufacturer(rs.getInt("p_manufacturer"));
				ps.add(p);
			}
			request.setAttribute("ps", ps);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	
	}

	public static void getCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from purchase_buy_recordall where recordall_buy_num=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			SupplyContract st = null;
			if (rs.next()) {
				st = new SupplyContract();
				st.setRecordall_buy_num(rs.getInt("recordall_buy_num"));
				st.setSupply_num(rs.getInt("supply_num"));
				st.setPurchase_date(rs.getDate("purchase_date"));
				st.setTransaction_date(rs.getDate("transaction_date"));
				st.setIn_warehouse_date(rs.getDate("in_warehouse_date"));
				st.setStatus(rs.getInt("status"));
				request.setAttribute("st", st);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void updateCont(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update purchase_buy_recordall set supply_num=?, purchase_date=?, transaction_date=?, in_warehouse_date=?, status=? where recordall_buy_num = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("supply_num"));
			pstmt.setString(2, request.getParameter("purchase_date"));
			pstmt.setString(3, request.getParameter("transaction_date"));
			pstmt.setString(4, request.getParameter("in_warehouse_date"));
			pstmt.setString(5, request.getParameter("status"));
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

	public static void getContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from purchase_buy_record where record_buy_num=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
			rs = pstmt.executeQuery();
			SupplyContents c = null;
			if (rs.next()) {
				c = new SupplyContents();
				c.setRecord_buy_num(rs.getInt("record_buy_num"));
				c.setRecordall_buy_num(rs.getInt("recordall_buy_num"));
				c.setP_id(rs.getInt("p_id"));
				c.setRecord_count(rs.getInt("record_count"));
				c.setRecord_price(rs.getInt("record_price"));
				request.setAttribute("c", c);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public static void updateContent(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update purchase_buy_record set recordall_buy_num=?, p_id=?, record_count=?, record_price=? where record_buy_num = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("recordall_buy_num"));
			pstmt.setString(2, request.getParameter("p_id"));
			pstmt.setString(3, request.getParameter("record_count"));
			pstmt.setString(4, request.getParameter("record_price"));
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

	public static void regProduct(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			
			request.setCharacterEncoding("utf-8");
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("p_si"));
			pstmt.setString(2, request.getParameter("p_type"));
			pstmt.setString(3, request.getParameter("p_quantity"));
			pstmt.setString(4, request.getParameter("p_name"));
			pstmt.setString(5, request.getParameter("p_unitCost"));
			pstmt.setString(6, request.getParameter("p_minStock"));
			pstmt.setString(7, request.getParameter("p_maxStock"));
			pstmt.setString(8, request.getParameter("p_manufacturer"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getP(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product where p_id=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("id")));
			rs = pstmt.executeQuery();
			Product p = null;
			if (rs.next()) {
				p = new Product();
				p.setP_id(rs.getInt("p_id"));
				p.setP_si(rs.getString("p_si"));
				p.setP_type(rs.getString("p_type"));
				p.setP_quantity(rs.getInt("p_quantity"));
				p.setP_name(rs.getString("p_name"));
				p.setP_unitCost(rs.getString("p_unitCost"));
				p.setP_minStock(rs.getString("p_minStock"));
				p.setP_maxStock(rs.getString("p_maxStock"));
				p.setP_manufacturer(rs.getInt("p_manufacturer"));
				request.setAttribute("p", p);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void updateProduct(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update product set p_si=?, p_type=?, p_quantity=?, p_name=?, p_unitCost=?, p_minStock=?, p_maxStock=?, p_manufacturer=? where p_id = ?";
		try {
			
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("p_si"));
			pstmt.setString(2, request.getParameter("p_type"));
			pstmt.setString(3, request.getParameter("p_quantity"));
			pstmt.setString(4, request.getParameter("p_name"));
			pstmt.setString(5, request.getParameter("p_unitCost"));
			pstmt.setString(6, request.getParameter("p_minStock"));
			pstmt.setString(7, request.getParameter("p_maxStock"));
			pstmt.setString(8, request.getParameter("p_manufacturer"));
			pstmt.setString(9, request.getParameter("id"));
			
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

	

}
