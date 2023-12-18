package com.semi.sales.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.sales.supply.SupplyComDAO;

@WebServlet("/UpdateProductC")
public class UpdateProductC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ProductDAO.getPdao().getP(request);
		request.setAttribute("contentPage", "jh/product/updateProduct.jsp");
		request.setAttribute("sidebar", "jh/sidebar.jsp");
		request.getRequestDispatcher("index2.jsp").forward(request, response);
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ProductDAO.getPdao().updateProduct(request);
		response.sendRedirect("UpdateProduct?id="+request.getParameter("id")+"&isSuccess="
				+ request.getAttribute("isSuccess"));	
	
	
	
	}

}
