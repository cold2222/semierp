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
	
		SupplyComDAO.getP(request);
		request.setAttribute("page", "product/updateProduct.jsp");
		request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		SupplyComDAO.updateProduct(request);
		response.sendRedirect("UpdateProductC?id="+request.getParameter("id")+"&isSuccess="
				+ request.getAttribute("isSuccess"));
	
	
	
	}

}
