package com.semi.sales.supply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CompanyRegC")
public class CompanyRegC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setAttribute("page", "company_reg.jsp");
		request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		SupplyComDAO.regCom(request);
		SupplyComDAO.getAllCom(request);
		request.setAttribute("page", "output_supply.jsp");
		request.getRequestDispatcher("jh/index.jsp").forward(request, response);
	
	
	}

}
