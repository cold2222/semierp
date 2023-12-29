package com.semi.sales.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.sales.supply.SupplyComDAO;

@WebServlet("/RegTypeC")
public class RegTypeC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO.getPdao().regType(request);
		ProductDAO.getPdao().getAllUnit(request);
		ProductDAO.getPdao().getAllType(request);
		request.setAttribute("contentPage", "jh/product/unit.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.setAttribute("selectedHeader", "contract");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	
	}

}
