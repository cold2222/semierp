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
		SupplyComDAO.regType(request);
		SupplyComDAO.getAllUnit(request);
		SupplyComDAO.getAllType(request);
		request.setAttribute("page", "product/unit.jsp");
		request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	}

}
