package com.semi.sales.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.sales.supply.SupplyComDAO;

@WebServlet("/ProductC")
public class ProductC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ProductDAO.getPdao().getAllProduct(request);
		ProductDAO.getPdao().getAllUnit(request);
		ProductDAO.getPdao().getAllType(request);
		ProductDAO.getPdao().paging(1, request);
		request.setAttribute("contentPage", "jh/product/supply_product.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.setAttribute("selectedHeader", "contract");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
