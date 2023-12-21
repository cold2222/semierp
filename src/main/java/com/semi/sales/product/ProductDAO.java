package com.semi.sales.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.sales.bbs.DBManager;

public class ProductDAO {
	private ArrayList<Product> productItems;
	private static final ProductDAO PDAO = new ProductDAO();

	private ProductDAO() {
	}

	public static ProductDAO getPdao() {
		return PDAO;
	}

	public void getType(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from type where type=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("t"));
			rs = pstmt.executeQuery();
			Type t = null;
			if (rs.next()) {
				t = new Type();
				t.setType(rs.getString("type"));
				request.setAttribute("t", t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void deleteType(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "delete type where type=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("t"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void getAllUnit(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from unit";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Unit u = null;
			ArrayList<Unit> us = new ArrayList<Unit>();
			while (rs.next()) {
				u = new Unit();
				u.setUnit(rs.getString("unit"));
				us.add(u);
			}
			request.setAttribute("us", us);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void getAllType(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from type";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Type t = null;
			ArrayList<Type> ts = new ArrayList<Type>();
			while (rs.next()) {
				t = new Type();
				t.setType(rs.getString("type"));
				ts.add(t);
			}
			request.setAttribute("ts", ts);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void getUnit(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from unit where unit=?";
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("u"));
			rs = pstmt.executeQuery();
			Unit u = null;
			if (rs.next()) {
				u = new Unit();
				u.setUnit(rs.getString("unit"));
				request.setAttribute("u", u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void deleteUnit(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "delete unit where unit=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("u"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void getAllProduct(HttpServletRequest request) {
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
		
		String sql = "select * from product";
		if(search.get("word") != null && !search.get("field").equals("all")) {
			sql += " where " + search.get("field") + " " + "like '%" + search.get("word") +"%'";
		}
		
		try {
			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Product p = null;
			productItems = new ArrayList<Product>();
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
				p.setP_manufacturer(rs.getString("p_manufacturer"));
				productItems.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void regProduct(HttpServletRequest request) {
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

	public void regType(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into type values(?)";
		try {

			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("type"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void regUnit(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into unit values(?)";
		try {

			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("unit"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void getP(HttpServletRequest request) {
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
				p.setP_manufacturer(rs.getString("p_manufacturer"));
				request.setAttribute("p", p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void updateProduct(HttpServletRequest request) {
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void updateType(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update type set type=? where type = ?";
		try {

			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("type"));
			pstmt.setString(2, request.getParameter("t"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void updateUnit(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update unit set unit=? where unit = ?";
		try {

			request.setCharacterEncoding("utf-8");

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("unit"));
			pstmt.setString(2, request.getParameter("u"));

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

	public void deleteProduct(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete product where p_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("p_id"));
			if(pstmt.executeUpdate() == 1) {
				System.out.println("프로덕트 삭제 성공");
			}
			
		}  catch (Exception e) {
			System.out.println("프로덕트 삭제 실패");
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

}
